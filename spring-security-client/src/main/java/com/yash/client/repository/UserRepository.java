package com.yash.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.client.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{


}
