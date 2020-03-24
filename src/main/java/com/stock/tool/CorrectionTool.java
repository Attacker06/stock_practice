package com.stock.tool;

import com.stock.entity.Dividend;
import com.stock.entity.StockHistoryInfo;
import com.stock.repository.IDividendRepository;
import com.stock.repository.IStockHistoryInfoRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CorrectionTool {


    public static List<StockHistoryInfo> monthlyPurchaseCorrection(List<StockHistoryInfo> originList, String stockId, String purchaseDay, IStockHistoryInfoRepository stockHistoryInfoRepository){
        List<StockHistoryInfo> cloneList = new ArrayList<>(originList);

        int i = 1;

        for(StockHistoryInfo historyInfo : originList){
            String year = String.valueOf(historyInfo.getDate().toLocalDate().getYear());
            String month = String.valueOf(historyInfo.getDate().toLocalDate().getMonth().getValue());
            int monthValue = historyInfo.getDate().toLocalDate().getMonth().getValue();
            while (i!=monthValue){
                int newDay = Integer.valueOf(purchaseDay);
                StockHistoryInfo newHistoryInfo=null;
                while (newHistoryInfo==null){
                    if (i==12&&monthValue==1){
                        newHistoryInfo=stockHistoryInfoRepository.getHistory(String.valueOf(Integer.valueOf(year)-1),String.valueOf(i),String.valueOf(newDay),stockId);
                    }else {
                        newHistoryInfo=stockHistoryInfoRepository.getHistory(year,String.valueOf(i),String.valueOf(newDay),stockId);
                    }
                    newDay++;
                }
                cloneList.add(cloneList.indexOf(historyInfo),newHistoryInfo);
                i++;
                if (i>12){
                    i=1;
                }
            }
            i++;
            if (i>12){
                i=1;
            }
        }


        return cloneList;
    }

    public static Map<String,Float> quantityAndDividendCorrection(String stockId,Integer monthlyFunds, List<StockHistoryInfo> correctedHistoryList, IDividendRepository dividendRepository){

        Integer year = correctedHistoryList.get(0).getDate().toLocalDate().getYear();
        float yearDividend =0.0f;

        Integer stockQuantity=0;
        float mod=0;
        for (StockHistoryInfo historyInfo : correctedHistoryList){
            Float price = historyInfo.getOpenPrice();
            mod= (int) (mod+monthlyFunds%price);
            int purchaseQuantity = (int) (monthlyFunds/historyInfo.getOpenPrice());
            stockQuantity=stockQuantity+purchaseQuantity;
            int innerYear=historyInfo.getDate().toLocalDate().getYear();
            if (innerYear != year){
                try {
                    Dividend dividend = dividendRepository.selectDividend(year - 1911, stockId);
                    yearDividend = yearDividend + (float) (stockQuantity*(dividend.getCashDividend()));
                    Double stockDividend = dividend.getStockDividend();
                    if (stockDividend!=0){
                        stockQuantity=stockQuantity+(int)(stockQuantity*stockDividend);
                    }
                }catch (Exception e){

                }finally {
                    year=innerYear;
                }
            }
        }

        Map<String,Float> dataMap = new HashMap<>();
        dataMap.put("stockQuantity",Float.valueOf(stockQuantity));
        dataMap.put("yearCashDividend",yearDividend);
        dataMap.put("mod",mod);

        return dataMap;
    }

    public static Map<String,Float> quantityAndDividendCorrectionForValueAveraging(String stockId,Integer monthlyFunds, List<StockHistoryInfo> correctedHistoryList, IDividendRepository dividendRepository){

        Integer year = correctedHistoryList.get(0).getDate().toLocalDate().getYear();
        float yearDividend =0.0f;

        float stockQuantity=0;
        float stockValue=monthlyFunds;
        float mod=0;
        float correctionValue=0;
        float capital=0;
        for (StockHistoryInfo historyInfo : correctedHistoryList){
            Float price = historyInfo.getOpenPrice();
            float purchaseQuantity = Math.round((stockValue-stockQuantity*price)/price);
            capital = capital + (stockValue-stockQuantity*price);
            stockQuantity=stockQuantity+purchaseQuantity;
            if (purchaseQuantity<0){
                correctionValue=correctionValue+Math.abs(purchaseQuantity*price);
            }
            mod= (int) (mod+monthlyFunds%price);
            int innerYear=historyInfo.getDate().toLocalDate().getYear();
            if (innerYear != year){
                try {
                    correctionValue=correctionValue*1.01f;
                    Dividend dividend = dividendRepository.selectDividend(year - 1911, stockId);
                    yearDividend = yearDividend + (float) (stockQuantity*(dividend.getCashDividend()));
                    Double stockDividend = dividend.getStockDividend();
                    if (stockDividend!=0){
                        stockQuantity=stockQuantity+(int)(stockQuantity*stockDividend);
                    }
                }catch (Exception e){

                }finally {
                    year=innerYear;
                }
            }
            stockValue=stockValue+monthlyFunds;
        }

        Map<String,Float> dataMap = new HashMap<>();
        dataMap.put("stockQuantity",Float.valueOf(stockQuantity));
        dataMap.put("yearCashDividend",yearDividend);
        dataMap.put("mod",mod);
        dataMap.put("correctionValue",correctionValue);
        dataMap.put("capital",capital);
        return dataMap;
    }

}
