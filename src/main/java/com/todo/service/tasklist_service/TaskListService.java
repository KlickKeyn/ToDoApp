package com.todo.service.tasklist_service;

import com.todo.model.Task;
import com.todo.model.TaskList;

import java.util.List;

public interface TaskListService {

    List<TaskList> getAllTaskList();

    TaskList findByTitle(String title);

    void addTaskToListByTitle(String titleList, Task task);

    void removeTask(String taskTitle);

    void addTaskList(TaskList taskList);

    void removeTaskList(String title);
}
