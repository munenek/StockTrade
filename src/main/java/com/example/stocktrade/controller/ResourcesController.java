package com.example.stocktrade.controller;

import com.example.stocktrade.service.TradesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/erase")
public class ResourcesController {
    @Autowired
    private TradesService tradeService;

    @DeleteMapping
    private ResponseEntity<String> deleteAllTrades() {
        tradeService.deleteAllTrades();
        return new ResponseEntity<>(HttpStatus.OK);

    }

    
}
