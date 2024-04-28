package com.toursiteback.messenger.service.users;

import com.toursiteback.messenger.dto.UserDto;
import com.toursiteback.messenger.repository.UsersRepository;
import com.toursiteback.util.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessengerServiceImpl implements MessengerService {
    private final UsersRepository userRepository;
    private final ModelConverter modelConverter;

    @Autowired
    public MessengerServiceImpl(UsersRepository userRepository, ModelConverter modelConverter) {
        this.userRepository = userRepository;
        this.modelConverter = modelConverter;
    }

    @Override
    public UserDto login(UserDto user) {
        userRepository.save(modelConverter.convert(user));
        return user;
    }
}
