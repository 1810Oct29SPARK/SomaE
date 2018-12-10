package com.revature.main;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.revature.dao.AlbumDaoImpl;
import com.revature.dao.ArtistDaoImpl;
import com.revature.dao.TrackDaoImpl;
import com.revature.model.Album;
import com.revature.model.Artist;
import com.revature.model.Genre;
import com.revature.model.Track;
import com.revature.util.ChinookUtilities;

public class ChinookLauncher {

	public static void main(String[] args) {
		
		// Setting up a connection to Chinook Oracle database
		Connection conn = null;
		
		try {
			System.out.println("Getting connection to database...");
			conn = ChinookUtilities.getConnection("connection.properties");
			System.out.println("Connection established");
			conn.setAutoCommit(false);
			System.out.println("Auto-commit is set to false\n");
		}
		catch (IOException | SQLException e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}
		
		// Access the database
		try {
			System.out.println("Transaction started\n");
//			printAllArtists(conn);
//			printOneArtist(conn, 200);
//			addOneArtist(conn, "Falcom JDK");
			
//			printAllAlbums(conn);
//			printOneAlbum(conn, 348);
//			addOneAlbum(conn, "Sen No Kiseki III Original Soundtrack", 277);
//			updateAlbum(conn);
//			deleteOneAlbum(conn, 350);
			
//			printOneTrack(conn, 1);
			printAlbumTracks(conn, 350);
//			deleteTracks(conn, 3504, 3600);
//			addMoreTracks(conn, 5);
//			addATrackToPlaylist(conn, 19, 3504);
			
//			try {
//				loadTracksFromFile(conn, "trackList.txt");
//			}
//			catch (IOException e) {
//				System.out.println("Error: " + e.getMessage());
//				System.exit(-1);
//			}
		}
		catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
//			e.printStackTrace();
			System.exit(-1);
		}
		finally {
			System.out.println("\nConnection closed");
			System.exit(0);
		}

	}
	
	// Test methods
	public static void printAllArtists(Connection conn) throws SQLException {
		ArtistDaoImpl artistAccess = new ArtistDaoImpl(conn);
		List<Artist> allArtists = artistAccess.getAllArtist();
		for (Artist a: allArtists) {
			System.out.println(a);
		}
	}
	
	public static void printOneArtist(Connection conn, int artistId) throws SQLException {
		ArtistDaoImpl artistAccess = new ArtistDaoImpl(conn);
		System.out.println(artistAccess.getArtistById(artistId));
	}
	
	public static void addOneArtist(Connection conn, String artistName) throws SQLException {
		ArtistDaoImpl artistAccess = new ArtistDaoImpl(conn);
		Artist newArtist = new Artist(artistName);
		System.out.println(artistAccess.addArtist(newArtist) + " row inserted");
	}
	
	public static void printAllAlbums(Connection conn) throws SQLException {
		AlbumDaoImpl albumAccess = new AlbumDaoImpl(conn);
		List<Album> albumList = albumAccess.getAllAlbums();
		for (Album a: albumList) {
			System.out.println(a);
		}
	}
	
	public static void printOneAlbum(Connection conn, int albumId) throws SQLException {
		AlbumDaoImpl albumAccess = new AlbumDaoImpl(conn);
		System.out.println(albumAccess.getAlbumById(albumId));
	}
	
	public static void addOneAlbum(Connection conn, String albumTitle, int artistId) throws SQLException {
		ArtistDaoImpl artistAccess = new ArtistDaoImpl(conn);
		AlbumDaoImpl albumAccess = new AlbumDaoImpl(conn);
		Album newAlbum = new Album(albumTitle, artistAccess.getArtistById(artistId));
		System.out.println(albumAccess.addAlbum(newAlbum) + " row inserted");
	}
	
	public static void updateAlbum(Connection conn) throws SQLException {
		AlbumDaoImpl albumAccess = new AlbumDaoImpl(conn);
		Album a = albumAccess.getAlbumById(349);
		a.setAlbumTitle("Hungarian Rhapsodies, S. 244");
		System.out.println(albumAccess.updateAlbum(a) + " row updated");
	}
	
	public static void deleteOneAlbum(Connection conn, int albumId) throws SQLException {
		AlbumDaoImpl albumAccess = new AlbumDaoImpl(conn);
		System.out.println(albumAccess.deleteAlbum(albumId) + " row deleted");
	}
	
	public static void printOneTrack(Connection conn, int trackId) throws SQLException {
		TrackDaoImpl trackAccess = new TrackDaoImpl(conn);
		System.out.println(trackAccess.getTrackById(trackId));
	}
	
	public static void printAlbumTracks(Connection conn, int albumId) throws SQLException {
		TrackDaoImpl trackAccess = new TrackDaoImpl(conn);
		List<Track> trackList = trackAccess.getTracksByAlbumId(albumId);
		for (Track t: trackList) {
			System.out.println(t);
		}
	}
	
	public static void addOneTrack(Connection conn, String trackName, String composer, int albumId, int genreId) throws SQLException {
		TrackDaoImpl trackAccess = new TrackDaoImpl(conn);
		Album a = new Album(albumId);
		Genre g = new Genre(genreId);
		System.out.println(trackAccess.addOneTrack(new Track(trackName, composer, a, g)) + " row inserted");
	}
	
	public static void deleteTracks(Connection conn, int startId, int endId) throws SQLException {
		TrackDaoImpl trackAccess = new TrackDaoImpl(conn);
		System.out.println(trackAccess.deleteTracksWithinRange(startId, endId) + " row(s) deleted");
	}
	
	public static void addMoreTracks(Connection conn, int amount) throws SQLException {
		TrackDaoImpl trackAccess = new TrackDaoImpl(conn);
		
		List<Track> trackList = new ArrayList<>();
		for (int i = 0; i < amount; ++i) {
			Album a = new Album(1);
			Genre g = new Genre(1);
			trackList.add(new Track((new Integer(i)).toString(), (new Integer(i)).toString(), a, g));
		}
		
		System.out.println("Total: " + trackAccess.addTracks(trackList) + " row(s) inserted");
	}
	
	public static void loadTracksFromFile(Connection conn, String fileName) throws IOException, SQLException {

		TrackDaoImpl trackAccess = new TrackDaoImpl(conn);
		List<Track> trackList = new ArrayList<>();
		
		FileReader fr = new FileReader(fileName);
		StringBuilder rawTrackData = new StringBuilder("");
		int data = 0;
		
		while ((data = fr.read()) != -1) {
			rawTrackData.append((char)data);
		}
		
		fr.close();
		
		if (rawTrackData.charAt(0) == '#') {
			System.out.println("No data found");
			return;
		}
		
		Pattern tagPattern = Pattern.compile("__(.*)__", Pattern.DOTALL);
		Matcher matchData = tagPattern.matcher(rawTrackData);
		matchData.find();
		String refinedData = matchData.group(1).trim();
		
		String[] rawTracks = refinedData.split("[\\s]*;[\\s]*");
		for (String track: rawTracks) {
			String[] trackMeta = track.split("[\\s]\\|[\\s]*");
			Album a = new Album(Integer.parseInt(trackMeta[1]));
			Genre g = new Genre(Integer.parseInt(trackMeta[2]));
			trackList.add(new Track(trackMeta[0], trackMeta[3], a, g));
		}
		
		if (!trackList.isEmpty()) {
			System.out.println(trackAccess.addTracks(trackList) + " tracks added");
		}
		
	}
	
	public static void addATrackToPlaylist(Connection conn, int playlistId, int trackId) throws SQLException {
		TrackDaoImpl trackAccess = new TrackDaoImpl(conn);
		System.out.println(trackAccess.addTrackToPlaylist(playlistId, trackId) + " record added");
	}

}
