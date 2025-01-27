package lk.project.taskhub.dto.request;


import java.time.LocalDateTime;

public class TaskRequestDto {

    private String taskName;

    private String description;

    private LocalDateTime dueDate;


    public TaskRequestDto() {
    }


    public TaskRequestDto(String taskName, String description, LocalDateTime dueDate) {
        this.taskName = taskName;
        this.description = description;
        this.dueDate = dueDate;

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


}
