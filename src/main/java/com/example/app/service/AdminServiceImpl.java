package com.example.app.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.app.mapper.AdminMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

	private final AdminMapper mapper;

	@Override
	public boolean loginByIdAndPass(String id, String pass) {

		if (mapper.selectById(id) == null) {
			return false;
		} else if (!BCrypt.checkpw(pass, mapper.selectById(id).getLoginPass())) {
			return false;
		}
		return true;
	}

}
