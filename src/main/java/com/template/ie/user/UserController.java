package com.template.ie.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.template.ie.user.model.User;

@RestController
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(UserController.class); 
	
	@Autowired
	UserMapper userMapper;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User queryUserById(@PathVariable Long id) {
		User user = userMapper.queryUserById(id);
		logger.info(user.toString());
		return user;
	}
}