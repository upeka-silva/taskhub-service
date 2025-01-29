package lk.project.taskhub.service;

import lk.project.taskhub.dto.request.TaskRequestDto;
import lk.project.taskhub.model.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {

     Task createTask(TaskRequestDto dto);

     Task updateTask(Long id,TaskRequestDto taskRequestDto);

     List<Task> getAllTasks();

     List<Task> findByDate(LocalDate filterDate);

     void deleteTaskById(Long id);
}
