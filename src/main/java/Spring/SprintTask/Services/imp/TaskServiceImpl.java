package Spring.SprintTask.Services.imp;

import Spring.SprintTask.Model.Folder;
import Spring.SprintTask.Model.Task;
import Spring.SprintTask.Model.TaskCategories;
import Spring.SprintTask.Repositories.folderRepository;
import Spring.SprintTask.Repositories.taskCategoryRepository;
import Spring.SprintTask.Repositories.taskRepository;
import Spring.SprintTask.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TaskServiceImpl  implements TaskService {

    @Autowired
    private taskRepository taskRepository;

    @Autowired
    private folderRepository folderRepository;

    @Autowired
    private taskCategoryRepository taskCategoryRepository;

    @Override
    public void addFolder(Folder folder) {
        folderRepository.save(folder);
    }

    @Override
    public List<Folder> getAllFolders() {
        return folderRepository.findAll();
    }

    @Override
    public List<Task> getAllTasksByFolderId(Long folderId) {
        return taskRepository.findTaskByFolderId(folderId);
    }

    @Override
    public Folder getFolder(Long id) {
        return  folderRepository.findById(id).orElse(null);
    }

    @Override
    public Task getTask(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public void addTask(Task task, Long folderId) {
        if(folderRepository.existsById(folderId)){
            task.setStatus(0);
            task.setFolder(folderRepository.findById(folderId).orElse(null));
            taskRepository.save(task);
        }
    }

    @Override
    public void deleteTask(Long id) {
        if(taskRepository.existsById(id)){
            taskRepository.deleteById(id);
        }
    }

    @Override
    public void updateTask(Task task, Long id) {
        if(taskRepository.existsById(id)){
           Task newTask = getTask(id);
           newTask.setName(task.getName());
           newTask.setDescription(task.getDescription());
           newTask.setStatus(task.getStatus());

           taskRepository.save(newTask);
        }
    }

    @Override
    public List<TaskCategories> gettAllCategories() {
        return taskCategoryRepository.findAll();
    }

    @Override
    public void addCategoryToFolder(Long folderId, Long categoryId) {
        if (folderRepository.existsById(folderId)) {
            Folder folder = folderRepository.findById(folderId).orElse(null);
            List<TaskCategories> categories = folder.getCategories();
            if(categories==null){
                    categories = new ArrayList<>();
                }
            TaskCategories category = taskCategoryRepository.findById(categoryId).orElse(null);
            categories.add(category);
            folder.setCategories(categories);
            addFolder(folder);
            }
        }

    @Override
    public void removeCategoryFromFolder(Long folderId, Long categoryId) {
        if (folderRepository.existsById(folderId)) {
            Folder folder = folderRepository.findById(folderId).orElse(null);
            List<TaskCategories> categories = folder.getCategories();
            TaskCategories category = taskCategoryRepository.findById(categoryId).orElse(null);
            categories.remove(category);
            folder.setCategories(categories);
            addFolder(folder);
        }
      }
    }


