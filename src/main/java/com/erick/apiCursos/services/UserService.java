package com.erick.apiCursos.services;

import com.erick.apiCursos.domain.user.User;
import com.erick.apiCursos.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User Not Found"));
    }
}
