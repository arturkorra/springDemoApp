package com.demo.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.app.entity.User;
/**
 * Class name: UserRepository
 *
 * Description: UserRepository 
 * 
 *
 * Company: Task
 *
 * @author Artur Korra
 * @date 23/jan/2020
 *
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
	public Optional<User> findByUsername(String username);//query must return one result

	public List<User> findByusername(String username);
}