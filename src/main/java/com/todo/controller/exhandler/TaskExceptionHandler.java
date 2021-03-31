package com.todo.controller.exhandler;

import com.todo.exception.task.TaskException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TaskExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TaskException.class)
    protected ResponseEntity<String> handleNoTaskException(TaskException ex) {
        return new ResponseEntity<>("error: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
