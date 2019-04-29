package com.cc.open.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.open.common.SessionCommon;
import com.cc.open.dao.TeacherMapper;
import com.cc.open.domain.Teacher;
import com.cc.open.service.ITeacherService;
import com.cc.open.vo.ResponVO;
import com.cc.open.vo.UserVO;

@Service
public class TeacherServiceImpl implements ITeacherService {
	
	@Autowired
	private TeacherMapper teacherDao;
	
	@Autowired
	private HttpServletRequest request;

	@Override
	public void createTeacherInfo(UserVO data) {
		teacherDao.createTeacherInfo(data);
	}

	@Override
	public ResponVO<Teacher> getTeacherInfo() {

		ResponVO<Teacher> result = new ResponVO<Teacher>();
		result.setSuccess(false);
		UserVO user = SessionCommon.checkUser(request);
		if(user == null) {
			result.setCode("401");
			result.setMessage("请登录");
			return result;
		}
		Teacher teacher = teacherDao.getTeacherInfoByUserId(user.getUserId());
		result.setCode("200");
		result.setSuccess(true);
		result.setData(teacher);
		return result;
	}

	@Override
	public ResponVO<String> updateTeacherInfo(Teacher teacher) {
		ResponVO<String> result = new ResponVO<>();
		result.setSuccess(false);
		UserVO user = SessionCommon.checkUser(request);
		if(user == null) {
			result.setCode("401");
			result.setMessage("请登录");
			return result;
		}
		if(!(user.getUserId().equals(teacher.getUserId()))){
			result.setCode("500");
			result.setMessage("不能修改其他用户信息");
			return result;
		}
		teacherDao.updateTeacherInfoByUserId(teacher);
		result.setCode("200");
		result.setSuccess(true);
		result.setMessage("修改成功");
		return result;
	}

}