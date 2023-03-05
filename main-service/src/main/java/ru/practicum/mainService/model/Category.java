package ru.practicum.mainService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "categories", schema = "public")
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

}
