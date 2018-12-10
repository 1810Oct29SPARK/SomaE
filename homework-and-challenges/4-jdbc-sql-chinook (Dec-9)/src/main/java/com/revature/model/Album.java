package com.revature.model;

public class Album {
	
	private int albumId;
	private String albumTitle;
	private Artist albumArtist;
	
	public Album(int albumId) {
		super();
		this.albumId = albumId;
	}

	public Album(int albumId, String albumTitle) {
		super();
		this.albumId = albumId;
		this.albumTitle = albumTitle;
	}

	public Album(String albumTitle, Artist albumArtist) {
		super();
		this.albumTitle = albumTitle;
		this.albumArtist = albumArtist;
	}

	public Album(int albumId, String albumTitle, Artist albumArtist) {
		super();
		this.albumId = albumId;
		this.albumTitle = albumTitle;
		this.albumArtist = albumArtist;
	}

	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public String getAlbumTitle() {
		return albumTitle;
	}

	public void setAlbumTitle(String albumTitle) {
		this.albumTitle = albumTitle;
	}

	public Artist getAlbumArtist() {
		return albumArtist;
	}

	public void setAlbumArtist(Artist albumArtist) {
		this.albumArtist = albumArtist;
	}

	@Override
	public String toString() {
		return "Album [albumId=" + albumId + ", albumTitle=" + albumTitle + ", albumArtist=" + albumArtist.getArtistName() + "]";
	}

}
