package com.stock.tool;


import com.stock.entity.Dividend;
import com.stock.entity.Stock;
import com.stock.repository.IDividendRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class DividendCrawler implements Runnable {

    private IDividendRepository dividendRepository;

    private String stockId;

    public DividendCrawler(IDividendRepository dividendRepository, String stockId) {
        this.dividendRepository = dividendRepository;
        this.stockId = stockId;
    }

    @Override
    public void run() {

        Document doc=null;
        try {
            doc = Jsoup.connect("https://tw.stock.yahoo.com/d/s/dividend_"+stockId+".html").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements tables = doc.select("table");
        Elements datas = tables.get(3).select("tr+tr>td");

        Map<String, List<Double>> dataMap = new HashMap<>();

        String mapKey=null;
        for (Element data : datas){
            String stockData = data.text();
            if (!stockData.contains(".")){
                mapKey=stockData;
                dataMap.put(stockData,new ArrayList<Double>());
            }else{
                dataMap.get(mapKey).add(Double.valueOf(stockData));
            }
        }

        Set<String> years = dataMap.keySet();

        for (String year : years){
            List<Double> dividends = dataMap.get(year);
            Stock stock = new Stock();
            stock.setId(stockId);
            Dividend dividend = new Dividend();
            dividend.setYear(Integer.valueOf(year));
            dividend.setCashDividend(dividends.get(0));
            dividend.setEarningStockDividend(dividends.get(1));
            dividend.setCapitalStockDividend(dividends.get(2));
            dividend.setStockDividend(dividends.get(3));
            dividend.setTotalDividend(dividends.get(4));
            dividend.setStock(stock);
            dividendRepository.save(dividend);
        }

        System.out.println();
        System.out.println();
    }
}
