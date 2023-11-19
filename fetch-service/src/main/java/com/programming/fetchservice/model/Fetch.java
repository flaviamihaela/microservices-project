// Declare package for class
package com.programming.fetchservice.model;

// Import classes and annotations
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

// Marks class as a Jpa entity (persistent domain object)
@Entity
// Specifies the table in db with which the enity is mapped 
@Table(name = "t_fetch")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fetch {
    @Id // marks the below field as a primary key 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fetchNumber;
    @OneToMany(cascade = CascadeType.ALL)
    private List<FetchIdeas> fetchIdeasList;
}