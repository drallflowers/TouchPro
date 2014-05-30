package com.touchpro;

/**
 * Created a HighScore object to help sort the score of the players
 * @author Darrall Flowers
 */
public class HighScore implements Comparable<HighScore> {
	String username = "";
	int score = 0;

	/**
	 * 
	 * @param username
	 * @param score
	 */
	public HighScore(String username, int score) {
		this.username = username;
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public String getUser() {
		return username;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setUser(String user) {
		this.username = user;
	}

	@Override
	public int compareTo(HighScore another) {
		if (getScore() < another.getScore()) {
			return -1;
		} else if (getScore() > another.getScore()) {
			return 1;
		}
		return 0;
	}

}
