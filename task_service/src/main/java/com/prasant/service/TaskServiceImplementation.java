package com.prasant.service;

import com.prasant.modal.Task;
import com.prasant.modal.TaskStatus;
import com.prasant.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskServiceImplementation implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task createTask(Task task, String requesterRole) throws Exception {
        if (!requesterRole.equals("ROLE_ADMIN")) {
            throw new Exception("Only admin can create task!");
        }

        task.setStatus(TaskStatus.PENDING);
        task.setCreatedAt(LocalDateTime.now());

        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) throws Exception {
        return taskRepository.findById(id).orElseThrow(() -> new Exception(("Task not found with id: " + id)));
    }

    @Override
    public List<Task> getAllTasks(TaskStatus status) {
        List<Task> allTasks = taskRepository.findAll();

        return allTasks.stream().filter(
                task -> status == null || task.getStatus().name().equalsIgnoreCase(status.toString())
        ).toList();
    }

    @Override
    public Task updateTask(Long id, Task updatedTask, Long userId) throws Exception {
        Task existingTask = getTaskById(id);

        if (updatedTask.getTitle() != null) {
            existingTask.setTitle(updatedTask.getTitle());
        }
        if (updatedTask.getDescription() != null) {
            existingTask.setDescription(updatedTask.getDescription());
        }
        if (updatedTask.getImage() != null) {
            existingTask.setImage(updatedTask.getImage());
        }
        if (updatedTask.getAssignedUserId() != null) {
            existingTask.setAssignedUserId(updatedTask.getAssignedUserId());
        }
        if (updatedTask.getTags() != null) {
            existingTask.setTags(updatedTask.getTags());
        }
        if (updatedTask.getDeadLine() != null) {
            existingTask.setDeadLine(updatedTask.getDeadLine());
        }
        if (updatedTask.getCreatedAt() != null) {
            existingTask.setCreatedAt(updatedTask.getCreatedAt());
        }
        if (updatedTask.getStatus() != null) {
            existingTask.setStatus(updatedTask.getStatus());
        }

        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTask(Long id) throws Exception {

        getTaskById(id);

        taskRepository.deleteById(id);
    }

    @Override
    public Task assignToUser(Long userId, Long taskId) throws Exception {
        Task task = getTaskById(taskId);
        task.setAssignedUserId(userId);
        task.setStatus(TaskStatus.ASSIGNED);

        return taskRepository.save(task);
    }

    @Override
    public List<Task> assignedUsers(Long userId, TaskStatus status) throws Exception {
        List<Task> allTasks = taskRepository.findByAssignedUserId(userId);
        return allTasks.stream().filter(
                task -> status == null || task.getStatus().name().equalsIgnoreCase(status.toString())
        ).toList();
    }

    @Override
    public Task completeTask(Long taskId) throws Exception {
        Task task = getTaskById(taskId);

        task.setStatus(TaskStatus.DONE);

        return taskRepository.save(task);
    }
}
