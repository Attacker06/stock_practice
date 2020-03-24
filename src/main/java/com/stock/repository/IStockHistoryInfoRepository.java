package com.stock.repository;

import com.stock.entity.StockHistoryInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IStockHistoryInfoRepository extends JpaRepository<StockHistoryInfo,Integer> {

    @Query(value = "SELECT * FROM stock_history_info WHERE DAY(date)=?1 AND stock_id=?2",nativeQuery = true)
    List<StockHistoryInfo> getHistorys(String day,String stockId);

    @Query(value = "SELECT * FROM stock_history_info WHERE YEAR(date)=?1 AND MONTH (date)=?2 AND DAY(date)=?3 AND stock_id=?4",nativeQuery = true)
    StockHistoryInfo getHistory(String year,String month,String day,String stockId);

    @Query(value = "SELECT MAX(closing_price) FROM (SELECT * FROM stock_history_info WHERE date BETWEEN ?1 AND ?2) AS temp",nativeQuery = true)
    Float getTenDaysHighestPrice(Date startDate,Date endDate);

    @Query(value = "SELECT MIN(closing_price) FROM (SELECT * FROM stock_history_info WHERE date BETWEEN ?1 AND ?2) AS temp",nativeQuery = true)
    Float getTenDaysLowestPrice(Date startDate,Date endDate);

    @Query(value = "SELECT * FROM stock_history_info WHERE date=?1 AND stock_id=?2",nativeQuery = true)
    StockHistoryInfo getHistoryByDate(Date date,String stockId);

    @Query(value = "SELECT * FROM stock_history_info WHERE id=?1 AND stock_id=?2",nativeQuery = true)
    StockHistoryInfo getHistoryById(Integer id,String stockId);
}
