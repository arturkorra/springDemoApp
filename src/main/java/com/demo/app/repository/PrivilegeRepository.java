package com.demo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.app.entity.Privilege;
/**
 * Class name: PrivilegeRepository
 *
 * Description: PrivilegeRepository 
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
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

}