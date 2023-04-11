package com.example.frontend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.example.front.entites.ConvertListService;
import com.example.front.entites.Pages;
import com.example.front.entites.Rooms;
import com.example.front.entites.Sales;

@Controller
public class RoomsController {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ConvertListService convertListService;
	@Value("${backend.api.url}")
	private String apiUrl;

	@SuppressWarnings("unchecked")
	@PostMapping("/viewAllRoomsByButton")
	public String viewAllRoomsByButton(Model model, @RequestParam Integer index) {
		try {
			String url = apiUrl + "/rooms/viewAllRoom";
			List<Rooms> listRooms = restTemplate.getForObject(url, List.class);
			if (listRooms != null) {
				List<Rooms> listsSalesShow = convertListService.listShowRooms(listRooms, index);
				List<Integer> listButton = convertListService.listButton(listRooms, index);
				model.addAttribute("countFields", listButton);
				model.addAttribute("listRooms", listsSalesShow);
			}
			return "managerRooms";
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NO_CONTENT) {
				List<Rooms> roomsArray = null;
				model.addAttribute("listRooms", roomsArray);
				return "managerRooms";
			}
			e.printStackTrace();
			model.addAttribute("message", e);
			return "error";
		}
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/viewAllRooms")
	public String viewAllRooms(Model model) {
		try {
			String url = apiUrl + "/rooms/viewAllRoom";
			List<Rooms> listRooms = restTemplate.getForObject(url, List.class);
			if (listRooms != null) {
				List<Rooms> listsSalesShow = convertListService.listShowRooms(listRooms, 0);
				List<Integer> listButton = convertListService.listButton(listRooms, 0);
				model.addAttribute("countFields", listButton);
				model.addAttribute("listRooms", listsSalesShow);
			}
			return "managerRooms";
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NO_CONTENT) {
				List<Rooms> roomsArray = null;
				model.addAttribute("listRooms", roomsArray);
				return "managerRooms";
			}
			e.printStackTrace();
			model.addAttribute("message", e);
			return "error";
		}
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/editRooms")
	public String editRooms(@RequestParam Optional<Integer> id, Model model) {
		try {
			Integer idInput = id.get();
			String urlDelete = apiUrl + "/rooms/deleteRoom" + "/" + idInput;
			restTemplate.delete(urlDelete);
			String url = apiUrl + "/rooms/viewAllRoom";
			List<Rooms> listRooms = restTemplate.getForObject(url, List.class);
			if (listRooms != null) {
				List<Rooms> listsSalesShow = convertListService.listShowRooms(listRooms, 0);
				List<Integer> listButton = convertListService.listButton(listRooms, 0);
				model.addAttribute("countFields", listButton);
				model.addAttribute("listRooms", listsSalesShow);
			}
			return "managerRooms";
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NO_CONTENT) {
				List<Rooms> roomsArray = null;
				model.addAttribute("listRooms", roomsArray);
				return "managerRooms";
			}
			e.printStackTrace();
			model.addAttribute("message", e);
			return "error";
		}
	}

	@RequestMapping("/addroom")
	public String addRoom(Model model) {
		Rooms roomDTO = new Rooms();
		model.addAttribute("room", roomDTO);
		return "addroom";
	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/addroom", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
	public String addRoom(Model model, @ModelAttribute("room") Rooms room) {
		try {

			Rooms roomDTO = new Rooms();
			roomDTO.setRoomName(room.getRoomName());
			roomDTO.setType(room.getType());
			roomDTO.setPrice(room.getPrice());
			Resource file = room.getMultipartFile().getResource();
			String urlImage = apiUrl + "/img/upload";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			body.add("file", file);
			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
			
			String fileID = restTemplate.postForObject(urlImage,requestEntity, String.class);
			roomDTO.setUrlImage(fileID);
			String url = apiUrl + "/rooms/addRoom";
			restTemplate.postForObject(url, roomDTO, Rooms.class);
			model.addAttribute("msg", "Add room sucessfully!");
			String urlAll = apiUrl + "/rooms/viewAllRoom";
			List<Rooms> listRooms = restTemplate.getForObject(urlAll, List.class);
			if (listRooms != null) {
				List<Rooms> listsSalesShow = convertListService.listShowRooms(listRooms, 0);
				List<Integer> listButton = convertListService.listButton(listRooms, 0);
				model.addAttribute("countFields", listButton);
				model.addAttribute("listRooms", listsSalesShow);
			}
			return "managerRooms";
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NO_CONTENT) {
				List<Rooms> roomsArray = null;
				model.addAttribute("listRooms", roomsArray);
				return "managerRooms";
			}
			e.printStackTrace();
			model.addAttribute("message", e);
			return "error";
		}
	}

}
