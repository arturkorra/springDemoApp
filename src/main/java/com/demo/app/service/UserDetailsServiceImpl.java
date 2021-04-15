package com.demo.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.app.entity.Role;
import com.demo.app.repository.UserRepository;
/**
 * Class name: UserDetailsServiceImpl
 *
 * Description: UserDetailsServiceImpl 
 * 
 *
 * Company: Task
 *
 * @author Artur Korra
 * @date 23/jan/2020
 *
 */
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService, IUserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// Search for user in DB
		List<com.demo.app.entity.User> allWithSameName = findByUsernameList(username);
		com.demo.app.entity.User appUser = null;
		if (allWithSameName.size()>1) {
			appUser=allWithSameName.get(0);//if we find 2 users with same name return the first one
		}else {
	     appUser = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		}
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		for (Role authority : appUser.getRoles()) {
			// ROLE_USER, ROLE_ADMIN,..
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getPrivileges().toString());
			grantList.add(grantedAuthority);
		}
		UserDetails user = (UserDetails) new User(appUser.getUsername(), appUser.getPassword(), grantList);
		return user;
	}


	@Override
	public com.demo.app.entity.User getUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<com.demo.app.entity.User> findByUsernameList(String username) {
		List<com.demo.app.entity.User> allUserWithName = userRepository.findByusername("admin");
		return allUserWithName;
	}
}