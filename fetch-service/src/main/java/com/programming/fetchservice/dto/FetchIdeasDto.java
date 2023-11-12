package com.programming.fetchservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchIdeasDto {
    private Long id;
    private String categoryCode;
    // private BigDecimal price;
    private Integer quantity;
}