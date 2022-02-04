package com.vwits.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vwits.gateway.model.JwtToken;
import com.vwits.gateway.model.User;

@Repository
public interface JwtTokenRepository extends JpaRepository<JwtToken, String> {

}
