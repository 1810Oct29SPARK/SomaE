package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Artist;

public class ArtistDaoImpl implements ArtistDao {
	
	private Connection conn;

	public ArtistDaoImpl(Connection conn) {
		super();
		this.conn = conn;
	}
	
	public int getNewArtistId() throws SQLException {
		
		String sqlStmt = "SELECT COUNT(1) FROM ARTIST";
		
		ResultSet rs = conn.createStatement().executeQuery(sqlStmt);
		
		if (rs.next()) {
			return rs.getInt("COUNT(1)") + 1;
		}
		else {
			return 1;
		}
		
	}

	@Override
	public List<Artist> getAllArtist() throws SQLException {
		
		List<Artist> artistList = new ArrayList<>();
		
		// SQL query to retrieve all artists from table
		String sqlStmt = "SELECT ARTISTID, NAME FROM ARTIST";
		
		// Obtain a result set by executing the query
		ResultSet rs = (this.conn.createStatement()).executeQuery(sqlStmt);
		
		// Iterate through the result set and fill the list
		while (rs.next()) {
			int ArtistId = rs.getInt("ARTISTID");
			String ArtistName = rs.getString("NAME");
			artistList.add(new Artist(ArtistId, ArtistName));
		}
		
		return artistList;
	}

	@Override
	public Artist getArtistById(int artistId) throws SQLException {
		
		Artist selectedArtist = null;
		
		String sqlStmt = "SELECT ARTISTID, NAME FROM ARTIST ";
		sqlStmt += "WHERE ARTISTID = ?";
		
		PreparedStatement pstmt = this.conn.prepareStatement(sqlStmt);
		pstmt.setInt(1, artistId);
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			selectedArtist = new Artist(rs.getInt("ARTISTID"), rs.getString("NAME"));
		}
		
		return selectedArtist;
		
	}

	@Override
	public int addArtist(Artist newArtist) throws SQLException {
		
		String sqlStmt = "INSERT INTO ARTIST (ARTISTID, NAME) ";
		sqlStmt += "VALUES (?, ?)";
		
		PreparedStatement pstmt = this.conn.prepareStatement(sqlStmt);
		pstmt.setInt(1, this.getNewArtistId());
		pstmt.setString(2, newArtist.getArtistName());
		
		int rowInserted = pstmt.executeUpdate();
		
		if (rowInserted > 0) {
			this.conn.commit();
		}
		
		return rowInserted;
		
	}

}
