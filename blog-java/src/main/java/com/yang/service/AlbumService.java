package com.yang.service;

import java.util.List;

import com.yang.pojo.Album;

public interface AlbumService {
	List<Album> getAlbumList(String userid);
	Album getAlbumListByaname(String userid,String aname);
	int createAlbum(Album album);
	int upDateAlbum(String newname, String adescribe,String oldaname,String userid);
	
}
