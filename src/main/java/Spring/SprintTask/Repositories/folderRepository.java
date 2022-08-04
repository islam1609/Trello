package Spring.SprintTask.Repositories;

import Spring.SprintTask.Model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface folderRepository extends JpaRepository<Folder,Long> {
}
