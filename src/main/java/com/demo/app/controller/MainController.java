package com.demo.app.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.app.config.WebSecurityConfig;
import com.demo.app.entity.User;
import com.demo.app.repository.UserRepository;
/**
 * Class name: MainController
 *
 * Description: MainController 
 * 
 *
 * Company: Task
 *
 * @author Artur Korra
 * @date 23/jan/2020
 *
 */
@Controller
public class MainController {
	@Autowired
	UserRepository userRepository;
	
	@Autowired 
    WebSecurityConfig webSecurityConfig;
	
	@GetMapping({ "/", "/login" })
	public String index() {
		return "login";
	}
	@GetMapping("/menu")
	public String userPanel(final Model model) {
		List<User> allUsers = userRepository.findAll();
		model.addAttribute("allUsers",allUsers);
		return "menu";
	}
	@GetMapping("/sortUsername")
	public String sortUsername(final Model model) {
		List<User> allUsers = userRepository.findAll();
//		Comparator.comparing(User::getUsername, (u1, u2) -> {
//			return u1.compareTo(u2);
//		});
		Collections.sort(allUsers, Comparator.comparing(User::getUsername));
		model.addAttribute("allUsers",allUsers);
		return "menu";
	}
	@GetMapping("/sortLastname")
	public String sortLastname(final Model model) {
		List<User> allUsers = userRepository.findAll();
		Collections.sort(allUsers, Comparator.comparing(User::getLastname));
		model.addAttribute("allUsers",allUsers);
		return "menu";
	}
	
	@RequestMapping(value = { "/edit" }, method = RequestMethod.GET)
	public String edit(Model model, @RequestParam(value = "id", defaultValue = "") String id) {
		User user = new User();

		if (id != null && id.length() > 0) {
			user = userRepository.getOne(Long.parseLong(id));
			if (user != null) {
				model.addAttribute("user", user);
			}
		}
		return "userEdit";
	}
	@RequestMapping(value = { "/addUser" }, method = RequestMethod.GET)
	public String addUser(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "addUser";
	}
	@RequestMapping(value = { "/deleteUser" }, method = RequestMethod.GET)
	public String deleteUser(Model model, @RequestParam(value = "id", defaultValue = "") String id) {
		User user = null;
		if (id != null && id.length() > 0) {
		 user = userRepository.getOne(Long.parseLong(id));
		}
        userRepository.delete(user);
        return "redirect:/menu";

	}
	// POST: Save edit
		@RequestMapping(value = { "/saveEdit" }, method = RequestMethod.POST)
		public String saveEdit(Model model, @ModelAttribute("user") @Validated User user,
				BindingResult result, final RedirectAttributes redirectAttributes) {

			if (result.hasErrors()) {
				return "edit?id=" + user.getId();
			}
			try {
				user.setPassword(webSecurityConfig.passwordEncoder().encode(user.getPassword()).toString());
				userRepository.save(user);
			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:/menu";
			}
			
			return "redirect:/menu";
		}
}
