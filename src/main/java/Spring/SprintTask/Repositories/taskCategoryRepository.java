package Spring.SprintTask.Repositories;


import Spring.SprintTask.Model.TaskCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface taskCategoryRepository extends JpaRepository<TaskCategories,Long> {
}
