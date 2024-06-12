package com.toursiteback.messenger.service.users;

import com.toursiteback.messenger.dto.UserDto;
import com.toursiteback.messenger.model.User;
import com.toursiteback.messenger.repository.UsersRepository;
import com.toursiteback.service.exception.InvalidEmailOrPasswordException;
import com.toursiteback.service.exception.UserNotAccessibleException;
import com.toursiteback.util.ModelConverter;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UsersRepository userRepository;
    private final ModelConverter modelConverter;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UsersRepository userRepository, ModelConverter modelConverter, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelConverter = modelConverter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto login(UserDto user) {
        User foundUser = userRepository.getUserByEmail(user.getEmail());
        if (foundUser != null) {
            if (passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
                foundUser.setSid(user.getSid());
                userRepository.save(foundUser);
                return ModelConverter.convert(foundUser);
            } else {
                throw new InvalidEmailOrPasswordException();
            }
        } else {
            val encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userRepository.save(modelConverter.convert(user));
            userRepository.incrementPositionForAllExceptSpecificEmail(user.getEmail());
            return user;
        }
    }

    @Override
    public void decreaseUserPositions() {
        userRepository.reducePositionForAllExceptSpecificEmail();
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.getUserByEmail(email);
        if (user != null) {
            return ModelConverter.convert(user);
        } else {
            throw new UserNotAccessibleException();
        }
    }
}
