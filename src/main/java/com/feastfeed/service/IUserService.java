package com.feastfeed.service;

import com.feastfeed.dto.UserDTO;

public interface IUserService {

	UserDTO fetchById(int id);

	void save(UserDTO userDTO);

}