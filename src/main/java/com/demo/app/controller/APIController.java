package com.demo.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.app.config.WebSecurityConfig;
import com.demo.app.entity.User;
import com.demo.app.repository.UserRepository;
import com.demo.app.service.IUserService;
/**
 * Class name: APIController
 *
 * Description: APIController 
 * 
 *
 * Company: Task
 *
 * @author Artur Korra
 * @date 23/jan/2020
 *
 */
@RestController
@RequestMapping("/api/service/usersJSON/")
public class APIController {
	@Autowired
	private IUserService userService;
	@Autowired
	UserRepository userRepository;
	
	@Autowired 
    WebSecurityConfig webSecurityConfig;
	
	@RequestMapping(value={"/allUser"})
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> allUser = new ArrayList<User>();
		allUser=userRepository.findAll();
		for (User user : allUser) {
			user.setRoles(null);
		}
		return new ResponseEntity<List<User>>(allUser, HttpStatus.OK);
	}
	
	//@PreAuthorize("hasAuthority('ROLE_USER')")
	@DeleteMapping("/deleteUser")
	public ResponseEntity<Void> deleteUser(@RequestParam(value = "id", defaultValue = "") String id) {
		User user = null;
		if (id != null && id.length() > 0) {
		 user = userRepository.getOne(Long.parseLong(id));
		}
        userRepository.delete(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping(value="logout")
	public ResponseEntity<Void> logout (HttpServletRequest request, HttpServletResponse response) {
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	private String getString(String data, String key) {
		String str = "";
		try {
			JSONParser parser = new JSONParser();
			parser.parse(data);
			JSONObject obj = (JSONObject) parser.parse(data);
			str = (String) obj.get(key);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
}
