package com.lcwd.user.service.services;

import java.util.List;

import com.lcwd.user.service.entities.User;

public interface UserService 
{
	
	User saveUser(User user);
	
	List<User> getAlluser();
	
	User getUser(String userId);

}
