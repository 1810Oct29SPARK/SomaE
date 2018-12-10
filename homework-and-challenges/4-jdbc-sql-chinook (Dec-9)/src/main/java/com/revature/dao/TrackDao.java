package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.model.Track;

public interface TrackDao {
	
	List<Track> getTracksByAlbumId(int albumId) throws SQLException;
	Track getTrackById(int trackId) throws SQLException;
	int addOneTrack(Track newTrack) throws SQLException;
	int addTracks(List<Track> trackList) throws SQLException;
	int deleteTracksWithinRange(int startId, int endId) throws SQLException;
	int addTrackToPlaylist(int playlistId, int trackId) throws SQLException;
	
}
