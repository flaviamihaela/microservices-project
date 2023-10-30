package com.programming.fetchservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchRequest {
    private List<FetchIdeasDto> fetchIdeasDtoList;
}