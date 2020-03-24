package com.stock.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dividend")
public class Dividend implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator="generatorName")
    @GenericGenerator(name = "generatorName", strategy = "native")
    private Integer id;

    @Column(length = 6)
    private Integer year;

    @Column(length = 10)
    private Double cashDividend;

    @Column(length = 10)
    private Double earningStockDividend;

    @Column(length = 10)
    private Double capitalStockDividend;

    @Column(length = 10)
    private Double stockDividend;

    @Column(length = 10)
    private Double totalDividend;

    @ManyToOne
    @JoinColumn(name = "stock_id",referencedColumnName ="id")
    private Stock stock;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getCashDividend() {
        return cashDividend;
    }

    public void setCashDividend(Double cashDividend) {
        this.cashDividend = cashDividend;
    }

    public Double getEarningStockDividend() {
        return earningStockDividend;
    }

    public void setEarningStockDividend(Double earningStockDividend) {
        this.earningStockDividend = earningStockDividend;
    }

    public Double getCapitalStockDividend() {
        return capitalStockDividend;
    }

    public void setCapitalStockDividend(Double capitalStockDividend) {
        this.capitalStockDividend = capitalStockDividend;
    }

    public Double getStockDividend() {
        return stockDividend;
    }

    public void setStockDividend(Double stockDividend) {
        this.stockDividend = stockDividend;
    }

    public Double getTotalDividend() {
        return totalDividend;
    }

    public void setTotalDividend(Double totalDividend) {
        this.totalDividend = totalDividend;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
