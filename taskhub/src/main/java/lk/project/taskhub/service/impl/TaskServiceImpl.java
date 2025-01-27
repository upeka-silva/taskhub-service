package lk.project.taskhub.service.impl;

import lk.project.taskhub.Exceptions.TaskNotFoundException;
import lk.project.taskhub.dto.request.TaskRequestDto;
import lk.project.taskhub.model.Task;
import lk.project.taskhub.model.User;
import lk.project.taskhub.repository.TaskRepository;
import lk.project.taskhub.repository.UserRepository;
import lk.project.taskhub.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {


    private final TaskRepository taskRepository;
    private final UserRepository userRepository;


    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Task creatTask(TaskRequestDto dto) {
        if(dto.getTaskName()!=null && dto.getDueDate() == null){
            Task task = new Task(
                    dto.getTaskName(),
                    dto.getDescription()
                    ,dto.getDueDate()
                    ,false,calculateIsTodayTask(dto.getDueDate())
            );
            return taskRepository.save(task);

        }

        throw new RuntimeException("Invalid task details!");
    }

    @Override
    public Task updateTask(Long id) {
        return taskRepository.findById(id).orElseThrow(()->new TaskNotFoundException("Task not found!"));
    }

    @Override
    public List<Task> getAllTasks(String userName) {
        return List.of();
    }

    @Override
    public void creatTask(Long id) {
        Optional<Task> selectedTask = taskRepository.findById(id);
        if(selectedTask.isPresent()){
            taskRepository.delete(selectedTask.get());
        }
      throw new TaskNotFoundException("Cannot find the task!");

    }


    private boolean calculateIsTodayTask(LocalDateTime dueDate) {
        LocalDate today = LocalDate.now();
        return dueDate.toLocalDate().equals(today);
    }

}
