package ecma.ai.codingbatapp.service;

import ecma.ai.codingbatapp.entity.ProgrammingLanguage;
import ecma.ai.codingbatapp.payload.ApiResponse;
import ecma.ai.codingbatapp.repository.PLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PLService {
    @Autowired
    PLRepository plRepository;

    public List<ProgrammingLanguage> getAll(){
        return plRepository.findAll();
    }
    public ProgrammingLanguage getOne(Integer id){
        Optional<ProgrammingLanguage> byId = plRepository.findById(id);
        if (byId.isPresent()) return null;
        return byId.get();
    }
    public ApiResponse delete(Integer id){
        plRepository.deleteById(id);
        return new ApiResponse("deleted successfully",true);
    }
    public ApiResponse add(ProgrammingLanguage pl){
        if (plRepository.existsByName(pl.getName())) return new ApiResponse("Already exist",false);
        ProgrammingLanguage programmingLanguage=new ProgrammingLanguage();
        programmingLanguage.setName(pl.getName());
        plRepository.save(programmingLanguage);
        return new ApiResponse("Saved successfully",true);
    }
    public ApiResponse edit(Integer id,ProgrammingLanguage pl){
        Optional<ProgrammingLanguage> byId = plRepository.findById(id);
        if (byId.isPresent()) return new ApiResponse("Not found",false);
        if (plRepository.existsByNameAndIdNot(pl.getName(), id)) return new ApiResponse("Already exist",false);
        ProgrammingLanguage programmingLanguage = byId.get();
        programmingLanguage.setName(pl.getName());
        plRepository.save(programmingLanguage);
        return new ApiResponse("Edited successfully",true);
    }
}
