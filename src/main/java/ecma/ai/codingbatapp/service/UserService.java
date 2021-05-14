package ecma.ai.codingbatapp.service;

import ecma.ai.codingbatapp.entity.User;
import ecma.ai.codingbatapp.payload.ApiResponse;
import ecma.ai.codingbatapp.payload.UserDto;
import ecma.ai.codingbatapp.repository.StarBadgeRepository;
import ecma.ai.codingbatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    StarBadgeRepository starBadgeRepository;

    public List<User> getAll(){
        return userRepository.findAll();
    }
    public User getOne(Integer id){
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) return null;
        return byId.get();
    }
    public ApiResponse delete(Integer id){
        userRepository.deleteById(id);
        return new ApiResponse("deleted successfully",true);
    }
    public ApiResponse add(UserDto userDto){
        if (userRepository.existsByEmail(userDto.getEmail())) return new ApiResponse("Already exist",false);
        User user=new User();
        user.setEmail(userDto.getEmail());
        user.setFullName(userDto.getFullName());
        user.setPassword(userDto.getPassword());
        user.setStarBadge(starBadgeRepository.getOne(userDto.getStarBadgeId()));
        userRepository.save(user);
        return new ApiResponse("Saved successfully",true);
    }
    public ApiResponse edit(Integer id,UserDto userDto){
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) return new ApiResponse("Not found",false);
        if (userRepository.existsByEmailAndIdNot(userDto.getEmail(), id)) return new ApiResponse("Already exist",false);
        User user = byId.get();
        user.setEmail(userDto.getEmail());
        user.setFullName(userDto.getFullName());
        user.setPassword(userDto.getPassword());
        user.setStarBadge(starBadgeRepository.getOne(userDto.getStarBadgeId()));
        userRepository.save(user);
        return new ApiResponse("Edited successfully",true);
    }
}
