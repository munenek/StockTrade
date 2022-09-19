package com.example.stocktrade.Repository;

import com.example.stocktrade.model.Trade;
import com.example.stocktrade.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface TradeRepository extends JpaRepository<Trade, Long> {
    Optional<List<Trade>> findByUserOrderByIdAsc(User user);
    Optional<List<Trade>> findTradeBySymbolAndTypeAndTimestampBetween(String symbol, String type, Timestamp start, Timestamp end);
    Optional<List<Trade>> findTradeBySymbolAndTimestampBetween(String symbol, Timestamp start, Timestamp end);

}
