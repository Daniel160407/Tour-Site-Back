package com.toursiteback.messenger.service.users;

import com.toursiteback.messenger.dto.UserDto;
import com.toursiteback.messenger.model.User;
import com.toursiteback.messenger.repository.UsersRepository;
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
        if (foundUser == null) {
            userRepository.save(modelConverter.convert(user));
            return user;
        }
        return ModelConverter.convert(foundUser);
    }
}
