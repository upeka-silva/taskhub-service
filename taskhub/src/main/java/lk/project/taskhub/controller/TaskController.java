package lk.project.taskhub.controller;
import lk.project.taskhub.dto.request.TaskRequestDto;
import lk.project.taskhub.model.Task;
import lk.project.taskhub.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/add")
    public ResponseEntity<Task> createTask(@RequestBody TaskRequestDto dto) {
        Task createdTask = taskService.createTask(dto);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping()
    public ResponseEntity<List<Task>> getTasksByDate(
            @RequestParam(name = "date") String date) {
        LocalDate filterDate = LocalDate.parse(date);
        List<Task> tasks = taskService.findByDate(filterDate);
        return ResponseEntity.ok(tasks);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id,@RequestBody TaskRequestDto taskRequestDto) {
        Task updatedTask = taskService.updateTask(id,taskRequestDto);
        return ResponseEntity.ok(updatedTask);
    }

   @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }

}
