package com.toursiteback.messenger.service.admin;

import com.toursiteback.messenger.model.User;
import com.toursiteback.messenger.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminMessengerServiceImpl implements AdminMessengerService {
    private final UsersRepository usersRepository;

    @Autowired
    public AdminMessengerServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<User> getUsers() {
        return usersRepository.findAllBy();
    }
}
