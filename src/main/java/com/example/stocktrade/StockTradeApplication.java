package com.example.stocktrade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class StockTradeApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockTradeApplication.class, args);
    }
    @PostConstruct
    private void setTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("EST"));
    }

}
