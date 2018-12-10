package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Album;
import com.revature.model.Artist;

public class AlbumDaoImpl implements AlbumDao {
	
	private Connection conn;

	public AlbumDaoImpl(Connection conn) {
		super();
		this.conn = conn;
	}
	
	public int getNewAlbumId() throws SQLException {
		
		String sqlStmt = "SELECT COUNT(1) FROM ALBUM";
		
		ResultSet rs = this.conn.createStatement().executeQuery(sqlStmt);
		
		if (rs.next()) {
			return rs.getInt("COUNT(1)") + 1;
		}
		else {
			return 1;
		}
		
	}

	@Override
	public List<Album> getAllAlbums() throws SQLException {
		
		List<Album> albumList = new ArrayList<>();
		
		String sqlStmt = "SELECT AB.ALBUMID, AB.TITLE, AR.ARTISTID, AR.NAME FROM ALBUM AB ";
		sqlStmt += "INNER JOIN ARTIST AR ON AB.ARTISTID = AR.ARTISTID";
		
		ResultSet rs = this.conn.createStatement().executeQuery(sqlStmt);
		
		while (rs.next()) {
			Artist a = new Artist(rs.getInt("ARTISTID"), rs.getString("NAME"));
			albumList.add(new Album(rs.getInt("ALBUMID"), rs.getString("TITLE"), a));
		}
		
		return albumList;
		
	}

	@Override
	public Album getAlbumById(int albumId) throws SQLException {
		
		Album selectedAlbum = null;
		
		String sqlStmt = "SELECT AB.ALBUMID, AB.TITLE, AR.ARTISTID, AR.NAME FROM ALBUM AB ";
		sqlStmt += "INNER JOIN ARTIST AR ON AB.ARTISTID = AR.ARTISTID ";
		sqlStmt += "WHERE AB.ALBUMID = ?";
		
		PreparedStatement pstmt = this.conn.prepareStatement(sqlStmt);
		pstmt.setInt(1, albumId);
		
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			Artist a = new Artist(rs.getInt("ARTISTID"), rs.getString("NAME"));
			selectedAlbum = new Album(rs.getInt("ALBUMID"), rs.getString("TITLE"), a);
		}
		
		return selectedAlbum;
		
	}

	@Override
	public int addAlbum(Album newAlbum) throws SQLException {
		
		String sqlStmt = "INSERT INTO ALBUM VALUES ";
		sqlStmt += "(?, ?, ?)";
		
		PreparedStatement pstmt = this.conn.prepareStatement(sqlStmt);
		pstmt.setInt(1, this.getNewAlbumId());
		pstmt.setString(2, newAlbum.getAlbumTitle());
		pstmt.setInt(3, newAlbum.getAlbumArtist().getArtistId());
		
		int rowInserted = pstmt.executeUpdate();

		if (rowInserted > 0) {
			this.conn.commit();
		}
		
		return rowInserted;
		
	}

	@Override
	public int updateAlbum(Album newAlbum) throws SQLException {
		
		String sqlStmt = "UPDATE ALBUM SET TITLE = ?, ARTISTID = ? ";
		sqlStmt += "WHERE ALBUMID = ?";
		
		PreparedStatement pstmt = this.conn.prepareStatement(sqlStmt);
		pstmt.setString(1, newAlbum.getAlbumTitle());
		pstmt.setInt(2, newAlbum.getAlbumArtist().getArtistId());
		pstmt.setInt(3, newAlbum.getAlbumId());
		
		int rowUpdated = pstmt.executeUpdate();
		
		if (rowUpdated > 0) {
			this.conn.commit();
		}
		
		return rowUpdated;
		
	}

	@Override
	public int deleteAlbum(int albumId) throws SQLException {
		
		String sqlStmt = "DELETE FROM ALBUM ";
		sqlStmt += "WHERE ALBUMID = ?";
		
		PreparedStatement pstmt = this.conn.prepareStatement(sqlStmt);
		pstmt.setInt(1, albumId);
		
		int rowDeleted = pstmt.executeUpdate();
		
		if (rowDeleted > 0) {
			this.conn.commit();
		}
		
		return rowDeleted;
		
	}

}
