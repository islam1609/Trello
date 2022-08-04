package Spring.SprintTask.Repositories;

import Spring.SprintTask.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface taskRepository extends JpaRepository<Task,Long> {
    List<Task> findTaskByFolderId(Long id);
}
