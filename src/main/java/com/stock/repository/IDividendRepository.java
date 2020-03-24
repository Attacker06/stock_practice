package com.stock.repository;

import com.stock.entity.Dividend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IDividendRepository extends JpaRepository<Dividend,Integer> {

    @Query("SELECT d from Dividend d inner join d.stock s where d.year=?1 and s.id=?2")
    Dividend selectDividend(Integer year,String stockId);
}
