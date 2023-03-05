package ru.practicum.mainService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "requests", schema = "public")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "requester_id")
    User requester;

    @ManyToOne
    @JoinColumn(name = "event_id")
    Event event;

    @Column(name = "created")
    LocalDateTime created;

    @Column(name = "status")
    Status status;

}
