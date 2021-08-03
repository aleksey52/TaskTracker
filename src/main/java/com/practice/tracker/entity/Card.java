package com.practice.tracker.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean done;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "card")
    private List<SubTask> subTasks;

    public Card(String name, boolean done, Task task, List<SubTask> subTasks) {
    	this.name = name;
    	this.done = done;
    	this.task = task;
        this.subTasks = subTasks;
    }

    public Card(String name, Task task, List<SubTask> subTasks) {
    	this.name = name;
    	this.done = false;
        this.task = task;
        this.subTasks = subTasks;
    }
}
