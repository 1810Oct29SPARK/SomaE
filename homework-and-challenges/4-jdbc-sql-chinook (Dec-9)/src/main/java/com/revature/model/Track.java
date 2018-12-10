package com.revature.model;

public class Track {
	
	private int trackId;
	private String trackName;
	private String composer;
	private Album trackAlbum;
	private Genre trackGenre;
	
	public Track(String trackName, String composer, Album trackAlbum, Genre trackGenre) {
		super();
		this.trackName = trackName;
		this.composer = composer;
		this.trackAlbum = trackAlbum;
		this.trackGenre = trackGenre;
	}

	public Track(int trackId, String trackName, String composer, Album trackAlbum, Genre trackGenre) {
		super();
		this.trackId = trackId;
		this.trackName = trackName;
		this.composer = composer;
		this.trackAlbum = trackAlbum;
		this.trackGenre = trackGenre;
	}

	public int getTrackId() {
		return trackId;
	}

	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public Album getTrackAlbum() {
		return trackAlbum;
	}

	public void setTrackAlbum(Album trackAlbum) {
		this.trackAlbum = trackAlbum;
	}

	public Genre getTrackGenre() {
		return trackGenre;
	}

	public void setTrackGenre(Genre trackGenre) {
		this.trackGenre = trackGenre;
	}

	@Override
	public String toString() {
		return "Track [trackId=" + trackId + ", trackName=" + trackName + ", composer=" + composer + ", trackAlbum="
				+ trackAlbum.getAlbumTitle() + ", trackGenre=" + trackGenre.getGenreName() + "]";
	}

}
