package com.example.stocktrade.controller;

import com.example.stocktrade.dto.response.PriceResponse;
import com.example.stocktrade.service.TradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/stocks")
public class StocksController {
    @Autowired
    private TradesService tradesService;
    @GetMapping(value= "/{symbol}/price")
    private ResponseEntity<PriceResponse> getHighestAndLowestPrice(@PathVariable(value="symbol") String symbol,
                                                                   @RequestParam(value = "start", required = false)
    String optionalStartDate, @RequestParam(value = "end", required = false)String optionalEndDate , Pageable pageable){
        return tradesService.getHighestAndLowestPrice(symbol,optionalStartDate,optionalEndDate,pageable);

    }
}
