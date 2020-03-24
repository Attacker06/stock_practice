package com.stock.response;

public class KdStrategyResponse extends BaseResponse {
    Float cost;
    Float profit;
    Float transactionTimes;

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Float getProfit() {
        return profit;
    }

    public void setProfit(Float profit) {
        this.profit = profit;
    }

    public Float getTransactionTimes() {
        return transactionTimes;
    }

    public void setTransactionTimes(Float transactionTimes) {
        this.transactionTimes = transactionTimes;
    }
}
