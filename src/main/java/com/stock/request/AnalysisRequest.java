package com.stock.request;

public class AnalysisRequest {

    private String stockId;

    private Integer monthlyFunds;

    private String purchaseDay;

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public Integer getMonthlyFunds() {
        return monthlyFunds;
    }

    public void setMonthlyFunds(Integer monthlyFunds) {
        this.monthlyFunds = monthlyFunds;
    }

    public String getPurchaseDay() {
        return purchaseDay;
    }

    public void setPurchaseDay(String purchaseDay) {
        this.purchaseDay = purchaseDay;
    }
}
