package Spring.SprintTask.Controllers;

import Spring.SprintTask.Model.Folder;
import Spring.SprintTask.Model.Task;
import Spring.SprintTask.Repositories.folderRepository;
import Spring.SprintTask.Repositories.taskCategoryRepository;
import Spring.SprintTask.Repositories.taskRepository;
import Spring.SprintTask.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private folderRepository folderRepository;

    @Autowired
    private taskRepository taskRepository;

    @Autowired
    private taskCategoryRepository taskCategoryRepository;


    @GetMapping(value = "/")
    public String indexPage(Model model){
        model.addAttribute("folders",taskService.getAllFolders());
        return "indexPage";
    }

    @PostMapping(value = "/addFolder")
    public String addFolder(@ModelAttribute(name = "folderObj") Folder folderObj){
        taskService.addFolder(folderObj);
        return "redirect:/";
    }


    @GetMapping(value = "/details/folders/{id}")
    public String detailsFolders(@PathVariable(name = "id") Long folderId,
                                  Model model) {
        model.addAttribute("folder", taskService.getFolder(folderId));
        model.addAttribute("tasks", taskService.getAllTasksByFolderId(folderId));
        model.addAttribute("categories", taskService.gettAllCategories());
        return "detailsFolder";
    }

    @GetMapping(value = "/detailsOfTask/{id}")
    public String detailsOfTask(@PathVariable(name = "id") Long id,
                                Model model){
        model.addAttribute("task",taskService.getTask(id));
        model.addAttribute("folder",taskService.getTask(id).getFolder());
        return "detailsTask";
    }

    @PostMapping(value = "/newTask")
    public String newTask(Model model,
                          @ModelAttribute(name = "newTask") Task newTask,
                          @RequestParam(name = "folderId") Long folderId){
        taskService.addTask(newTask,folderId);
        return "redirect:/details/folders/" + folderId;
    }

    @PostMapping(value = "/deleteTask")
    public String deleteTask(@RequestParam(name = "taskId") Long taskId,
                             @RequestParam(name = "folderId") Long folderId){
        taskService.deleteTask(taskId);
        return "redirect:/details/folders/" + folderId;
    }

    @PostMapping(value = "/updateTask")
    public String updateTask(@ModelAttribute(name = "taskObj") Task taskObj,
                             @RequestParam(name ="taskId") Long taskId){
        taskService.updateTask(taskObj,taskId);
        return "redirect:/details/folders/" + taskService.getTask(taskId).getFolder().getId();
    }

    @PostMapping(value = "/addCategoryToFolder")
    public String addCategory(@RequestParam(name = "folderId") Long folderId,
                              @RequestParam(name ="categoryId") Long categoryId){
        taskService.addCategoryToFolder(folderId,categoryId);
        return "redirect:/details/folders/" + folderId;
    }

    @PostMapping(value = "/removeCategory")
    public String removeCategory(@RequestParam(name = "folderId") Long folderId,
                              @RequestParam(name ="catId") Long categoryId){
        taskService.removeCategoryFromFolder(folderId,categoryId);
        return "redirect:/details/folders/" + folderId;
    }

}
