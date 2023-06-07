package com.upc.TuCine.TuCine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Person")
public class Person {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;
    @Column(name="first_name", length = 80, nullable = false)
    private String first_name;
    @Column(name="last_name", length = 80, nullable = false)
    private String last_name;
    @Column(name="birthdate", nullable = false)
    private LocalDate birthdate;
    @Column(name="phone", length = 9, nullable = false)
    private String phone;
    @Column(name="email", length = 80, nullable = false)
    private String email;
    @Column(name="password", length = 15, nullable = false)
    private String password;
    @Column(name="number_dni", length = 8, nullable = false)
    private String number_dni;

    @ManyToOne
    @JoinColumn(name = "Gender_id",nullable = false, foreignKey = @ForeignKey(name = "FK_GENDER_ID"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Gender Gender_id;

    @ManyToOne
    @JoinColumn(name = "TypeUser_id",nullable = false, foreignKey = @ForeignKey(name = "FK_TYPEUSER_ID"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private TypeUser TypeUser_id;

    //Falta la función que valida que el correo no se repita
}
