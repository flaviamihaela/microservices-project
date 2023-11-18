package com.programming.fetchservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchIdeasDto {
    private Long id;
    private String categoryCode;
    private Integer quantity;
}