package com.revature.model;

public class Artist {
	
	private int artistId;
	private String artistName;
	
	public Artist() {
		super();
	}

	public Artist(String artistName) {
		super();
		this.artistName = artistName;
	}

	public Artist(int artistId, String artistName) {
		super();
		this.artistId = artistId;
		this.artistName = artistName;
	}

	public int getArtistId() {
		return artistId;
	}

	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	@Override
	public String toString() {
		return "Artist [artistId=" + artistId + ", artistName=" + artistName + "]";
	}
	
}
