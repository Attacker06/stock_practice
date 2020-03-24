package com.stock.tool;

import com.stock.entity.Stock;
import com.stock.repository.IDividendRepository;
import com.stock.repository.IStockDailyInfoRepository;
import com.stock.repository.IStockHistoryInfoRepository;
import com.stock.repository.IStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Component
public class StockRunner implements CommandLineRunner {

    @Autowired
    private IStockRepository stockRepository;

    @Autowired
    private IStockDailyInfoRepository StockDailyInfoRepository;

    @Autowired
    private IStockHistoryInfoRepository stockHistoryInfoRepository;

    @Autowired
    private IDividendRepository dividendRepository;

    @Autowired
    IStockHistoryInfoRepository historyRepository;

    @Autowired
    private ExecutorService executorService;



    @Override
    public void run(String... args) throws Exception {
//        List<Stock> all = stockRepository.findAll();
//        List<String> stockIdList = new ArrayList<>();
//        for (Stock stock : all){
//            stockIdList.add(stock.getId());
//        }
//
//        Stock stock;
//        for (String stockCode : args){
//            stock = new Stock();
//            stock.setId(stockCode);
//            executorService.execute(new StockCrawler(stockRepository,StockDailyInfoRepository,stock));
//        }

//        Stock stock = new Stock();
//        stock.setId("2888");
//        executorService.execute(new StcokHistoryCrawler(stock,stockHistoryInfoRepository));

//        executorService.execute(new AnalyseTool(historyRepository,dividendRepository));

//        for (String stockId : stockIdList){
//            executorService.execute(new DividendCrawler(dividendRepository,stockId));
//        }

//        executorService.execute(new StcokHistoryCrawler(stockHistoryInfoRepository,stockIdList));

//        executorService.execute(new RsvCalculator(stockHistoryInfoRepository));

    }

    @PreDestroy
    public void destroy(){
        executorService.shutdown();
    }
}
