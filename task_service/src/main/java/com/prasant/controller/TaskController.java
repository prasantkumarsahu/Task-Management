package com.prasant.controller;

import com.prasant.modal.Task;
import com.prasant.modal.TaskStatus;
import com.prasant.modal.UserDto;
import com.prasant.service.TaskService;
import com.prasant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task, @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto user = userService.getUserProfile(jwt);

        Task createdTask = taskService.createTask(task, user.getRole());

        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id,
                                            @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto user = userService.getUserProfile(jwt);

        Task task = taskService.getTaskById(id);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Task>> getAssignedUser(@RequestParam(required = false) TaskStatus status,
                                                      @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto user = userService.getUserProfile(jwt);

        List<Task> task = taskService.assignedUsers(user.getId(), status);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam(required = false) TaskStatus status,
                                                  @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto user = userService.getUserProfile(jwt);

        List<Task> task = taskService.getAllTasks(status);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/{id}/user/{userId}/assigned")
    public ResponseEntity<Task> assignTaskToUser(@PathVariable Long id,
                                                 @PathVariable Long userId,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto user = userService.getUserProfile(jwt);

        Task task = taskService.assignToUser(userId, id);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id,
                                           @RequestBody Task task,
                                           @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto user = userService.getUserProfile(jwt);

        Task updatedtask = taskService.updateTask(id, task, user.getId());

        return new ResponseEntity<>(updatedtask, HttpStatus.OK);
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Task> completeTask(@PathVariable Long id) throws Exception {

        Task updatedtask = taskService.completeTask(id);

        return new ResponseEntity<>(updatedtask, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) throws Exception {

        taskService.deleteTask(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
