package com.example.stocktrade.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class PriceResponse {
    @JsonProperty("symbol")
    String symbol;
    @JsonProperty("highest")
    float highest;
    @JsonProperty("lowest")
    float lowest;

}
