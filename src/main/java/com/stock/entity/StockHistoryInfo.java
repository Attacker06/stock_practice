package com.stock.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "stockHistoryInfo")
public class StockHistoryInfo implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator="generatorName")
    @GenericGenerator(name = "generatorName", strategy = "native")
    private Integer id;

    @Column
    private Date date;

    @Column(length = 25)
    private Integer volumeStock;

    @Column(length = 50)
    private Long totalPrice;

    @Column(length = 10)
    private Float openPrice;

    @Column(length = 10)
    private Float highestPrice;

    @Column(length = 10)
    private Float lowestPrice;

    @Column(length = 10)
    private Float closingPrice;

    @Column(length = 10)
    private Float spread;

    @Column(length = 25)
    private Integer volume;

    @Column
    private Float rsv;

    @Column
    private Float k;

    @Column
    private Float d;

    @ManyToOne
    @JoinColumn(name = "stock_id",referencedColumnName = "id")
    private Stock stock;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getVolumeStock() {
        return volumeStock;
    }

    public void setVolumeStock(Integer volumeStock) {
        this.volumeStock = volumeStock;
    }

    public Float getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(Float openPrice) {
        this.openPrice = openPrice;
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

    public Float getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(Float closingPrice) {
        this.closingPrice = closingPrice;
    }

    public Float getSpread() {
        return spread;
    }

    public void setSpread(Float spread) {
        this.spread = spread;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Float getRsv() {
        return rsv;
    }

    public void setRsv(Float rsv) {
        this.rsv = rsv;
    }

    public Float getK() {
        return k;
    }

    public void setK(Float k) {
        this.k = k;
    }

    public Float getD() {
        return d;
    }

    public void setD(Float d) {
        this.d = d;
    }
}
