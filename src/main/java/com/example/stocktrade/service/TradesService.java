package com.example.stocktrade.service;

import com.example.stocktrade.dto.response.PriceResponse;
import com.example.stocktrade.model.Trade;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.List;

public interface TradesService {
     void deleteAllTrades();
     ResponseEntity<String> addTrade(Trade trade);

    ResponseEntity<Trade> fetchTradeById(Long id);

    ResponseEntity<List<Trade>> fetchTradeByUserId(Long userId);

    ResponseEntity<List<Trade>> fetchTradeByStockSymbolAndTradeType(String symbol, String tradeType, String startDate, String endDate, Pageable pageable);

    ResponseEntity<PriceResponse> getHighestAndLowestPrice(String symbol, String optionalStartDate, String optionalEndDate, Pageable pageable);

    ResponseEntity<Iterable<Trade>> fetchAllTrades();
}
