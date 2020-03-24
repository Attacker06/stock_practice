package com.stock.service;

import com.stock.request.AnalysisRequest;
import com.stock.response.AnalysisResponse;
import com.stock.response.KdStrategyResponse;

public interface IAnalysisService {

    AnalysisResponse dollarCostAveraging(AnalysisRequest request);

    AnalysisResponse valueAveraging(AnalysisRequest request);

    KdStrategyResponse kdStrategy(String stockId,Float purchaseAmount);
}
