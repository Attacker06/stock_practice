package com.stock.tool;

import com.stock.entity.Stock;
import com.stock.entity.StockDailyInfo;
import com.stock.repository.IStockDailyInfoRepository;
import com.stock.repository.IStockRepository;
import org.json.JSONArray;
import org.json.JSONObject;

public class StockCrawler implements Runnable {

    private IStockRepository iStockRepository;

    private IStockDailyInfoRepository StockDailyInfoRepository;

    private Stock stock ;

    String stockCode;

    public StockCrawler(IStockRepository iStockRepository,IStockDailyInfoRepository StockDailyInfoRepository, Stock stock) {
        this.iStockRepository = iStockRepository;
        this.StockDailyInfoRepository = StockDailyInfoRepository;
        this.stock = stock;
    }

    @Override
    public void run() {


        stockCode= this.stock.getId();
//        int amount = 1;
        String url;

        while (true) {
            try {
                Thread.sleep(5000);
                url="https://mis.twse.com.tw/stock/api/getStockInfo.jsp?ex_ch=tse_"+stockCode+".tw&json=1&_=1571102031702";
                RequestSender requestSender = new RequestSender();
                String responseData = requestSender.sendRequest(url, "GET");

                JSONObject jsonObject = new JSONObject(responseData);
                JSONArray msgArray = new JSONArray(jsonObject.get("msgArray").toString());
                JSONObject data = msgArray.getJSONObject(0);

                JSONObject timeObject = new JSONObject(jsonObject.get("queryTime").toString());

                String key = "tse" + stockCode + ".tw";
                String id = data.getString("c");
                String name = data.getString("n");

                Float currentPrice = data.getFloat("z");
                Float highestPrice = data.getFloat("h");
                Float lowestPrice = data.getFloat("l");
                Integer tVolume = data.getInt("tv");
                Integer volume = data.getInt("v");
                Float yesterdayPrice = data.getFloat("y");
                Float openingPrice = data.getFloat("o");
                String date = timeObject.getString("sysDate");
                String time = timeObject.getString("sysTime");

                StockDailyInfo stockDailyInfo = new StockDailyInfo();
                stock.setId(id);
                stockDailyInfo.setCurrentPrice(currentPrice);
                stockDailyInfo.setHighestPrice(highestPrice);
                stockDailyInfo.setLowestPrice(lowestPrice);
                stockDailyInfo.setOpeningPrice(openingPrice);
                stockDailyInfo.setVolume(volume);
                stockDailyInfo.settVolume(tVolume);
                stockDailyInfo.setYesterdayPrice(yesterdayPrice);
                stockDailyInfo.setDate(date);
                stockDailyInfo.setTime(time);
                stockDailyInfo.setStock(stock);

                StockDailyInfoRepository.save(stockDailyInfo);
//                if (id!=null&&name!=null){
//                    stock.setKey(key);
//                    stock.setId(id);
//                    stock.setName(name);
//                    iStockRepository.save(stock);
//                }


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
//                amount++;
//                Integer countingData = Integer.parseInt(stockCode);
//                countingData++;
//                stockCode = String.valueOf(countingData);
//
//                if (stockCode.length()<2){
//                    stockCode="000"+stockCode;
//                }else if (stockCode.length()<3){
//                    stockCode="00"+stockCode;
//                }else if (stockCode.length()<4){
//                    stockCode="0"+stockCode;
//                }

            }
        }
    }

}
