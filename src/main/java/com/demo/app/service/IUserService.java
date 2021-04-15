package com.demo.app.service;

import java.util.List;

import com.demo.app.entity.User;
/**
 * Class name: IUserService
 *
 * Description: IUserService 
 * 
 *
 * Company: Task
 *
 * @author Artur Korra
 * @date 23/jan/2020
 *
 */
public interface IUserService {
	public User getUser(String username);
	public List<User> findByUsernameList(String username);//query return list
}
