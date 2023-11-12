package com.programming.fetchservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class InventoryResponse {
    private String categoryCode;
    private boolean isInStock;
}
