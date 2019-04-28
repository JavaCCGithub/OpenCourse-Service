package com.cc.open.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cc.open.common.ConstantCommon;
import com.cc.open.service.ICourseDataService;
import com.cc.open.utils.FileUploadUtil;
import com.cc.open.vo.CourseDataVO;
import com.cc.open.vo.ResponVO;

@RestController
@RequestMapping(value = "/course/data")
public class CourseDataController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ICourseDataService courseDataService;
	
	@RequestMapping(value = "/upload/video", method = RequestMethod.POST, produces = "application/json")
	public ResponVO<CourseDataVO> uploadCourseVideo(@RequestParam("courseId") String courseId, @RequestParam("file") MultipartFile file){
		ResponVO<CourseDataVO> result = new ResponVO<>();
		result.setCode("500");
		result.setSuccess(false);
		if(file == null){
			result.setMessage("文件上传失败");
			return result;
	    }
		return courseDataService.uploadCourseDataOne(courseId,ConstantCommon.SAVE_URL_VIDEOS,file);	
	}
	
	@RequestMapping(value = "/upload/doc", method = RequestMethod.POST, produces = "application/json")
	public ResponVO<CourseDataVO> uploadCourseDoc(@RequestParam("courseId") String courseId, @RequestParam("file") MultipartFile file){
		ResponVO<CourseDataVO> result = new ResponVO<>();
		result.setCode("500");
		result.setSuccess(false);
		if(file == null){
			result.setMessage("文件上传失败");
			return result;
	    }
		return courseDataService.uploadCourseDataOne(courseId,ConstantCommon.SAVE_URL_DOC,file);	
	}
	
	@RequestMapping(value = "/upload/files/{courseId}", method = RequestMethod.POST, produces = "application/json")
	public ResponVO<List<String>> uploadCourseDatas(@PathVariable("courseId") String courseId, @RequestParam("file") MultipartFile[] files){
		ResponVO<List<String>> result = new ResponVO<>();
		result.setCode("500");
		result.setSuccess(false);
		if(files == null){
			result.setMessage("文件上传失败");
			return result;
	    }
		return courseDataService.uploadCourseDatas(courseId,files);	
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json")
	public ResponVO<String> updateCourseData(@RequestBody CourseDataVO courseDataVO){		
		return courseDataService.updateCourseData(courseDataVO);
	}
	
	@RequestMapping(value = "/find/{courseId}", method = RequestMethod.GET, produces = "application/json")
	public ResponVO<CourseDataVO> getCourseData(@PathVariable("courseId") String courseId){		
		return courseDataService.getCourseData(courseId);
	}
	
	@RequestMapping(value = "/delect", method = RequestMethod.POST, produces = "application/json")
	public ResponVO<String> deleteCourseData(@RequestBody CourseDataVO courseDataVO){		
		return courseDataService.deleteCourseDataUrl(courseDataVO);
	}
}
