package com.prasant.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private final List<String> tags = new ArrayList<>();
    private final TaskStatus status = TaskStatus.PENDING;
    private Long id;
    private String title;
    private String description;
    private String image;
    private Long assignedUserId;
    private LocalDateTime deadLine;
    private LocalDateTime createdAt;
}
