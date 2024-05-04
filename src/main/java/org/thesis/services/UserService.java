package org.thesis.services;

import io.quarkus.elytron.security.common.BcryptUtil;
import org.thesis.dtos.UserDto;
import org.thesis.entities.User;
import org.thesis.exceptions.SimpleException;
import org.thesis.repositories.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Base64;

@ApplicationScoped
public class UserService {

    private static final String USERNAME_AND_PASSWORD_ERROR = "Something went wrong, please check your username and password!";
    private static final String USERNAME_ERROR = "This username is reserved, please choose another one!";

    @Inject
    UserRepository userRepository;

    public void registration(UserDto userDto) throws SimpleException {
        if (userRepository.findById(userDto.getUsername()) != null) {
            throw new SimpleException(USERNAME_ERROR);
        }
        userRepository.save(map(userDto).newUserPass());
    }

    public String login(final UserDto userDto) throws SimpleException {
        try {
            User user = userRepository.findById(userDto.getUsername());
            if (BcryptUtil.matches(userDto.getPassword(), user.getPassword())) {
                user.setLastSuccessfulLogin(LocalDateTime.now());
                userRepository.update(user);
                return getBasicAuth(userDto);
            } else {
                throw new SimpleException(USERNAME_AND_PASSWORD_ERROR);
            }
        } catch (Exception e) {
            throw new SimpleException(USERNAME_AND_PASSWORD_ERROR);
        }
    }

    public String modifyUser(String actualUsername, UserDto userDto) throws SimpleException {
        try {
            User user = userRepository.findById(actualUsername);
            if (null == user) {
                throw new SimpleException(USERNAME_AND_PASSWORD_ERROR);
            }
            userRepository.update(map(userDto).newUserPass());
            return getBasicAuth(userDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SimpleException(USERNAME_AND_PASSWORD_ERROR);
        }
    }

    private String getBasicAuth(final UserDto userDto) {
        return "Basic "+ Base64.getEncoder().encodeToString((userDto.getUsername() + ":" + userDto.getPassword()).getBytes());
    }

    private UserDto map(final User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        userDto.setLastSuccessfulLogin(user.getLastSuccessfulLogin());
        return userDto;
    }

    private User map(final UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        user.setLastSuccessfulLogin(userDto.getLastSuccessfulLogin());
        return user;
    }
}
