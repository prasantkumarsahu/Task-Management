package com.prasant.service;

import com.prasant.modal.Task;
import com.prasant.modal.TaskStatus;

import java.util.List;

public interface TaskService {

    Task createTask(Task task, String requesterRole) throws Exception;

    Task getTaskById(Long id) throws Exception;

    List<Task> getAllTasks(TaskStatus status);

    Task updateTask(Long id, Task updatedTask, Long userId) throws Exception;

    void deleteTask(Long id) throws Exception;

    Task assignToUser(Long userId, Long taskId) throws Exception;

    List<Task> assignedUsers(Long userId, TaskStatus status) throws Exception;

    Task completeTask(Long taskId) throws Exception;
}
