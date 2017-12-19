package com.sf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;
import com.sf.mapper.UserMapper;
import com.sf.model.User;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	public User queryUser(){
		User record=new User();
		record.setId(1L);
		User user= userMapper.selectOne(record);
		return  user;
	}
	public void saveUser(User record){
		userMapper.insert(record);
	}
	public List<User> queryUsers(User user){
		 Example example = new Example(User.class);
//	     example.setOrderByClause("id DESC");
	     // TODO 分页暂时不做
	     return this.userMapper.selectByExample(example);
	}
}
