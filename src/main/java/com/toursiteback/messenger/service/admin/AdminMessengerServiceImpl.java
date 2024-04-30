package com.toursiteback.messenger.service.admin;

import com.toursiteback.messenger.dto.UserDto;
import com.toursiteback.messenger.repository.UsersRepository;
import com.toursiteback.util.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminMessengerServiceImpl implements AdminMessengerService {
    private final UsersRepository usersRepository;
    private final ModelConverter modelConverter;

    @Autowired
    public AdminMessengerServiceImpl(UsersRepository usersRepository, ModelConverter modelConverter) {
        this.usersRepository = usersRepository;
        this.modelConverter = modelConverter;
    }

    @Override
    public List<UserDto> getUsers() {
        return modelConverter.convertUsersToDtoList(usersRepository.findAllByExceptAdmin());
    }
}
