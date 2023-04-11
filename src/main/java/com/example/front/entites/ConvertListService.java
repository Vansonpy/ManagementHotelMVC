package com.example.front.entites;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ConvertListService {
	public List<Integer> listButton(List listAll, Integer index) {
		List<Integer> listButton = new ArrayList<Integer>();
		Integer countField = listAll.size();
		for (int i = 0; i < countField; i = i + 8) {
			listButton.add(i);
		}
		return listButton;
	}
	
	public List<Sales> listShowSales(List listAll, Integer index) {
		Integer fromIndex = index * 8;
		Integer toIndex = 0;
		if ((listAll.size() - fromIndex) > 8) {
			toIndex = fromIndex + 8;
		} else {
			toIndex = listAll.size();
		}
		List<Sales> listsSalesShow = listAll.subList(fromIndex, toIndex);
		return listsSalesShow;
	}
	
	public List<Pages> listShowPages(List listAll, Integer index) {
		Integer fromIndex = index * 8;
		Integer toIndex = 0;
		if ((listAll.size() - fromIndex) > 8) {
			toIndex = fromIndex + 8;
		} else {
			toIndex = listAll.size();
		}
		List<Pages> listsPagesShow = listAll.subList(fromIndex, toIndex);
		return listsPagesShow;
	}
	
	public List<Users> listShowUsers(List listAll, Integer index) {
		Integer fromIndex = index * 8;
		Integer toIndex = 0;
		if ((listAll.size() - fromIndex) > 8) {
			toIndex = fromIndex + 8;
		} else {
			toIndex = listAll.size();
		}
		List<Users> listsUsersShow = listAll.subList(fromIndex, toIndex);
		return listsUsersShow;
	}
	
	public List<Rooms> listShowRooms(List listAll, Integer index) {
		Integer fromIndex = index * 8;
		Integer toIndex = 0;
		if ((listAll.size() - fromIndex) > 8) {
			toIndex = fromIndex + 8;
		} else {
			toIndex = listAll.size();
		}
		List<Rooms> listsRoomsShow = listAll.subList(fromIndex, toIndex);
		return listsRoomsShow;
	}
	
	public List<Contact> listShowContact(List listAll, Integer index) {
		Integer fromIndex = index * 8;
		Integer toIndex = 0;
		if ((listAll.size() - fromIndex) > 8) {
			toIndex = fromIndex + 8;
		} else {
			toIndex = listAll.size();
		}
		List<Contact> listsContactShow = listAll.subList(fromIndex, toIndex);
		return listsContactShow;
	}
}
