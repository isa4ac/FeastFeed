package com.feastfeed.dao;

import com.feastfeed.dto.UserDTO;

public interface IUserDAO {

	boolean save(UserDTO userDTO) throws Exception;
	
}
