package com.business;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.business.entities.Admin;
import com.business.repositories.AdminRepository;
import com.business.services.AdminService;

@SpringBootTest
class AdminServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminService adminService;

    @Test
    void getAdminById() {
        // Create mock admin object
        Admin admin = new Admin();
        admin.setAdminId(1);
        admin.setAdminName("Admin1");
        admin.setAdminEmail("admin1@email.com");
        admin.setAdminPassword("password1");

        // Simulate admin in repository
        Mockito.when(adminRepository.findById(1)).thenReturn(Optional.of(admin));

        // Call getAdmin method
        Admin retrievedAdmin = adminService.getAdmin(1);

        // Verify that admin is retrieved correctly
        assertEquals(admin, retrievedAdmin);
    }

    @Test
    void getAdminWithInvalidIdThrowsException() {
        // Simulate admin not found in repository
        Mockito.when(adminRepository.findById(100)).thenReturn(Optional.empty());

        // Call getAdmin method
        assertThrows(IllegalArgumentException.class, () -> adminService.getAdmin(100));
    }

    @Test
    void addAdmin() {
        // Create mock admin object
        Admin admin = new Admin();
        admin.setAdminName("New Admin");
        admin.setAdminEmail("newAdmin@email.com");
        admin.setAdminPassword("newPassword");

        // Simulate saving admin in repository
        Mockito.when(adminRepository.save(admin)).thenReturn(admin);

        // Call addAdmin method
        adminService.addAdmin(admin);

        // Verify that admin is saved correctly
        Mockito.verify(adminRepository, Mockito.times(1)).save(admin);
    }

    @Test
    void updateAdmin() {
        // Create mock admin object
        Admin admin = new Admin();
        admin.setAdminId(1);
        admin.setAdminName("Updated Admin");
        admin.setAdminEmail("updatedAdmin@email.com");
        admin.setAdminPassword("updatedPassword");

        // Simulate updating admin in repository
        Mockito.when(adminRepository.findById(1)).thenReturn(Optional.of(admin));
        Mockito.when(adminRepository.save(admin)).thenReturn(admin);

        // Call updateAdmin method
        adminService.updateAdmin(admin, 1);

        // Retrieve updated admin from repository
        Admin retrievedAdmin = adminRepository.findById(1).orElseThrow();

        // Verify that admin is updated correctly
        assertEquals("Updated Admin", retrievedAdmin.getAdminName());
        assertEquals("updatedAdmin@email.com", retrievedAdmin.getAdminEmail());
        assertEquals("updatedPassword", retrievedAdmin.getAdminPassword());
    }
}