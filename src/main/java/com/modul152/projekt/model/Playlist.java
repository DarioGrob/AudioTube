package com.modul152.projekt.model;

import com.vaadin.flow.templatemodel.AllowClientUpdates;

import java.util.List;

public class Playlist {

	private String name;
	private List<Song> songs;
	private Song currentSong;

	public Playlist(String name, List<Song> songs) {
		this.name = name;
		this.songs = songs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public Song getCurrentSong() {
		return currentSong;
	}

	public void setCurrentSong(Song currentSong) {
		this.currentSong = currentSong;
	}
}
