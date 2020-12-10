package com.spring.inwoo.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.inwoo.dao.UserDao;
import com.spring.inwoo.vo.UserVo;



@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;

	@Override
	public void insert(UserVo vo) {
		userDao.insert(vo);
		
	}

	@Override
	public int getByEmail(String email) {
		return userDao.getByEmail(email);
	}

	@SuppressWarnings("null")
	@Override
	public UserVo getByEmailAndPassword(String email, String password) {
		UserVo userVo = null;
		HashMap<String, String> map = null;
		map.put("email", email);
		map.put("password", password);
		userVo = userDao.getByEmailAndPassword(map);
		return userVo;
	}

	@Override
	public UserVo getByNo(Long no) {
		return userDao.getByNo(no);
	}

	@Override
	public void update(UserVo vo) {
		userDao.update(vo);
	}
}
















