package com.stock.tool;

import com.stock.entity.StockHistoryInfo;
import com.stock.repository.IStockHistoryInfoRepository;

import java.sql.Date;
import java.util.List;

public class RsvCalculator implements Runnable {

    IStockHistoryInfoRepository stockHistoryInfoRepository;

    public RsvCalculator(IStockHistoryInfoRepository stockHistoryInfoRepository) {
        this.stockHistoryInfoRepository = stockHistoryInfoRepository;
    }

    @Override
    public void run() {
        List<StockHistoryInfo> historyInfoList = stockHistoryInfoRepository.findAll();
        for (StockHistoryInfo historyInfo : historyInfoList){
            if (historyInfo.getId()>8) {
                Date stockTransferDay = historyInfo.getDate();
                Date startDate = new Date(stockTransferDay.getTime() - 1000 * 60 * 60 * 24 * 8);
                Date yesterDay = new Date(stockTransferDay.getTime() - 1000 * 60 * 60 * 24);
                Float tenDaysHighest = stockHistoryInfoRepository.getTenDaysHighestPrice(startDate, stockTransferDay);
                Float tenDaysLowest = stockHistoryInfoRepository.getTenDaysLowestPrice(startDate, stockTransferDay);
                Float currentPrice = historyInfo.getClosingPrice();

                Float rsv;
                if (tenDaysHighest.equals(tenDaysLowest)) {
                    rsv = 0f;
                } else {
                    rsv = (currentPrice - tenDaysLowest) / (tenDaysHighest - tenDaysLowest) * 100;
                }

                historyInfo.setRsv(rsv);

                StockHistoryInfo lastDayHistory = stockHistoryInfoRepository.getHistoryById(historyInfo.getId()-1, "0050");

                if (historyInfo.getId() == 1563){
                    System.out.println();
                }

                    if (lastDayHistory != null) {
                        Float lastDayKValue = lastDayHistory.getK();
                        Float lastDayDValue = lastDayHistory.getD();

                        Float k = 2*lastDayKValue/3 + rsv/3;
                        Float d = 2*lastDayDValue/3 + k/3;

                        historyInfo.setK(k);
                        historyInfo.setD(d);
                        historyInfo.setRsv(rsv);
                        stockHistoryInfoRepository.save(historyInfo);
                    }
            }
        }
    }
}
