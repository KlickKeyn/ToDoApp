package com.todo.controller.exhandler;

import com.todo.exception.tasklist.TaskListException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TaskListExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TaskListException.class)
    protected ResponseEntity<String> handleNoTaskListsException(TaskListException ex) {
        return new ResponseEntity<>("error " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
