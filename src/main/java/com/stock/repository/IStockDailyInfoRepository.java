package com.stock.repository;

import com.stock.entity.Stock;
import com.stock.entity.StockDailyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStockDailyInfoRepository extends JpaRepository<StockDailyInfo,Integer> {
}
