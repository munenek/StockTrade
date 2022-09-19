package com.example.stocktrade.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TradeRequest {
    @JsonProperty("id")
    Long id;
    @JsonProperty("type")
    String type;
    @JsonProperty("user")
    User user;
    @JsonProperty("symbol")
    String symbol;
    @JsonProperty("shares")
    Integer shares;
    @JsonProperty("price")
    Float price;
    @JsonProperty("timestamp")
    String timestamp;

}
