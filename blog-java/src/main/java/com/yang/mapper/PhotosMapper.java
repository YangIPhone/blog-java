package com.yang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yang.pojo.Photo;

public interface PhotosMapper {
	 List<Photo> getPhotosByUidAndAname(@Param("userid")String userid,@Param("aname")String aname);
	 Photo getAlbumCover(@Param("userid")String userid,@Param("aname")String aname);
	 int addPhoto(Photo photo);
	 int delPhoto(@Param("photoid")int photoid);
}
