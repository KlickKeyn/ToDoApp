package com.todo.repository;

import com.todo.model.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskListRepository extends JpaRepository<TaskList, Long> {

    TaskList findByTitle(String title);

}
