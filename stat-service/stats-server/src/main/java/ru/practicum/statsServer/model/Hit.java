package ru.practicum.statsServer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hits", schema = "public")
@NoArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
public class Hit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "app", length = 255, nullable = false)
    private String app;

    @Column(name = "uri", length = 255, nullable = false)
    private String uri;

    @Column(name = "ip", length = 15, nullable = false)
    private String ip;

    @Column(name = "created")
    private LocalDateTime timestamp;

    @Transient
    Long hits;

}
