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
public class UserController {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ConvertListService convertListService;
	@Value("${backend.api.url}")
	private String apiUrl;

	@Autowired
	private EmailService emailService;

	@GetMapping("/booknow")
	public String bookNowIndex(Model model) {

		Sales saleNew = new Sales();
		Users user = new Users();
		model.addAttribute("sales", saleNew);
		model.addAttribute("user", user);
		return "bookNow";

	}

	@SuppressWarnings("unchecked")
	@PostMapping("/room")
	public String bookNowRoom(Model model, @ModelAttribute("sales") Sales sale, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		try {
			if (request.getSession().getAttribute("userSession") != null) {
				Users user = (Users) request.getSession().getAttribute("userSession");
				model.addAttribute("sales", sale);
				model.addAttribute("user", user);
				return "bookingConfirm";
			}
			Users user = new Users();
			model.addAttribute("sales", sale);
			model.addAttribute("user", user);
			return "bookingConfirm";
		} catch (Exception e) {
			e.printStackTrace();

			String url = apiUrl + "/rooms/viewAllRoom";
			List<Rooms> listRooms = restTemplate.getForObject(url, List.class);
			List<Rooms> listsRoomsShow = convertListService.listShowRooms(listRooms, 0);
			List<Integer> listButton = convertListService.listButton(listRooms, 0);
			model.addAttribute("countFields", listButton);
			model.addAttribute("listRooms", listsRoomsShow);
			return "roomCustomer";
		}

	}

