package com.programming.fetchservice.service;

import com.programming.fetchservice.dto.FetchIdeasDto;
import com.programming.fetchservice.dto.FetchRequest;
import com.programming.fetchservice.model.Fetch;
import com.programming.fetchservice.model.FetchIdeas;
import com.programming.fetchservice.repository.FetchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class FetchService {

    private final FetchRepository fetchRepository;

    public void placeFetch(FetchRequest fetchRequest) {
        Fetch fetch = new Fetch();
        fetch.setFetchNumber(UUID.randomUUID().toString());

        List<FetchIdeas> fetchIdeas = fetchRequest.getFetchIdeasDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        fetch.setFetchIdeasList(fetchIdeas);

        fetchRepository.save(fetch);
    }

    private FetchIdeas mapToDto(FetchIdeasDto fetchIdeasDto) {
        FetchIdeas fetchIdeas = new FetchIdeas();
        fetchIdeas.setPrice(fetchIdeasDto.getPrice());
        fetchIdeas.setQuantity(fetchIdeasDto.getQuantity());
        fetchIdeas.setSkuCode(fetchIdeasDto.getSkuCode());
        return fetchIdeas;
    }
}