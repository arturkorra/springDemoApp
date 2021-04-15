package com.demo.app.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.demo.app.config.WebSecurityConfig;
import com.demo.app.entity.Privilege;
import com.demo.app.entity.Role;
import com.demo.app.entity.User;
import com.demo.app.repository.PrivilegeRepository;
import com.demo.app.repository.RoleRepository;
import com.demo.app.repository.UserRepository;
/**
 * Class name: AddAdminRoleToDB
 *
 * Description: AddAdminRoleToDB 
 * 
 *
 * Company: Task
 *
 * @author Artur Korra
 * @date 23/jan/2020
 *
 */
@Component    
public class AddAdminRoleToDB implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PrivilegeRepository privilegeRepository;
    @Autowired 
    WebSecurityConfig webSecurityConfig;
    @Override
    public void run(String...args) throws Exception {
    	List<Privilege> allPriv = privilegeRepository.findAll();
    	if (allPriv.size()==0) {
    		Privilege privRead = new Privilege();
    		privRead.setName("READ_PRIVILEGE");
    		privilegeRepository.save(privRead);
    		Privilege privWrite = new Privilege();
    		privWrite.setName("WRITE_PRIVILEGE");
    		privilegeRepository.save(privWrite);
		}
    	List<Role> allRole = roleRepository.findAll();
    	if (allRole.size()==0) {
    		Role roleAdmin = new Role();
    		roleAdmin.setName("ROLE_ADMIN");
    		allPriv = privilegeRepository.findAll();
    		roleAdmin.setPrivileges(allPriv);
    		roleRepository.save(roleAdmin);
    		
    		Role roleUser = new Role();
    		roleUser.setName("ROLE_USER");
    		allPriv = privilegeRepository.findAll();
    		List<Privilege> userPriv =new ArrayList<Privilege>();
    		userPriv.add(allPriv.get(0).getName().equals("ROLE_USER")?allPriv.get(0):allPriv.get(1));
    		roleUser.setPrivileges(userPriv);
    		roleRepository.save(roleUser);
		}
    	
    	//if (!userRepository.findByUsername("admin").isPresent()){
    	 if (userRepository.findAll().size()==0){
        	User admin = new User();
            admin.setUsername("admin");
            admin.setLastname("admin");
            admin.setHome("Albania");
            admin.setWork("Task");
            admin.setNumber("+355684388951");
            admin.setPassword(webSecurityConfig.passwordEncoder().encode("admin").toString());
            allRole = roleRepository.findAll();
            admin.setRoles(allRole);
            userRepository.save(admin);
		}
    	
        
    }
}