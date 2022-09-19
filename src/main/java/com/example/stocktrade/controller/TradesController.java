package com.example.stocktrade.controller;

import com.example.stocktrade.model.Trade;
import com.example.stocktrade.service.TradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/trades")
public class TradesController {
    @Autowired
    private TradesService tradeService;
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    @PostMapping
    private ResponseEntity<String> addTrade(@RequestBody Trade trade) {
        return tradeService.addTrade(trade);
    }

    @GetMapping(value="/{id}")
    private ResponseEntity<Trade> fetchTradeById(@PathVariable(value="id") Long id){
        return tradeService.fetchTradeById(id);

    }
    @GetMapping
    private ResponseEntity<Iterable<Trade>> fetchAllTrades(){
        return tradeService.fetchAllTrades();

    }

    @GetMapping(value="/users/{UserId}")
    private ResponseEntity<List<Trade>> getTradeByUserId(@PathVariable(value="UserId") Long UserId ) {
        return tradeService.fetchTradeByUserId(UserId);

    }

    @GetMapping(value="/stocks/{stockSymbol}")
    private ResponseEntity<List<Trade>> getTradeBySymbolAndType(@PathVariable(value = "stockSymbol") String symbol,@RequestParam(value = "type",required = false)String tradetype,
                                                         @RequestParam(value = "start")
                                                         String optionalStartDate,
                                                         @RequestParam(value = "end")String optionalEndDate ,Pageable pageable){
        return tradeService.fetchTradeByStockSymbolAndTradeType(symbol,tradetype,optionalStartDate,optionalEndDate,  pageable);
    }



}
