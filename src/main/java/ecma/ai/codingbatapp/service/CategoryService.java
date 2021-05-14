package ecma.ai.codingbatapp.service;

import ecma.ai.codingbatapp.entity.Category;
import ecma.ai.codingbatapp.entity.ProgrammingLanguage;
import ecma.ai.codingbatapp.payload.ApiResponse;
import ecma.ai.codingbatapp.payload.CategoryDto;
import ecma.ai.codingbatapp.repository.CategoryRepository;
import ecma.ai.codingbatapp.repository.PLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    PLRepository plRepository;

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }
    public Category getOne(Integer id){
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()) return null;
        return byId.get();
    }
    public ApiResponse delete(Integer id){
        categoryRepository.deleteById(id);
        return new ApiResponse("deleted successfully",true);
    }
    public ApiResponse add(CategoryDto categoryDto){
        if (categoryRepository.existsByName(categoryDto.getName())) return new ApiResponse("Already exist",false);
        Category category=new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setStarNumber(categoryDto.getStarNumber());

        List<ProgrammingLanguage> languages=new ArrayList<>();
        List<Integer> languageList = categoryDto.getLanguageList();
        for (Integer integer : languageList) {
            Optional<ProgrammingLanguage> byId = plRepository.findById(integer);
            if (!byId.isPresent()) return new ApiResponse("Not found",false);
            ProgrammingLanguage programmingLanguage = byId.get();
            languages.add(programmingLanguage);
        }

        category.setLanguageList(languages);
        categoryRepository.save(category);
        return new ApiResponse("Saved successfully",true);

    }
    public ApiResponse edit(Integer id,CategoryDto categoryDto){
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()) return new ApiResponse("Not found",false);
        if (plRepository.existsByNameAndIdNot(categoryDto.getName(), id)) return new ApiResponse("Already exist",false);
        Category category = byId.get();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setStarNumber(categoryDto.getStarNumber());

        List<ProgrammingLanguage> languages=new ArrayList<>();
        List<Integer> languageList = categoryDto.getLanguageList();
        for (Integer integer : languageList) {
            Optional<ProgrammingLanguage> byId1 = plRepository.findById(integer);
            if (!byId1.isPresent()) return new ApiResponse("Not found",false);
            ProgrammingLanguage programmingLanguage = byId1.get();
            languages.add(programmingLanguage);
        }

        category.setLanguageList(languages);
        categoryRepository.save(category);
        return new ApiResponse("Edited successfully",true);
    }
}
