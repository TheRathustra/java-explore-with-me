package ru.practicum.mainService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "users", schema = "public")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

}
