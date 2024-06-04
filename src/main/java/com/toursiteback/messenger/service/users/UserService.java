package com.toursiteback.messenger.service.users;

import com.toursiteback.messenger.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto login(UserDto user);
    void decreaseUserPositions();
}
