package com.toursiteback.service;

import com.toursiteback.service.adminPanel.AdminPanelService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("AdminDetailsService")
public class AdminDetailsService implements UserDetailsService {

    private final AdminPanelService adminPanelService;

    @Autowired
    public AdminDetailsService(AdminPanelService adminPanelService) {
        this.adminPanelService = adminPanelService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        val user = adminPanelService.getUser(username);
        assert user != null;
        return new User(user.getName(), user.getPassword(), List.of());
    }
}
