package com.toursiteback.messenger.service.users;

import com.toursiteback.messenger.dto.UserDto;
import com.toursiteback.messenger.model.User;
import com.toursiteback.messenger.repository.UsersRepository;
import com.toursiteback.service.exception.InvalidEmailOrPasswordException;
import com.toursiteback.util.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UsersRepository userRepository;
    private final ModelConverter modelConverter;

    @Autowired
    public UserServiceImpl(UsersRepository userRepository, ModelConverter modelConverter) {
        this.userRepository = userRepository;
        this.modelConverter = modelConverter;
    }

    @Override
    public UserDto login(UserDto user) {
        User foundUser = userRepository.getUserByEmail(user.getEmail());
        if (foundUser != null) {
            User passwordCheckedUser = userRepository.getUserByEmailAndPassword(user.getEmail(), user.getPassword());
            if (passwordCheckedUser != null && foundUser == passwordCheckedUser) {
                foundUser.setSid(user.getSid());
                userRepository.save(foundUser);
                return ModelConverter.convert(foundUser);
            } else {
                throw new InvalidEmailOrPasswordException();
            }
        } else {
            userRepository.save(modelConverter.convert(user));
            userRepository.incrementPositionForAllExceptSpecificEmail(user.getEmail());
            return user;
        }
    }

    @Override
    public void decreaseUserPositions() {
        userRepository.reducePositionForAllExceptSpecificEmail();
    }
}
