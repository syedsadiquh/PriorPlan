package com.syedsadiquh.priorplan.dao;

import com.syedsadiquh.priorplan.models.Task;
import com.syedsadiquh.priorplan.models.User;
import com.syedsadiquh.priorplan.models.enums.TaskPriority;
import com.syedsadiquh.priorplan.models.enums.TaskStatus;
import com.syedsadiquh.priorplan.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class TaskDAO {

    private final TaskRepository taskRepository;

    public TaskDAO(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public boolean insertTask(User user, String title, String desc, String priority, String dueDate) {
        try {
            LocalDate date = LocalDate.parse(dueDate.replace(" ", "-"), 
                DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
            LocalDateTime dateTime = date.atStartOfDay(); // Convert to LocalDateTime at start of day
            
            var task = Task.builder()
                    .user(user)
                    .title(title)
                    .description(desc)
                    .priority(TaskPriority.valueOf(priority))
                    .dueDate(dateTime)
                    .status(TaskStatus.NOT_STARTED)
                    .build();
            taskRepository.save(task);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}