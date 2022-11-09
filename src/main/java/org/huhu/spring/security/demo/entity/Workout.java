package org.huhu.spring.security.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity(name = "t_workout")
public class Workout {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private String user;

    private LocalDateTime start;

    private LocalDateTime end;

    private Integer difficulty;

}
