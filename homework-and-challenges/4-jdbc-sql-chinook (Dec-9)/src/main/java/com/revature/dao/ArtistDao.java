package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.model.Artist;

public interface ArtistDao {
	
	List<Artist> getAllArtist() throws SQLException;
	Artist getArtistById(int artistId) throws SQLException;
	int addArtist(Artist newArtist) throws SQLException;

}
