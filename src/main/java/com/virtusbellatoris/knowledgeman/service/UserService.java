package com.virtusbellatoris.knowledgeman.service;

import com.virtusbellatoris.knowledgeman.DTO.LoginDTO;
import com.virtusbellatoris.knowledgeman.DTO.PasswordChangeDTO;
import com.virtusbellatoris.knowledgeman.DTO.UserDTO;
import com.virtusbellatoris.knowledgeman.model.User;
import com.virtusbellatoris.knowledgeman.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.virtusbellatoris.knowledgeman.security.JWTService;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class UserService implements UserDetailsService {

    // Inject UserRepository and PasswordEncoder (BCrypt)
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    // Constructor
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // GET
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("This user does not exist."));
    }

    // SAVE
    public User createUser(UserDTO userDTO) {

        if(userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new RuntimeException("A user with this email already exists.");
        }

        User user = new User(
                userDTO.getName(),
                userDTO.getSurname(),
                userDTO.getGender(),
                userDTO.getEmail(),
                userDTO.getBirthDate(),
                userDTO.getCountryOfOrigin(),
                "unknown"
        );

        // password hashing with BCrypt: setPasswordHash(bcrypt.hash(rawPassword))
        user.setHashedPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }

    // UPDATE - Update User Profile
    public User updateProfile(Integer id, UserDTO updatedUserDTO) {
        // Get currently logged in user's email from JWT
        String loggedInEmail = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        // Load the user being modified
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found."));

        // Check ownership — or allow admin
        String existingUserEmail = existingUser.getEmail();
        boolean isAdmin = SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!loggedInEmail.equals(existingUserEmail) && !isAdmin) {
            throw new RuntimeException("You are not allowed to modify this user.");
        }

        //---
        /*
        User existingUser = userRepository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("This user does not exist."));

         */

        if(!passwordEncoder.matches(updatedUserDTO.getPassword(), existingUser.getHashedPassword())){
            throw new RuntimeException( "Type correct password to save changes.");
        }

        existingUser.setName(updatedUserDTO.getName());
        existingUser.setSurname(updatedUserDTO.getSurname());
        existingUser.setGender(updatedUserDTO.getGender());
        existingUser.setCountryOfOrigin(updatedUserDTO.getCountryOfOrigin());
        existingUser.setBirthDate(updatedUserDTO.getBirthDate());
        existingUser.setEmail(updatedUserDTO.getEmail());

        return userRepository.save(existingUser);
        // add later: 3 wrong typing logs out the user.
        // handle userType separately with your admin authorization logic.

    }

    // UPDATE - Change Password
    public User changePassword(Integer id, PasswordChangeDTO passwordChangeDTO) {
        // Get currently logged in user's email from JWT
        String loggedInEmail = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        // Load the user being modified
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found."));

        // Check ownership — or allow admin
        String existingUserEmail = existingUser.getEmail();
        boolean isAdmin = SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!loggedInEmail.equals(existingUserEmail) && !isAdmin) {
            throw new RuntimeException("You are not allowed to modify this user.");
        }

        //---
        /*
        User user = userRepository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("This user does not exist."));

         */

        if(!passwordEncoder.matches(passwordChangeDTO.getCurrentPassword(), existingUser.getHashedPassword())){
            throw new RuntimeException( "Current password is incorrect.");
        }

        if(passwordEncoder.matches(passwordChangeDTO.getNewPassword(), existingUser.getHashedPassword())){
            throw new RuntimeException( "New password must be different from your current password.");
        }

        if(!passwordChangeDTO.getNewPassword().equals(passwordChangeDTO.getConfirmNewPassword())){
            throw new RuntimeException( "Passwords do not match.");
        }

        existingUser.setHashedPassword(passwordEncoder.encode(passwordChangeDTO.getNewPassword()));
        return userRepository.save(existingUser);
    }

    /*
    validate later:
    new password isn't empty
    minimum length
    perhaps maximum length
    password isn't the same as the old password
     */

    // DELETE
    public void deleteUser(Integer id) {
        // Get currently logged in user's email from JWT
        String loggedInEmail = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        // Load the user being modified
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found."));

        // Check ownership — or allow admin
        String existingUserEmail = existingUser.getEmail();
        boolean isAdmin = SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!loggedInEmail.equals(existingUserEmail) && !isAdmin) {
            throw new RuntimeException("You are not allowed to modify this user.");
        }

        //---
        /*
        User user = userRepository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("This user does not exist."));

         */
        userRepository.delete(existingUser);
    }

    // Handle role changes through a service with appropriate authorization.
    public User promoteToAdmin(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("This user does not exist."));

        user.setUserType(User.UserType.ADMIN);
        return userRepository.save(user);
    }

    public User demoteToRegular(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("This user does not exist."));

        user.setUserType(User.UserType.REGULAR);
        return userRepository.save(user);
    }


    // by implements UserDetailsService Spring Security needs to know how to load your user from the database
    // This converts your User entity into a Spring Security UserDetails object.
    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

        return org.springframework.security.core.userdetails.User
            .withUsername(user.getEmail())
            .password(user.getHashedPassword())
            .roles(user.getUserType().name()) // This converts ADMIN → role ADMIN.
                                              // But Spring Security's hasRole("ADMIN") internally looks
                                              // for ROLE_ADMIN — Spring automatically adds the ROLE_ prefix when you use .roles().
            .build();
    }

    // LogInMe
    public String logInMe (LoginDTO loginDTO) {
         /*
        Receive LoginDTO
        Call userService to find user by email
        Verify password with BCrypt
        Call jwtService.generateToken(email)
        Return token
        */

        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(()->
                        new RuntimeException("This user does not exist."));

        if(!passwordEncoder.matches(loginDTO.getPassword(), user.getHashedPassword())){
            throw new RuntimeException( "Email and Password do not match.");
        }

        return jwtService.generateToken(loginDTO.getEmail());

    }

}
