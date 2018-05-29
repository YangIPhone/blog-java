package com.yang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yang.mapper.PhotosMapper;
import com.yang.pojo.Photo;
import com.yang.service.PhotosService;

@Service
public class PhotosServiceImpl implements PhotosService {
	@Autowired
	PhotosMapper photosMapper;
	@Override
	public List<Photo> getPhotosByUidAndAname(String userid, String aname) {
		// TODO Auto-generated method stub
		return photosMapper.getPhotosByUidAndAname(userid, aname);
	}
	@Override
	public Photo getAlbumCover(String userid, String aname) {
		// TODO Auto-generated method stub
		return photosMapper.getAlbumCover(userid, aname);
	}
	@Override
	public int addPhoto(Photo photo) {
		// TODO Auto-generated method stub
		return photosMapper.addPhoto(photo);
	}
	@Override
	public int delPhoto(int photoid) {
		// TODO Auto-generated method stub
		return photosMapper.delPhoto(photoid);
	}
}
