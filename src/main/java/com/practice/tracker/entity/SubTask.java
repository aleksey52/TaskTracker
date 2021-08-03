package com.practice.tracker.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class SubTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean done;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    public SubTask(String name, boolean done, Card card) {
        this.name = name;
        this.done = done;
        this.card = card;
    }

    public SubTask(String name, Card card) {
        this.name = name;
        this.done = false;
        this.card = card;
    }
}
