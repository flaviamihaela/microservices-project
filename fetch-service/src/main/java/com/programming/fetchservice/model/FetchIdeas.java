package com.programming.fetchservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "t_fetch_ideas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FetchIdeas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryCode;
    private Integer quantity;
}