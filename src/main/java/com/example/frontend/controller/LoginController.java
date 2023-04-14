package com.example.frontend.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.front.entites.Pages;
import com.example.front.entites.Sales;
import com.example.front.entites.Users;

@Controller
public class LoginController {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${backend.api.url}")
	private String apiUrl;

	@GetMapping("/loginPage")
	public String loginPage(Model model) {
		Users user = new Users();
		model.addAttribute("user", user);
		return "loginPage";
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/loginPage")
	public String index(Model model, @ModelAttribute("Users") Users user, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String url = apiUrl + "/users/login";
			Users userDTO = restTemplate.postForObject(url, user, Users.class);
			if (userDTO != null) {
				/*
				 * String bearerToken = HttpHeaders.AUTHORIZATION; String token =
				 * bearerToken.substring(7); Cookie cookie = new Cookie("jwtToken", token);
				 * cookie.setMaxAge(60 * 60 * 24); // Thời gian sống của cookie là 1 ngày
				 * cookie.setPath("/"); // Chỉ định cookie có thể được truy cập bởi tất cả các
				 * URL trong ứng dụng response.addCookie(cookie);
				 */
				
				String urlHeader = apiUrl + "/pages/viewByPagesNameAndLocation" + "?pageName=Home" + "&location=Header";
				List<Pages> listContentHeader = restTemplate.getForObject(urlHeader, List.class);

				String urlBorder = apiUrl + "/pages/viewByPagesNameAndLocation" + "?pageName=Home" + "&location=Border";
				List<Pages> listContentBorder = restTemplate.getForObject(urlBorder, List.class);

				Sales sale = new Sales();
				model.addAttribute("listBorder", listContentBorder);
				model.addAttribute("listHeader", listContentHeader);
				model.addAttribute("sales", sale);
				request.getSession().setAttribute("userSession", userDTO);
				if(userDTO.getRole().equals("1")) {
					return "adminHomePage";
				}
				return "home";
			} else {
				model.addAttribute("msg", "Login Fail");
				return "loginPage";
			}
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
				model.addAttribute("msg", "Login failed");
				// Tao trang loi 400
			} else {
				model.addAttribute("msg", "Login failed ");
			}
			return "loginPage";
		}

	}

	@GetMapping("/forgetPasswordPage")
	public String forgetPasswordPage(Model model) {
		Users user = new Users();
		model.addAttribute("user", user);
		return "forgetPassword";
	}

	@PostMapping("/forgetPasswordPage")
	public String forgetPassword(Model model, @ModelAttribute("user") Users user) {
		try {
			String url = apiUrl + "/users/checkUserNameAndEmail";
			Users userDTO = new Users();
			System.out.println(user.getUserName());
			userDTO.setUserName(user.getUserName());
			userDTO.setEmail(user.getEmail());
			userDTO = restTemplate.postForObject(url, userDTO, Users.class);
			if (userDTO != null) {
				if (user.getConfirmPassword().equals(user.getPassword())) {
					userDTO.setPassword(user.getPassword());
					String url1 = apiUrl + "/users/updateMember/" + userDTO.getId();
					restTemplate.put(url1, userDTO, Users.class);
					model.addAttribute("msg", "Successfull UpdatePass");
					return "loginPage";
				} else {
					model.addAttribute("msg", "Not the same Pass");
					return "forgetPassword";
				}
			} else {
				model.addAttribute("msg", "Username not registered");
				return "forgetPassword";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e);
			return "error";
		}
	}

	@GetMapping("/registerPage")
	public String registerPage(Model model, @ModelAttribute("Users") Users user) {
		model.addAttribute("user", user);
		return "register";
	}

	@PostMapping("/registerPage")
	public String signUp(Model model, @ModelAttribute("Users") Users user) {
		try {
			String url = apiUrl + "/users/registerMember";
			if (user.getPassword().equals(user.getConfirmPassword())) {
				Users userdDTO = new Users();
				String lastName = user.getLastName();
				String firstName = user.getFirstName();
				userdDTO.setFullName(lastName + " " + firstName);
				userdDTO.setEmail(user.getEmail());
				userdDTO.setGender(user.getGender());
				userdDTO.setPhone(user.getPhone());
				userdDTO.setUserName(user.getUserName());
				userdDTO.setPassword(user.getPassword());
				userdDTO.setRole("RoleUser");
				userdDTO = restTemplate.postForObject(url, userdDTO, Users.class);
				if (userdDTO != null) {

					model.addAttribute("msgResgiter", "Register Success");
					return "loginPage";
				} else {
					model.addAttribute("msg", "Register Fail");
					return "register";
				}
			} else {
				model.addAttribute("msg", "Password is not the same");
				return "register";
			}
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
				model.addAttribute("msg", "Register failed ");
			}
			if (e.getStatusCode() == HttpStatus.RESET_CONTENT)
				model.addAttribute("msg", "Register failed ");
			return "register";
		}

	}

	@SuppressWarnings("unchecked")
	@GetMapping("/signout")
	public String signout(@RequestParam Optional<Integer> id, Model model, HttpServletRequest request) {
		request.getSession().setAttribute("userSession", null);
		
		String urlHeader = apiUrl + "/pages/viewByPagesNameAndLocation" + "?pageName=Home" + "&location=Header";
		List<Pages> listContentHeader = restTemplate.getForObject(urlHeader, List.class);

		String urlBorder = apiUrl + "/pages/viewByPagesNameAndLocation" + "?pageName=Home" + "&location=Border";
		List<Pages> listContentBorder = restTemplate.getForObject(urlBorder, List.class);

		Sales sale = new Sales();
		model.addAttribute("listBorder", listContentBorder);
		model.addAttribute("listHeader", listContentHeader);
		model.addAttribute("sales", sale);
		return "home";
	}

}
