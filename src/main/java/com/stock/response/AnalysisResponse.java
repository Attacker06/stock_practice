package com.stock.response;

public class AnalysisResponse extends BaseResponse {

    Float stockValueFV;
    Float stockValuePV;
    Float totalCahDividend;
    Float investmentFundsPV;
    Float InvestmentFundsFV;
    Float stockQuantity;
    Float latestPrice;
    Float totalReturnFV;
    Float totalReturnPV;
    Float roi;
    Float ReturnAnnualizedRate;

    public Float getStockValueFV() {
        return stockValueFV;
    }

    public void setStockValueFV(Float stockValueFV) {
        this.stockValueFV = stockValueFV;
    }

    public Float getStockValuePV() {
        return stockValuePV;
    }

    public void setStockValuePV(Float stockValuePV) {
        this.stockValuePV = stockValuePV;
    }

    public Float getTotalCahDividend() {
        return totalCahDividend;
    }

    public void setTotalCahDividend(Float totalCahDividend) {
        this.totalCahDividend = totalCahDividend;
    }

    public Float getInvestmentFundsPV() {
        return investmentFundsPV;
    }

    public void setInvestmentFundsPV(Float investmentFundsPV) {
        this.investmentFundsPV = investmentFundsPV;
    }

    public Float getInvestmentFundsFV() {
        return InvestmentFundsFV;
    }

    public void setInvestmentFundsFV(Float investmentFundsFV) {
        InvestmentFundsFV = investmentFundsFV;
    }

    public Float getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Float stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Float getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(Float latestPrice) {
        this.latestPrice = latestPrice;
    }

    public Float getTotalReturnFV() {
        return totalReturnFV;
    }

    public void setTotalReturnFV(Float totalReturnFV) {
        this.totalReturnFV = totalReturnFV;
    }

    public Float getTotalReturnPV() {
        return totalReturnPV;
    }

    public void setTotalReturnPV(Float totalReturnPV) {
        this.totalReturnPV = totalReturnPV;
    }

    public Float getRoi() {
        return roi;
    }

    public void setRoi(Float roi) {
        this.roi = roi;
    }

    public Float getReturnAnnualizedRate() {
        return ReturnAnnualizedRate;
    }

    public void setReturnAnnualizedRate(Float returnAnnualizedRate) {
        ReturnAnnualizedRate = returnAnnualizedRate;
    }
}
