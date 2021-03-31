package com.todo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "task_list")
@Data
@NoArgsConstructor
public class TaskList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id")
    private List<Task> tasks;
}