	@SuppressWarnings("unchecked")
	@PostMapping("/viewAllUsersByButton")
	public String viewAllUsersByButton(Model model, @RequestParam Integer index) {
		try {

			String url = apiUrl + "/users/viewAllMember";
			List<Users> listUsers = restTemplate.getForObject(url, List.class);
			List<Users> listsUsersShow = convertListService.listShowUsers(listUsers, index);
			List<Integer> listButton = convertListService.listButton(listUsers, index);
			model.addAttribute("countFields", listButton);
			model.addAttribute("listUsers", listsUsersShow);
			return "Quanlymember";
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NO_CONTENT) {
				List<Users> listUsers = null;
				model.addAttribute("listUsers", listUsers);
				return "Quanlymember";
			}
			e.printStackTrace();
			model.addAttribute("message", e);
			return "error";
		}
	}

	@GetMapping("/contact")
	public String contactIndex(Model model) {
//		model.addAttribute("Rooms", new Rooms());
		return "contact";
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/room")
	public String getAllRoom(Model model) {
		try {
			String url = apiUrl + "/rooms/viewAllRoom";
			List<Rooms> listRooms = restTemplate.getForObject(url, List.class);
			if (listRooms != null) {
				List<Rooms> listsRoomsShow = convertListService.listShowRooms(listRooms, 0);
				List<Integer> listButton = convertListService.listButton(listRooms, 0);
				Sales sale = new Sales();
				model.addAttribute("countFields", listButton);
				model.addAttribute("sale", sale);
				model.addAttribute("listRooms", listsRoomsShow);
			}
			return "roomCustomer";
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NO_CONTENT) {
				List<Rooms> roomsArray = null;
				Sales sale = new Sales();
				model.addAttribute("listRooms", roomsArray);
				model.addAttribute("sale", sale);
				return "roomCustomer";
			}
			e.printStackTrace();
			model.addAttribute("message", e);
			return "error";
		}
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/FormCheckRoom")
	public String getAllRoomByFormCheckRoom(@RequestParam String checkinDate, @RequestParam String checkoutDate,
			@RequestParam String type, @RequestParam String price, Model model) throws Exception {
		try {
			Date checkInDate = new SimpleDateFormat("yyyy-MM-dd").parse(checkinDate);
			Date checkOutDate = new SimpleDateFormat("yyyy-MM-dd").parse(checkoutDate);
			Integer priceConvert = Integer.parseInt(price);
			Sales sale = new Sales();
			sale.setCheckinDate(checkInDate);
			sale.setCheckoutDate(checkOutDate);
			sale.setPrice(priceConvert);
			sale.setType(type);

			String url = apiUrl + "/rooms/FormCheckRoom";
			List<Rooms> listRooms = restTemplate.postForObject(url, sale, List.class);
			if (listRooms != null) {
				List<Rooms> listsRoomsShow = convertListService.listShowRooms(listRooms, 0);
				List<Integer> listButton = convertListService.listButton(listRooms, 0);
				model.addAttribute("countFields", listButton);
				model.addAttribute("listRooms", listsRoomsShow);
			}
			return "roomCustomer";
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NO_CONTENT) {
				List<Rooms> roomsArray = null;
				model.addAttribute("listRooms", roomsArray);
				return "roomCustomer";
			}
			e.printStackTrace();
			model.addAttribute("message", e);
			return "error";
		}
	}

	@GetMapping("/viewUser")
	public String getUserInfo(Model model, HttpServletRequest request) {
		try {
			Users user = (Users) request.getSession().getAttribute("userSession");
			String userName = user.getUserName();
			String url = apiUrl + "/users/viewByUserName" + "/" + userName;
			Users userInfo = restTemplate.getForObject(url, Users.class);
			if (userInfo != null) {
				model.addAttribute("userInfo", userInfo);
			}
			return "Quanlythongtincanhan";
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NO_CONTENT) {
				Users userInfo = null;
				model.addAttribute("userInfo", userInfo);
				return "Quanlythongtincanhan";
			}
			e.printStackTrace();
			return "notExitUrl";
		}
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/viewListUsers")
	public String getListUsersInfo(Model model) {
		try {

			String url = apiUrl + "/users/viewAllMember";
			List<Users> listUsers = restTemplate.getForObject(url, List.class);
			if (listUsers != null) {
				List<Users> listsUsersShow = convertListService.listShowUsers(listUsers, 0);
				List<Integer> listButton = convertListService.listButton(listUsers, 0);
				model.addAttribute("countFields", listButton);
				model.addAttribute("listUsers", listsUsersShow);
			}
			return "Quanlymember";
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NO_CONTENT) {
				List<Users> listUsers = null;
				model.addAttribute("listUsers", listUsers);
				return "Quanlymember";
			}
			e.printStackTrace();
			model.addAttribute("message", e);
			return "error";
		}
	}

	@PostMapping("/FormBookRoom")
	public String bookRoomByForm(@RequestParam String checkinDate, @RequestParam String checkoutDate,
			@RequestParam String type, @RequestParam String price, @RequestParam String email, Model model)
			throws MessagingException, Exception {
		try {
			Date checkInDate = new SimpleDateFormat("yyyy-MM-dd").parse(checkinDate);
			Date checkOutDate = new SimpleDateFormat("yyyy-MM-dd").parse(checkoutDate);
			Integer priceConvert = Integer.parseInt(price);
			Sales sale = new Sales();
			sale.setCheckinDate(checkInDate);
			sale.setCheckoutDate(checkOutDate);
			sale.setPrice(priceConvert);
			sale.setType(type);
			sale.setUserName(email);
			String url = apiUrl + "/rooms/FormBookRoom";
			Rooms room = restTemplate.postForObject(url, sale, Rooms.class);
			if (room != null) {
				emailService.sendMail(email, checkinDate, checkoutDate, type, room.getPrice(), room.getRoomName());
				model.addAttribute("msgSuccess", "Booking success");
				return "bookNow";
			}
			model.addAttribute("msgFaile", "Room Not Exit");
			return "bookNow";
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NO_CONTENT) {
				model.addAttribute("msgFaile", "Room Not Exit");
				return "bookNow";
			}
			e.printStackTrace();
			model.addAttribute("message", e);
			return "error";
		}
	}
}
