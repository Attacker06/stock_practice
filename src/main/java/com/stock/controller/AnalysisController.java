package com.stock.controller;

import com.stock.request.AnalysisRequest;
import com.stock.response.BaseResponse;
import com.stock.service.IAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analysis")
public class AnalysisController {

    @Autowired
    IAnalysisService analysisService;

    @PostMapping("/dollarCostAveraging")
    public BaseResponse dollarCostAveraging(@RequestBody AnalysisRequest request){
        return analysisService.dollarCostAveraging(request);
    }

    @PostMapping("/valueAveraging")
    public BaseResponse valueAveraging(@RequestBody AnalysisRequest request){
        return analysisService.valueAveraging(request);
    }

    @GetMapping("/kdStrategy")
    public BaseResponse kdStrategy(@RequestParam String stockId, @RequestParam Float purchaseAmount){
        return analysisService.kdStrategy(stockId,purchaseAmount);
    }
}
