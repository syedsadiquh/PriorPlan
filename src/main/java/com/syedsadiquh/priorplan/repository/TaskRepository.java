package com.syedsadiquh.priorplan.repository;

import com.syedsadiquh.priorplan.models.Task;
import com.syedsadiquh.priorplan.models.User;
import com.syedsadiquh.priorplan.models.enums.TaskStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    public List<Task> findTaskByUser(User user);

    public List<Task> findTaskByUserAndStatus(User user, TaskStatus status);

    public List<Task> findAllTaskByUserAndDueDateAndStatus(User user, LocalDateTime dueDate, TaskStatus status);

    @Modifying
    @Transactional
    @Query("update Task t set t.status = :newStatus where t.user = :user and t.taskId = :taskId")
    public boolean updateTaskStatus(User user, long taskId, TaskStatus newStatus);


}
