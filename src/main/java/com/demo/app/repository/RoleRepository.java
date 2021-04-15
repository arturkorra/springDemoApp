package com.demo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.app.entity.Role;
/**
 * Class name: RoleRepository
 *
 * Description: RoleRepository 
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
public interface RoleRepository extends JpaRepository<Role, Long> {

}