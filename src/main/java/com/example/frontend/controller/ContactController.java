package com.example.frontend.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.front.entites.Contact;
import com.example.front.entites.ConvertListService;
import com.example.front.entites.EmailService;
import com.example.front.entites.Pages;
import com.example.front.entites.Rooms;
import com.example.front.entites.Sales;
import com.example.front.entites.Users;

@Controller
public class ContactController {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ConvertListService convertListService;
	@Value("${backend.api.url}")
	private String apiUrl;


	@PostMapping("/sendContact")
	public String sendContact(@ModelAttribute("contact") Contact contact, Model model) throws MessagingException {
		try {
			String url = apiUrl + "/contact/addContact";
			String repon = restTemplate.postForObject(url, contact, String.class);
			model.addAttribute("msgSuccess", repon);

			return "contact";

		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NO_CONTENT) {
				model.addAttribute("msgFaile", "Room Not Exit");
				return "contact";
			}
			e.printStackTrace();
			model.addAttribute("message", e);
			return "error";
		}
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/customerReview")
	public String getAllRoom(Model model) {
		try {
			String url = apiUrl + "/contact/viewAllContact";
			List<Contact> listContacts = restTemplate.getForObject(url, List.class);
			if (listContacts != null) {
				List<Contact> listsContactsShow = convertListService.listShowContact(listContacts, 0);
				List<Integer> listButton = convertListService.listButton(listContacts, 0);
				model.addAttribute("countFields", listButton);
				model.addAttribute("listContacts", listsContactsShow);
			}
			return "customerReview";
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NO_CONTENT) {
				List<Rooms> roomsArray = null;
				model.addAttribute("listContacts",  roomsArray);
				return "customerReview";
			}
			e.printStackTrace();
			model.addAttribute("message", e);
			return "error";
		}
	}

}
