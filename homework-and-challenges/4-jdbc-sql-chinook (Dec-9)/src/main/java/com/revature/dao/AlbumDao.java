package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.model.Album;

public interface AlbumDao {
	
	List<Album> getAllAlbums() throws SQLException;
	Album getAlbumById(int albumId) throws SQLException;
	int addAlbum(Album newAlbum) throws SQLException;
	int updateAlbum(Album newAlbum) throws SQLException;
	int deleteAlbum(int albumId) throws SQLException;

}
