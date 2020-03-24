package com.stock.service.imp;

import com.stock.entity.Dividend;
import com.stock.entity.StockHistoryInfo;
import com.stock.repository.IDividendRepository;
import com.stock.repository.IStockHistoryInfoRepository;
import com.stock.request.AnalysisRequest;
import com.stock.response.AnalysisResponse;
import com.stock.response.KdStrategyResponse;
import com.stock.service.IAnalysisService;
import com.stock.tool.CorrectionTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

@Service
public class AnalysisService implements IAnalysisService {

    @Autowired
    IStockHistoryInfoRepository stockHistoryInfoRepository;

    @Autowired
    IDividendRepository dividendRepository;

    @Override
    public AnalysisResponse dollarCostAveraging(AnalysisRequest request) {
        String stockId = request.getStockId();
        String purchaseDay = request.getPurchaseDay();
        Integer monthlyFunds = request.getMonthlyFunds();

        List<StockHistoryInfo> historyList = stockHistoryInfoRepository.getHistorys(purchaseDay, stockId);
        List<StockHistoryInfo> correctedHistoryList = CorrectionTool.monthlyPurchaseCorrection(historyList, stockId, purchaseDay, stockHistoryInfoRepository);

        Map<String, Float> dataMap = CorrectionTool.quantityAndDividendCorrection(stockId, monthlyFunds, correctedHistoryList, dividendRepository);
        float stockQuantity = dataMap.get("stockQuantity");
        float yearDividend = dataMap.get("yearCashDividend");
        float mod = dataMap.get("mod");


        Float latestPrice = correctedHistoryList.get(correctedHistoryList.size() - 1).getClosingPrice();
        Float total = (float) (stockQuantity * latestPrice + yearDividend);

        float capital = 0.0f;
        for (int j = 0; j < correctedHistoryList.size(); j++) {
            capital = monthlyFunds + capital * (1 + 0.01f / 12);
        }

        Float stockValueFV = total;
        Float totalCahDividend = (float) yearDividend;
        Float investmentFundsPV = (float) (5000 * correctedHistoryList.size() - mod);
        Float investmentFundsFV = capital;
        Float totalReturnFV = total - capital;
        Float roi = (total - capital) / capital;
        Float returnAnnualizedRate = new BigDecimal((Math.pow(roi + 1, 0.1) - 1)).setScale(5, RoundingMode.HALF_UP).floatValue();
        AnalysisResponse response = new AnalysisResponse();

        response.setStockValueFV(stockValueFV);
        response.setStockQuantity((float) stockQuantity);
        response.setTotalCahDividend(totalCahDividend);
        response.setInvestmentFundsPV(investmentFundsPV);
        response.setInvestmentFundsFV(investmentFundsFV);
        response.setTotalReturnFV(totalReturnFV);
        response.setRoi(roi);
        response.setReturnAnnualizedRate(returnAnnualizedRate);
        response.setLatestPrice(latestPrice);

        return response;
    }

    @Override
    public AnalysisResponse valueAveraging(AnalysisRequest request) {
        String stockId = request.getStockId();
        String purchaseDay = request.getPurchaseDay();
        Integer monthlyValue = request.getMonthlyFunds();

        List<StockHistoryInfo> historyList = stockHistoryInfoRepository.getHistorys(purchaseDay, stockId);
        List<StockHistoryInfo> correctedHistoryList = CorrectionTool.monthlyPurchaseCorrection(historyList, stockId, purchaseDay, stockHistoryInfoRepository);


        Map<String, Float> dataMap = CorrectionTool.quantityAndDividendCorrectionForValueAveraging(stockId, monthlyValue, correctedHistoryList, dividendRepository);

        float stockQuantity = dataMap.get("stockQuantity");
        float yearDividend = dataMap.get("yearCashDividend");
//        float mod = dataMap.get("mod");
        float correctionValue = dataMap.get("correctionValue");
        float capital = dataMap.get("capital");


        Float latestPrice = correctedHistoryList.get(correctedHistoryList.size() - 1).getClosingPrice();
        Float total = (float) (stockQuantity * latestPrice + yearDividend + correctionValue);


        Float stockValueFV = total;
        Float totalCahDividend = (float) yearDividend;
//        Float investmentFundsPV = (float)(5000*correctedHistoryList.size());
        Float investmentFundsFV = capital;
        Float totalReturnFV = total - capital;
        Float roi = (total - capital) / capital;
        Float returnAnnualizedRate = new BigDecimal((Math.pow(roi + 1, 0.1) - 1)).setScale(5, RoundingMode.HALF_UP).floatValue();
        AnalysisResponse response = new AnalysisResponse();

        response.setStockValueFV(stockValueFV);
        response.setStockQuantity((float) stockQuantity);
        response.setTotalCahDividend(totalCahDividend);
//        response.setInvestmentFundsPV(investmentFundsPV);
        response.setInvestmentFundsFV(investmentFundsFV);
        response.setTotalReturnFV(totalReturnFV);
        response.setRoi(roi);
        response.setReturnAnnualizedRate(returnAnnualizedRate);
        response.setLatestPrice(latestPrice);

        return response;
    }

    @Override
    public KdStrategyResponse kdStrategy(String stockId, Float purchaseAmount) {
        KdStrategyResponse kdStrategyResponse = new KdStrategyResponse();
        List<StockHistoryInfo> stockHistoryList = stockHistoryInfoRepository.findAll();
        Float cost = 0f;
        Float stockAmount = 0f;
        Float sellAmount = 0f;
        Float transactionTimes = 0f;
        StockHistoryInfo outTest = null;
        for (StockHistoryInfo stockHistoryInfo : stockHistoryList) {
            outTest = stockHistoryInfo;
            if (stockHistoryInfo.getId() > 8 && stockHistoryInfo.getStock().getId().equals("0050")) {
                Float closingPrice = stockHistoryInfo.getClosingPrice();
                Float openPrice = stockHistoryInfo.getOpenPrice();
                Float d = stockHistoryInfo.getD();
                Float k = stockHistoryInfo.getK();
                if (d < 20 && k < 20 && k<d) {
                    sellAmount += stockAmount * openPrice;
                    stockAmount = 0f;
                    transactionTimes++;
                } else if (d > 80 && k > 80) {
                    cost += 5000;
                    stockAmount += 5000 / closingPrice;
                    transactionTimes++;
                }
            }
        }


        Float profit = sellAmount - cost;
        kdStrategyResponse.setCost(cost);
        kdStrategyResponse.setProfit(profit);
        kdStrategyResponse.setTransactionTimes(transactionTimes);
        return kdStrategyResponse;
    }
}
