package com.sunny.fooddelivery.service;

import com.sunny.fooddelivery.model.Admin;
import com.sunny.fooddelivery.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    public Admin login(String email, String password) {
        return adminRepository.findByEmailAndPassword(email, password).orElse(null);
    }
}
