package com.stock.tool;

import com.stock.entity.Stock;
import com.stock.entity.StockHistoryInfo;
import com.stock.repository.IStockHistoryInfoRepository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Date;
import java.util.List;

public class StcokHistoryCrawler implements Runnable {

    private IStockHistoryInfoRepository stockHistoryInfoRepository;

    private Stock stock;

    private StockHistoryInfo stockHistoryInfo;

    private List<String> stockIdList;

    public StcokHistoryCrawler(Stock stock,IStockHistoryInfoRepository stockHistoryInfoRepository) {
        this.stock = stock;
        this.stockHistoryInfoRepository = stockHistoryInfoRepository;
    }

    public StcokHistoryCrawler(IStockHistoryInfoRepository stockHistoryInfoRepository, List<String> stockIdList) {
        this.stockHistoryInfoRepository = stockHistoryInfoRepository;
        this.stockIdList = stockIdList;
    }

    @Override
    public void run() {
        String url;
        String year = "2010";
        String month = "01";
        String day = "04";
        String date;
        String stockId;

        for (String id : stockIdList) {
            Stock stock = new Stock();
            stock.setId(id);
            stockId = id;
            try {
                while (true) {

                    Thread.sleep(3000);
                    date = year + month + day;
                    url = "https://www.twse.com.tw/exchangeReport/STOCK_DAY?response=json&date=" + date + "&stockNo=" + stockId + "&_=1571207702955";
                    RequestSender requestSender = new RequestSender();
                    String responseData = requestSender.sendRequest(url, "GET");
                    JSONArray data = new JSONObject(responseData).getJSONArray("data");

                    for (Object array : data) {
                        stockHistoryInfo = new StockHistoryInfo();
                        JSONArray stockData = (JSONArray) array;
                        Date stockDate = DateFormatter.format(stockData.getString(0));
                        stockHistoryInfo.setDate(stockDate);
                        stockHistoryInfo.setVolumeStock(Integer.valueOf(stockData.getString(1).replace(",", "")));
                        stockHistoryInfo.setTotalPrice(Long.valueOf(stockData.getString(2).replace(",", "")));
                        stockHistoryInfo.setOpenPrice(Float.valueOf(stockData.getString(3).replace(",", "")));
                        stockHistoryInfo.setHighestPrice(Float.valueOf(stockData.getString(4).replace(",", "")));
                        stockHistoryInfo.setLowestPrice(Float.valueOf(stockData.getString(5).replace(",", "")));
                        stockHistoryInfo.setClosingPrice(Float.valueOf(stockData.getString(6).replace(",", "")));
                        stockHistoryInfo.setSpread(Float.valueOf(stockData.getString(7).replace("+", "").replace("X", "")));
                        stockHistoryInfo.setVolume(Integer.valueOf(stockData.getString(8).replace(",", "")));
                        stockHistoryInfo.setStock(stock);
                        stockHistoryInfoRepository.save(stockHistoryInfo);
                    }

                    month = String.valueOf(Integer.parseInt(month) + 1);

                    if (month.length() < 2) {
                        month = "0" + month;
                    }

                    if (month.equals("13")) {
                        month = "01";
                        year = String.valueOf(Integer.parseInt(year) + 1);
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

        }
    }
}
