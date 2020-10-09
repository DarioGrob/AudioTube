package com.modul152.projekt.model;

import com.vaadin.flow.templatemodel.AllowClientUpdates;

import java.util.List;

public class Playlist {

	private String name;
	private String controllButton;
	private List<Song> songs;
	private Song currentSong;

	public Playlist(String name, List<Song> songs, String controllButton, Song currentSong) {
		this.name = name;
		this.controllButton = controllButton;
		this.songs = songs;
		this.currentSong = currentSong;
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

	public String getControllButton() {
		return controllButton;
	}

	@AllowClientUpdates
	public void setControllButton(String controllButton) {
		this.controllButton = controllButton;
	}

	public Song getCurrentSong() {
		return currentSong;
	}

	public void setCurrentSong(Song currentSong) {
		this.currentSong = currentSong;
	}
}
