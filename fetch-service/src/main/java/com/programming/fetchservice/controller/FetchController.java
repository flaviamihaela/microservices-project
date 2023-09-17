package com.programming.fetchservice.controller;

import com.programming.fetchservice.dto.FetchRequest;
import com.programming.fetchservice.service.FetchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fetch")
@RequiredArgsConstructor
public class FetchController {

    private final FetchService fetchService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeFetch(@RequestBody FetchRequest fetchRequest) {
        fetchService.placeFetch(fetchRequest);
        return "Out of fresh ideas?! Let's see if the Programmer's Bucket List can help";
    }
}