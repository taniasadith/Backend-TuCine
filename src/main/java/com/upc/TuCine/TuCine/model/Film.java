package com.upc.TuCine.TuCine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Film")
public class Film {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title", length = 255, nullable = false)
    private String title;
    @Column(name = "year", nullable = false)
    private Integer year;
    @Column(name = "synopsis", length = 5000, nullable = false)
    private String synopsis;
    @Column(name = "poster", length = 5000, nullable = false)
    private String poster;
    @Column(name = "trailer", length = 5000, nullable = false)
    private String trailer;
    @Column(name = "duration", nullable = false)
    private Integer duration;

    @OneToOne
    @JoinColumn(name = "ContentRating_id", nullable = false, foreignKey = @ForeignKey(name = "FK_CONTENTRATING_ID"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ContentRating ContentRating_id;

    @ManyToMany
    private List<Actor> actors;
}
