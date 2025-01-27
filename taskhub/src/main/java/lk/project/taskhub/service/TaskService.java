package lk.project.taskhub.service;

import lk.project.taskhub.dto.request.TaskRequestDto;
import lk.project.taskhub.model.Task;

import java.util.List;

public interface TaskService {

     Task creatTask(TaskRequestDto dto);

     Task updateTask(Long id );

     List<Task> getAllTasks(String userName);

     void creatTask(Long id);






}
