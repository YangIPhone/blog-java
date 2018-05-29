package com.yang.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yang.pojo.Photo;

public interface PhotosService {
	List<Photo> getPhotosByUidAndAname(String userid,String aname);
	int addPhoto(Photo photo);
	int delPhoto(int photoid);
}
