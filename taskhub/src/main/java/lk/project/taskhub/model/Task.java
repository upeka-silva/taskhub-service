package lk.project.taskhub.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String taskName;

    private String description;

    @Column(nullable = false)
    private LocalDateTime dueDate;

    @Column(nullable = false)
    private boolean isCompleted = false;

    @Column(nullable = false)
    private boolean isTodayTask =false;


    public Task() {
    }

    public Task( String taskName, String description,
                LocalDateTime dueDate, boolean isCompleted, boolean isTodayTask) {
        this.taskName = taskName;
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;
        this.isTodayTask = isTodayTask;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isTodayTask() {
        return isTodayTask;
    }

    public void setTodayTask(boolean todayTask) {
        isTodayTask = todayTask;
    }
}
