package com.example.stocktrade.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User {
    @JsonProperty("id")
    Long id;
    @JsonProperty("name")
    String name;
}
