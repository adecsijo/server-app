package org.thesis.services;

import io.quarkus.elytron.security.common.BcryptUtil;
import org.thesis.dtos.SimpleStringDto;
import org.thesis.dtos.UserDto;
import org.thesis.entities.User;
import org.thesis.exceptions.SimpleException;
import org.thesis.mappers.UserMapper;
import org.thesis.repositories.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class UserService {

    private static final String USERNAME_AND_PASSWORD_ERROR = "Something went wrong, please check your username and password!";
    private static final String USERNAME_ERROR = "This username is reserved, please choose another one!";

    @Inject
    UserRepository userRepository;

    @Inject
    UserMapper userMapper;

    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public void registration(UserDto userDto) throws SimpleException {
        if (userRepository.findById(userDto.getUsername()) != null) {
            throw new SimpleException(USERNAME_ERROR);
        }
        userRepository.save(userMapper.map(userDto).newUser());
    }

    public SimpleStringDto login(final UserDto userDto) throws SimpleException {
        try {
            User user = userRepository.findById(userDto.getUsername());
            if (BcryptUtil.matches(userDto.getPassword(), user.getPassword())) {
                return new SimpleStringDto(getBasicAuth(userDto));
            } else {
                throw new SimpleException(USERNAME_AND_PASSWORD_ERROR);
            }
        } catch (Exception e) {
            throw new SimpleException(USERNAME_AND_PASSWORD_ERROR);
        }
    }

    public SimpleStringDto modifyUser(String actualUsername, UserDto userDto) throws SimpleException {
        try {
            User user = userRepository.findById(actualUsername);
            if (null == user) {
                throw new SimpleException(USERNAME_AND_PASSWORD_ERROR);
            }
            userRepository.delete(user);
            userRepository.save(userMapper.map(userDto).newUser());
            return new SimpleStringDto(getBasicAuth(userDto));
        } catch (Exception e) {
            e.printStackTrace();
            throw new SimpleException(USERNAME_AND_PASSWORD_ERROR);
        }
    }

    private String getBasicAuth(final UserDto userDto) {
        final String basicAuth = userDto.getUsername() + ":" + userDto.getPassword();
        return Base64.getEncoder().encodeToString(basicAuth.getBytes());
    }
}
