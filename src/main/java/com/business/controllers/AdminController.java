package com.business.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.business.entities.Admin;
import com.business.entities.Product;
import com.business.entities.User;
import com.business.login.credentials.AdminLogin;
import com.business.services.AdminService;
import com.business.services.ProductService;
import com.business.services.UserService;

@Controller
public class AdminController{
    private final AdminService adminService;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public AdminController(AdminService adminService, UserService userService, ProductService productService) {
        this.adminService = adminService;
        this.userService = userService;
        this.productService = productService;
    }
    
    
    //validating login
    
	private static final String SERVICES_URL = "redirect:/services";
    
	@PostMapping("/adminLogin")
	
	public String getallData(@ModelAttribute("adminLogin")AdminLogin login,Model model)
	{
		String email=login.getEmail();
		String password=login.getPassword();
		if(adminService.validateAdminCredentials(email, password))
		{
			return SERVICES_URL;
		}
	
		else{
			model.addAttribute("error", "Invalid email or password");
			return "Login";
		}
	}

	@GetMapping("/services")
	public String returnBack(Model model) {
		Iterable<Admin> admins = this.adminService.getAll();
		Iterable<User> users = this.userService.getAllUsers();
		Iterable<Product> products = this.productService.getAllProducts();
		if (admins instanceof List || users instanceof List) {
			List<Admin> adminsAsList = (List<Admin>) admins;
			model.addAttribute("admins", adminsAsList);

			List<User> usersAsList = (List<User>) users;
			model.addAttribute("users", usersAsList);

			List<Product> productsAsList = (List<Product>) products;
			model.addAttribute("products", productsAsList);
		} else {
			model.addAttribute("admins", admins);
		}
		
		return "Admin_Page";
	}
	//Invoking addAdmin Page
	@GetMapping("/addAdmin")
	public String addAdminPage()
	{
		return "Add_Admin";
	}
	//Handling AddAdmin
	@PostMapping("addingAdmin")
	public String addAdmin(@ModelAttribute Admin admin)
	{
		this.adminService.addAdmin(admin);
		return SERVICES_URL;
	}
	//invoking updateAdmin Page
	@GetMapping("/updateAdmin/{adminId}")
	public String update(@PathVariable("adminId") int id,Model model)
	{
		Admin admin = this.adminService.getAdmin(id);
		model.addAttribute("admin", admin);
		return "Update_Admin";
	}
	//Handling Update Page
	@GetMapping("/updatingAdmin/{id}")
	public String updateAdmin(@ModelAttribute Admin admin,@PathVariable("id") int id)
	{
		this.adminService.updateAdmin(admin, id);
		return SERVICES_URL;
	}
	//IHandling delete operation
	@GetMapping("/deleteAdmin/{id}")
	public String deleteAdmin(@PathVariable("id") int id)
	{
		this.adminService.delete(id);
		return SERVICES_URL;
	}
	//invoking user page
	@GetMapping("/addUser")
	public String addUser()
	{
		return "Add_User";
	}
	//adding user
	@PostMapping("addingUser")
	public String addNewUser(@ModelAttribute User user)
	{
		this.userService.addUser(user);
		return SERVICES_URL;
	}
	//Invoking UpdateUser Page
	@GetMapping("/updateUser/{userId}")
	public String updateUserPage(@PathVariable("userId") int id,Model model)
	{
		User user = this.userService.getUser(id);
		model.addAttribute("user", user);
		return "Update_User";
	}
	//Handling Update Page
	@GetMapping("/updatingUser/{id}")
	public String updateUser(@ModelAttribute User user,@PathVariable("id") int id)
	{
		this.userService.updateUser(user, id);
		return SERVICES_URL;
	}
	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable("id")int id)
	{
		this.userService.deleteUser(id);
		return SERVICES_URL;
	}
	
	//handling Product page
	@GetMapping("/addProduct")
	public String addProduct()
	{
		return "Add_Product";
	}
	@PostMapping("addingProduct")
	public String addNewProduct(@ModelAttribute Product product)
	{
		this.productService.addProduct(product);
		return SERVICES_URL;
	}
	
	@GetMapping("/updateProduct/{productId}")
	public String updateProduct(@PathVariable("productId") int id,Model model)
	{
		Product product=this.productService.getProduct(id);
		System.out.println(product);
		model.addAttribute("product", product);
		return "Update_Product";
	}
	
	
	@GetMapping("/updatingProduct/{id}")
	public String updateProduct(@ModelAttribute Product product,@PathVariable("id") int id)
	{
		this.productService.updateProduct(product, id);
		return SERVICES_URL;
	}
	@GetMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable("id")int id)
	{
		this.productService.deleteProduct(id);
		return SERVICES_URL;
	}

}

