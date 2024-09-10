package com.prasant.service;

import com.prasant.modal.TaskDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "TASK-SERVICE")
public interface TaskService {

    @GetMapping("/api/tasks/{id}")
    TaskDto getTaskById(@PathVariable Long id,
                        @RequestHeader("Authorization") String jwt) throws Exception;

    @PutMapping("/api/tasks/{id}/complete")
    TaskDto completeTask(@PathVariable Long id) throws Exception;
}
