package com.toursiteback.messenger.service.admin;

import com.toursiteback.messenger.dto.UserDto;
import com.toursiteback.messenger.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminMessengerService {
    List<UserDto> getUsers();

    void deleteUser(String email);
}
