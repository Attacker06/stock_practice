package com.stock.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stockDailyInfo")
public class StockDailyInfo implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator="generatorName")
    @GenericGenerator(name = "generatorName", strategy = "native")
    private Integer id;

    @Column
    private Float currentPrice;

    @Column
    private Float highestPrice;

    @Column
    private Float lowestPrice;

    @Column
    private Float yesterdayPrice;

    @Column
    private Float openingPrice;

    @Column
    private Integer volume;

    @Column
    private Integer tVolume;

    @Column
    private String date;

    @Column
    private String time;

    @ManyToOne
    @JoinColumn(name = "stock_id",referencedColumnName = "id")
    private Stock stock;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Float currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Float getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(Float highestPrice) {
        this.highestPrice = highestPrice;
    }

    public Float getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(Float lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public Float getYesterdayPrice() {
        return yesterdayPrice;
    }

    public void setYesterdayPrice(Float yesterdayPrice) {
        this.yesterdayPrice = yesterdayPrice;
    }

    public Float getOpeningPrice() {
        return openingPrice;
    }

    public void setOpeningPrice(Float openingPrice) {
        this.openingPrice = openingPrice;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer gettVolume() {
        return tVolume;
    }

    public void settVolume(Integer tVolume) {
        this.tVolume = tVolume;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
