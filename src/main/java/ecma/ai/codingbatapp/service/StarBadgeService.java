package ecma.ai.codingbatapp.service;

import ecma.ai.codingbatapp.entity.ProgrammingLanguage;
import ecma.ai.codingbatapp.entity.StarBadge;
import ecma.ai.codingbatapp.payload.ApiResponse;
import ecma.ai.codingbatapp.payload.StarBadgeDto;
import ecma.ai.codingbatapp.repository.PLRepository;
import ecma.ai.codingbatapp.repository.StarBadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StarBadgeService {
    @Autowired
    StarBadgeRepository starBadgeRepository;
    @Autowired
    PLRepository plRepository;
    public List<StarBadge> getAll(){
        return starBadgeRepository.findAll();
    }
    public StarBadge getOne(Integer id){
        Optional<StarBadge> byId = starBadgeRepository.findById(id);
        if (byId.isPresent()) return null;
        return byId.get();
    }
    public ApiResponse delete(Integer id){
        starBadgeRepository.deleteById(id);
        return new ApiResponse("deleted successfully",true);
    }
    public ApiResponse add(StarBadgeDto starBadgeDto){
        StarBadge starBadge1=new StarBadge();
        starBadge1.setValue(starBadgeDto.getValue());
        starBadge1.setLanguage(plRepository.getOne(starBadgeDto.getPlId()));
        starBadgeRepository.save(starBadge1);
        return new ApiResponse("Saved successfully",true);
    }
    public ApiResponse edit(Integer id,StarBadgeDto starBadgeDto){
        Optional<StarBadge> byId = starBadgeRepository.findById(id);
        if (byId.isPresent()) return new ApiResponse("Not found",false);
        StarBadge starBadge1 = byId.get();
        starBadge1.setValue(starBadgeDto.getValue());
        starBadge1.setLanguage(plRepository.getOne(starBadgeDto.getPlId()));
        starBadgeRepository.save(starBadge1);
        return new ApiResponse("Edited successfully",true);
    }
}
