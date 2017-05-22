package com.neu.coe;

public class Movie {
	
	int movieid;
	String title;
	String genre;
	
	public Movie(int movieid, String title, String genre){
		this.movieid = movieid;
		this.title = title;
		this.genre = genre;
	}

	public Movie() {
		super();
	}

	public int getMovieid() {
		return movieid;
	}

	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	
	
	
}
