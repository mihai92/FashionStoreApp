package com.business.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.business.entities.Admin;
import com.business.repositories.AdminRepository;

@Service

public class AdminService
 { private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

	public Iterable<Admin> getAll() {
		return adminRepository.findAll();
	}

    public Admin getAdmin(int id) {
        Optional<Admin> admin = adminRepository.findById(id);
        if (admin.isPresent()) {
            return admin.get();
        } else {
            throw new IllegalArgumentException("Admin with ID " + id + " does not exist");
        }
    }

    public void updateAdmin(Admin admin, int id) {
        Admin updatedAdmin = adminRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Admin with ID " + id + " does not exist"));
        updatedAdmin.setAdminName(admin.getAdminName());
        updatedAdmin.setAdminEmail(admin.getAdminEmail());
        updatedAdmin.setAdminPassword(admin.getAdminPassword());
        adminRepository.save(updatedAdmin);
    }

    public void delete(int id) {
        adminRepository.deleteById(id);
    }

    public void addAdmin(Admin admin) {
        if (admin == null) {
            throw new IllegalArgumentException("Admin object cannot be null");
        }
        adminRepository.save(admin);
    }

    public boolean validateAdminCredentials(String email, String password) {
        return adminRepository.findByadminEmail(email) != null && adminRepository.findByadminEmail(email).getAdminPassword().equals(password);
    }
}
