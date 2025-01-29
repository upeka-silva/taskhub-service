package lk.project.taskhub.repository;

import lk.project.taskhub.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query(value = "SELECT * FROM Tasks t WHERE DATE(t.created_at) = :date", nativeQuery = true)
    List<Task> findByCreatedAtDate(@Param("date") LocalDate date);


}
