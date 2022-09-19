package com.example.stocktrade.service;

import com.example.stocktrade.Repository.TradeRepository;
import com.example.stocktrade.Repository.UserRepository;
import com.example.stocktrade.dto.response.PriceResponse;
import com.example.stocktrade.exceptions.TradeException;
import com.example.stocktrade.model.Trade;
import com.example.stocktrade.model.User;
import com.example.stocktrade.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.example.stocktrade.utils.Dates.DATE_PATTERN;


@Service
@Transactional
@Slf4j
public class TradeServiceImpl implements TradesService {
    @Autowired
    TradeRepository tradeRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void deleteAllTrades() {
        tradeRepository.deleteAll();
    }

    @Override
    public ResponseEntity<String> addTrade(Trade trade) {
        Optional<Trade> fetchedTrade = tradeRepository.findById(trade.getId());
        Optional<User> user = userRepository.findById(trade.getUser().getId());
        if (fetchedTrade.isEmpty()) {

            if (user.isEmpty()) {
                userRepository.save(trade.getUser());
            }
            tradeRepository.save(trade);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Trade> fetchTradeById(Long id) {
        if (tradeRepository.findById(id).isPresent()) {

            return new ResponseEntity<>(tradeRepository.findById(id).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<Trade>> fetchTradeByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            Optional<List<Trade>> trade = tradeRepository.findByUserOrderByIdAsc(user.get());
            return trade.map(trades -> new ResponseEntity<>(trades, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<Trade>> fetchTradeByStockSymbolAndTradeType(String symbol, String tradeType, String startDate, String endDate, Pageable pageable) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN, Locale.ENGLISH);
        LocalDate startDateConv = LocalDate.parse(startDate, formatter);
        LocalDate endDateConv = LocalDate.parse(endDate, formatter);
        log.info("the start timestamp is " + Timestamp.valueOf(startDateConv.atStartOfDay()));
        List<Trade> trades = tradeRepository.findAll();
        List<String> symbols = new ArrayList<>();
        List<String> types = new ArrayList<>();
        for (Trade trade : trades) {
            String symb = trade.getSymbol();
            symbols.add(symb);
            String typ = trade.getType();
            types.add(typ);
        }
        if (types.contains(tradeType) && symbols.contains(symbol)) {


            Optional<List<Trade>> trade = tradeRepository.
                    findTradeBySymbolAndTypeAndTimestampBetween(symbol, tradeType, Timestamp.valueOf(startDateConv.atStartOfDay()), Timestamp.valueOf(endDateConv.atTime(23, 59, 59)));

            return new ResponseEntity<>(trade.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    @Override
    public ResponseEntity<PriceResponse> getHighestAndLowestPrice(String symbol, String startDate, String endDate, Pageable pageable) {
        List<Trade> trades;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN, Locale.ENGLISH);
        LocalDate startDateConv = LocalDate.parse(startDate, formatter);
        LocalDate endDateConv = LocalDate.parse(endDate, formatter);
        List<Trade> allTrades = tradeRepository.findAll();
        log.info("the start timestamp is " + Timestamp.valueOf(startDateConv.atStartOfDay()));

        List<String> symbols = new ArrayList<>();
        for (Trade trade : allTrades) {
            String symb = trade.getSymbol();
            symbols.add(symb);
        }
        if (symbols.contains(symbol)) {
            trades = tradeRepository.
                    findTradeBySymbolAndTimestampBetween(symbol, Timestamp.valueOf(startDateConv.atStartOfDay()), Timestamp.valueOf(endDateConv.atTime(23, 59, 59))).get();
            List<Float> prices = new ArrayList<>();
            for (Trade trade : trades) {
                prices.add(trade.getPrice());
            }
            Collections.sort(prices);
            log.info("the prices for this date range are " + prices);
            if (prices.size() > 0) {
                PriceResponse response = new PriceResponse(symbol, Collections.max(prices), Collections.min(prices));

                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {


                return new ResponseEntity(new TradeException(Constants.noTrades), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Iterable<Trade>> fetchAllTrades() {
        Iterable<Trade> trade = tradeRepository.findAll();
        return new ResponseEntity<>(trade, HttpStatus.OK);
    }
}
