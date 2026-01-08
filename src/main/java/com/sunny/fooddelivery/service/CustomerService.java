package com.sunny.fooddelivery.service;

import com.sunny.fooddelivery.model.Customer;
import com.sunny.fooddelivery.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer register(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email)
                .orElse(null);  // ✅ Unwrap optional safely
    }

    public Customer login(String email, String password) {
        Customer customer = customerRepository.findByEmail(email)
                .orElse(null); // ✅ Unwrap optional safely

        if (customer != null && customer.getPassword().equals(password)) {
            return customer;
        }
        return null;
    }
}
