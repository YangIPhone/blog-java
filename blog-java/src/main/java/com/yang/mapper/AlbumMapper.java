package com.yang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yang.pojo.Album;

public interface AlbumMapper {
	List<Album> getAlbumList(@Param("userid") String userid);
	Album getAlbumListByaname(@Param("userid") String userid,@Param("aname") String aname);
	int createAlbum(Album album);
	int upDateAlbum(@Param("newaname") String newaname,@Param("adescribe") String adescribe,@Param("oldaname") String oldaname,@Param("userid") String userid);
}
