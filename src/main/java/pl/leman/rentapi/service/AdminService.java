package pl.leman.rentapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.leman.rentapi.model.Admin;
import pl.leman.rentapi.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Iterable<Admin> findAll() {
        return adminRepository.findAll();
    }
}
