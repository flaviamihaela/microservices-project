package com.programming.fetchservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_fetch")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fetch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fetchNumber;
    @OneToMany(cascade = CascadeType.ALL)
    private List<FetchIdeas> fetchIdeasList;
}