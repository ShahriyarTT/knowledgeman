package com.virtusbellatoris.knowledgeman.controller;

import com.virtusbellatoris.knowledgeman.DTO.PasswordChangeDTO;
import com.virtusbellatoris.knowledgeman.DTO.UserDTO;
import com.virtusbellatoris.knowledgeman.model.User;
import com.virtusbellatoris.knowledgeman.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PutMapping("/{id}/profile")
    public User updateProfile(@PathVariable Integer id,
                              @RequestBody UserDTO updatedUserDTO) {
        return userService.updateProfile(id, updatedUserDTO);
    }

    @PutMapping("/{id}/password")
    public User changePassword(@PathVariable Integer id,
                               @RequestBody PasswordChangeDTO passwordChangeDTO) {
        return userService.changePassword(id, passwordChangeDTO);
    }

    @PutMapping("/{id}/promote")
    public User promoteToAdmin(@PathVariable Integer id){
        return userService.promoteToAdmin(id);
    }

    @PutMapping("/{id}/demote")
    public User demoteToRegular(@PathVariable Integer id){
        return userService.demoteToRegular(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
    }


}
