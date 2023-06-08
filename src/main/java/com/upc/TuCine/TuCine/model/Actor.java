package com.upc.TuCine.TuCine.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Actor")
public class Actor {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name", length = 100, nullable = false)
    private String first_name;
    @Column(name = "last_name", length = 100, nullable = false)
    private String last_name;
    @Column(name = "biography", length = 200, nullable = false)
    private String biography;
    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @ManyToMany(mappedBy = "actors")
    private List<Film> films;
}
