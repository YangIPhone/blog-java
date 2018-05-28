package com.yang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yang.mapper.AlbumMapper;
import com.yang.pojo.Album;
import com.yang.service.AlbumService;

@Service
public class AlbumServiceImpl implements AlbumService {
	@Autowired AlbumMapper albumMapper;
	
	@Override
	public List<Album> getAlbumList(String userid) {
		// TODO Auto-generated method stub
		return albumMapper.getAlbumList(userid);
	}

	@Override
	public Album getAlbumListByaname(String userid, String aname) {
		// TODO Auto-generated method stub
		return albumMapper.getAlbumListByaname(userid, aname);
	}
	
	@Override
	public int createAlbum(Album album) {
		// TODO Auto-generated method stub
		return albumMapper.createAlbum(album);
	}

	@Override
	public int upDateAlbum(String newname, String adescribe, String oldaname,String userid) {
		// TODO Auto-generated method stub
		return albumMapper.upDateAlbum(newname,adescribe,oldaname,userid);
	}


}
