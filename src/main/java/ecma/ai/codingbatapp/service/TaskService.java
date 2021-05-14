package ecma.ai.codingbatapp.service;

import ecma.ai.codingbatapp.entity.Task;
import ecma.ai.codingbatapp.entity.User;
import ecma.ai.codingbatapp.payload.ApiResponse;
import ecma.ai.codingbatapp.payload.TaskDto;
import ecma.ai.codingbatapp.payload.UserDto;
import ecma.ai.codingbatapp.repository.CategoryRepository;
import ecma.ai.codingbatapp.repository.TaskRepository;
import ecma.ai.codingbatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;

    public List<Task> getAll(){
        return taskRepository.findAll();
    }
    public Task getOne(Integer id){
        Optional<Task> byId = taskRepository.findById(id);
        if (byId.isPresent()) return null;
        return byId.get();
    }
    public ApiResponse delete(Integer id){
        taskRepository.deleteById(id);
        return new ApiResponse("deleted successfully",true);
    }
    public ApiResponse add(TaskDto taskDto){
        if (taskRepository.existsByNameAndCategoryId(taskDto.getName(), taskDto.getCategoryId())) return new ApiResponse("Already exist",false);
        if (!categoryRepository.findById(taskDto.getCategoryId()).isPresent()) return new ApiResponse("Category not found",false);
        if (!userRepository.findById(taskDto.getUserId()).isPresent()) return new ApiResponse("User not found",false);
        Task task=new Task();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setUser(userRepository.getOne(taskDto.getUserId()));
        task.setCategory(categoryRepository.getOne(taskDto.getCategoryId()));
        taskRepository.save(task);
        return new ApiResponse("Saved successfully",true);
    }
    public ApiResponse edit(Integer id,TaskDto taskDto){
        Optional<Task> byId =taskRepository.findById(id);
        if (byId.isPresent()) return new ApiResponse("Not found",false);
        if (taskRepository.existsByNameAndCategoryIdAndIdNot(taskDto.getName(), taskDto.getCategoryId(), id)) return new ApiResponse("Already exist",false);
        if (!categoryRepository.findById(taskDto.getCategoryId()).isPresent()) return new ApiResponse("Category not found",false);
        if (!userRepository.findById(taskDto.getUserId()).isPresent()) return new ApiResponse("User not found",false);
        Task task = byId.get();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setUser(userRepository.getOne(taskDto.getUserId()));
        task.setCategory(categoryRepository.getOne(taskDto.getCategoryId()));
        taskRepository.save(task);
        return new ApiResponse("Edited successfully",true);
    }
}
