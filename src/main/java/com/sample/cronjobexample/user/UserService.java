package com.sample.cronjobexample.user;

import com.sample.cronjobexample.user.dto.UserDTO;
import com.sample.cronjobexample.user.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getUsers() {
        List<User> userEntities = getUserEntities();
        return userModelMapper(userEntities);
    }

    public List<User> getUserEntities() {
        return userRepository.findAll();
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = userDTOMapper(userDTO);
        User savedUser = userRepository.save(user);
        return userModelMapper(List.of(savedUser)).get(0);
    }

    public void createTestUsers(int numberOfUsersToCreate) {
        List<User> users = IntStream.range(1, numberOfUsersToCreate + 1)
                .mapToObj(i -> User.builder().name("TestUser" + i).age(30).build()).toList();
        userRepository.saveAll(users);
    }

    private List<UserDTO> userModelMapper(List<User> users) {
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setName(user.getName());
            userDTO.setAge(user.getAge());
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    private User userDTOMapper(UserDTO userDTO) {
        return User.builder().name(userDTO.getName()).age(userDTO.getAge()).build();
    }
}
