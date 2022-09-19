package com.example.stocktrade.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TradeException {
    @JsonProperty("message")
    String message;
}
