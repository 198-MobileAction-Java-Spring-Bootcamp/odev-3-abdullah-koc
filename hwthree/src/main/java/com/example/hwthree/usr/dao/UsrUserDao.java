package com.example.hwthree.usr.dao;

import com.example.hwthree.usr.entity.UsrUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsrUserDao extends JpaRepository<UsrUser, Long> {

    UsrUser findByUsername(String username);
}
