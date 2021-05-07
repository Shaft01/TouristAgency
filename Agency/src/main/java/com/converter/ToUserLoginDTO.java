package com.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dto.UserDTO;
import com.model.User;

@Component
public class ToUserLoginDTO implements Converter<User, UserDTO> {

	@Override
	public UserDTO convert(User source) {
		return new UserDTO(source);
	}
	
	public List<UserDTO> convert(List<User> source) {
		List<UserDTO> userDTOs = new ArrayList<>(source.size());
		for (User user : source) {
			userDTOs.add(convert(user));
		}
		
		return userDTOs;
	}
}
