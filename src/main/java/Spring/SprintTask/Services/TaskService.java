package Spring.SprintTask.Services;

import Spring.SprintTask.Model.Folder;
import Spring.SprintTask.Model.Task;
import Spring.SprintTask.Model.TaskCategories;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TaskService {
    void addFolder(Folder folder);
    List<Folder> getAllFolders();
    List<Task> getAllTasksByFolderId(Long folderId);
    Folder getFolder(Long id);
    Task getTask(Long id);
    void addTask(Task task, Long id);
    void deleteTask(Long id);
    void updateTask(Task task, Long id);
    List<TaskCategories> gettAllCategories();
    void addCategoryToFolder(Long folderId, Long categoryId);
    void removeCategoryFromFolder(Long folderId, Long categoryId);
}
