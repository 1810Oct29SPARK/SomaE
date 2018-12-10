package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.revature.model.Album;
import com.revature.model.Genre;
import com.revature.model.Track;

public class TrackDaoImpl implements TrackDao {
	
	private Connection conn;
	

	public TrackDaoImpl(Connection conn) {
		super();
		this.conn = conn;
	}
	
	public int getNewTrackId() throws SQLException {
		
		String sqlStmt = "SELECT COUNT(1) FROM TRACK";
		
		ResultSet rs = this.conn.createStatement().executeQuery(sqlStmt);
		
		if (rs.next()) {
			return rs.getInt("COUNT(1)") + 1;
		}
		else {
			return 1;
		}
		
	}

	@Override
	public List<Track> getTracksByAlbumId(int albumId) throws SQLException {
		
		List<Track> trackList = new ArrayList<>();

		String sqlStmt = "SELECT T.TRACKID, T.NAME AS TN, T.COMPOSER, A.TITLE, G.GENREID, G.NAME AS GN " + 
				"FROM TRACK T " + 
				"INNER JOIN ALBUM A " + 
				"ON T.ALBUMID = A.ALBUMID " + 
				"INNER JOIN GENRE G " + 
				"ON T.GENREID = G.GENREID " + 
				"WHERE A.ALBUMID = ?";
		
		PreparedStatement pstmt = this.conn.prepareStatement(sqlStmt);
		pstmt.setInt(1, albumId);
		
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			Album a = new Album(albumId, rs.getString("TITLE"));
			Genre g = new Genre(rs.getInt("GENREID"), rs.getString("GN"));
			trackList.add(new Track(rs.getInt("TRACKID"), rs.getString("TN"), rs.getString("COMPOSER"), a, g));
		}
		Collections.reverse(trackList);
		
		return trackList;
		
	}

	@Override
	public Track getTrackById(int trackId) throws SQLException {
		
		Track selectedTrack = null;
		
		String sqlStmt = "SELECT T.NAME AS TN, T.COMPOSER, A.ALBUMID, A.TITLE, G.GENREID, G.NAME AS GN " + 
				"FROM TRACK T " + 
				"INNER JOIN ALBUM A " + 
				"ON T.ALBUMID = A.ALBUMID " + 
				"INNER JOIN GENRE G " + 
				"ON T.GENREID = G.GENREID " + 
				"WHERE T.TRACKID = ?";
		
		PreparedStatement pstmt = this.conn.prepareStatement(sqlStmt);
		pstmt.setInt(1, trackId);
		
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			Album a = new Album(rs.getInt("ALBUMID"), rs.getString("TITLE"));
			Genre g = new Genre(rs.getInt("GENREID"), rs.getString("GN"));
			selectedTrack = new Track(trackId, rs.getString("TN"), rs.getString("COMPOSER"), a, g);
		}
		
		return selectedTrack;
		
	}

	@Override
	public int addOneTrack(Track newTrack) throws SQLException {
		
		String sqlStmt = "INSERT INTO TRACK " + 
				"(TRACKID, NAME, ALBUMID, GENREID, COMPOSER) " + 
				"VALUES (?, ?, ?, ?, ?)";
		
		PreparedStatement pstmt = this.conn.prepareStatement(sqlStmt);
		pstmt.setInt(1, this.getNewTrackId());
		pstmt.setString(2, newTrack.getTrackName());
		pstmt.setInt(3, newTrack.getTrackAlbum().getAlbumId());
		pstmt.setInt(4, newTrack.getTrackGenre().getGenreId());
		pstmt.setString(5, newTrack.getComposer());

		int rowInserted = pstmt.executeUpdate();

		if (rowInserted > 0) {
			this.conn.commit();
		}
		
		return rowInserted;
		
	}

	@Override
	public int addTracks(List<Track> trackList) throws SQLException {
		
		int rowInserted = 0;
		
		for (Track t: trackList) {
			rowInserted += addOneTrack(t);
		}
		
		return rowInserted;
		
	}

	@Override
	public int deleteTracksWithinRange(int startId, int endId) throws SQLException {
		
		String sqlStmt = "DELETE FROM TRACK " + 
				"WHERE TRACKID BETWEEN ? AND ?";
		
		PreparedStatement pstmt = this.conn.prepareStatement(sqlStmt);
		pstmt.setInt(1, startId);
		pstmt.setInt(2, endId);
		
		int rowDeleted = pstmt.executeUpdate();
		
		if (rowDeleted > 0) {
			this.conn.commit();
		}
		
		return rowDeleted;
		
	}

	@Override
	public int addTrackToPlaylist(int playlistId, int trackId) throws SQLException {
		
		String sqlStmt = "{CALL ADD_TRACK_TO_PLAYLIST(?, ?, ?)}";
		
		CallableStatement cstmt = this.conn.prepareCall(sqlStmt);
		cstmt.setInt(1, playlistId);
		cstmt.setInt(2, trackId);
		cstmt.registerOutParameter(3, java.sql.Types.INTEGER);
		
		if (!cstmt.execute()) {
			this.conn.commit();
		}
		
		return cstmt.getInt(3);
		
	}

}
