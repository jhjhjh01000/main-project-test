package com.main.config.auth;

import org.springframework.security.core.userdetails.UserDetails;

public interface PrincipalDetails1 extends UserDetails {

    String getEmail();

}
