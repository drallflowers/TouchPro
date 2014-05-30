package com.touchpro;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class GetHighScoresActivity extends Activity {
	ArrayList<HighScore> ListOfHighScores = new ArrayList<HighScore>();

	// Created a HighScore object to help sort the score of the players
	public class HighScore implements Comparable<HighScore> {
		String username = "";
		int score = 0;

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

	/*
	 * Parses JSON on server then uploads scores to a sheet for the app
	 */
	public void run() throws IOException {

		// TODO:insert proper URL from Ben's website
		URL url = new URL("http://127.0.0.1/HighScores/get_highscores.php");
		try {
			InputStream is = url.openStream();
			JSONParser parser = new JSONParser();
			JSONArray json = (JSONArray) parser.parse(IOUtils.toString(is));

			// loop thru JSON & store inside a list
			for (int i = 0; i < json.size() - 1; i++) {
				JSONObject x = (JSONObject) json.get(i);
				String user = x.get("username").toString();
				int score = (int) x.get("score");
				HighScore temp = new HighScore(user, score);
				ListOfHighScores.add(temp);

			}
			Collections.sort(ListOfHighScores);

			// TODO:update cells

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get_highscores_activity);
		final Button gameButton = (Button) findViewById(R.id.viewHighScoresButton);

		// Big O(n)linear with time
		// loops thru List of high scores for 10 of cells
		// if 10 high scoring players do not exist input a dummy value
		StringBuilder sb = new StringBuilder("cnt");
		for (int i = 0; i < 11; i++) {
			if (ListOfHighScores.get(i).equals(null)
					|| ListOfHighScores.get(i).equals(" ")) {
				HighScore dummyValue = new HighScore("no player", 0);
				ListOfHighScores.add(dummyValue);
			}

		}

		TableLayout table = new TableLayout(this);

		table.setStretchAllColumns(true);
		table.setShrinkAllColumns(true);

		TableRow rowTitle = new TableRow(this);
		rowTitle.setGravity(Gravity.CENTER_HORIZONTAL);

		TableRow rankLabels = new TableRow(this);
		TableRow rank1 = new TableRow(this);
		TableRow rank2 = new TableRow(this);
		TableRow rank3 = new TableRow(this);
		// TODO: add rows
		TableRow rank4 = new TableRow(this);
		TableRow rank5 = new TableRow(this);
		TableRow rank6 = new TableRow(this);

		// TODO: add rows
		TableRow rank7 = new TableRow(this);
		TableRow rank8 = new TableRow(this);
		TableRow rank9 = new TableRow(this);
		// TODO:add row
		TableRow rank10 = new TableRow(this);

		rank3.setGravity(Gravity.CENTER);

		TextView empty = new TextView(this);

		// loop thru array and build list by rank
		// title column/row
		TextView title = new TextView(this);
		// HighScores hs = new HighScore();
		title.setText("HighScores");

		title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
		title.setGravity(Gravity.CENTER);
		title.setTypeface(Typeface.SERIF, Typeface.BOLD);

		TableRow.LayoutParams params = new TableRow.LayoutParams();
		params.span = 6;

		rowTitle.addView(title, params);

		// labels column
		TextView PlayerLabel = new TextView(this);
		PlayerLabel.setText("Player");
		PlayerLabel.setTypeface(Typeface.DEFAULT_BOLD);

		TextView conditionsLabel = new TextView(this);
		conditionsLabel.setText("Conditions");
		conditionsLabel.setTypeface(Typeface.DEFAULT_BOLD);

		rankLabels.addView(empty);
		rank1.addView(PlayerLabel);

		rank3.addView(conditionsLabel);

		// day 1 column
		TextView day1Label = new TextView(this);

		day1Label.setText("Players:");
		day1Label.setTypeface(Typeface.SERIF, Typeface.BOLD);

		TextView cell1 = new TextView(this);
		TextView cell2 = new TextView(this);
		TextView cell3 = new TextView(this);
		TextView cell4 = new TextView(this);
		TextView cell5 = new TextView(this);
		TextView cell6 = new TextView(this);
		TextView cell7 = new TextView(this);
		TextView cell8 = new TextView(this);
		TextView cell9 = new TextView(this);
		TextView cell10 = new TextView(this);

		// do this 10 times
		cell1.setText("ListOfHighScores.get(0).username");
		cell1.setGravity(Gravity.CENTER_HORIZONTAL);

		cell2.setText("ListOfHighScores.get(1).username");
		cell2.setGravity(Gravity.CENTER_HORIZONTAL);

		cell3.setText("ListOfHighScores.get(2).username");
		cell3.setGravity(Gravity.CENTER_HORIZONTAL);

		cell4.setText("ListOfHighScores.get(3).username");
		cell4.setGravity(Gravity.CENTER_HORIZONTAL);

		cell5.setText("ListOfHighScores.get(4).username");
		cell5.setGravity(Gravity.CENTER_HORIZONTAL);

		cell6.setText("ListOfHighScores.get(5).username");
		cell6.setGravity(Gravity.CENTER_HORIZONTAL);

		cell7.setText("ListOfHighScores.get(6).username");
		cell7.setGravity(Gravity.CENTER_HORIZONTAL);

		cell8.setText("ListOfHighScores.get(7).username");
		cell8.setGravity(Gravity.CENTER_HORIZONTAL);

		cell9.setText("ListOfHighScores.get(8).username");
		cell9.setGravity(Gravity.CENTER_HORIZONTAL);

		cell10.setText("ListOfHighScores.get(9).username");
		cell10.setGravity(Gravity.CENTER_HORIZONTAL);

		rankLabels.addView(day1Label);
		rank1.addView(cell1);
		rank2.addView(cell2);
		rank3.addView(cell3);
		rank4.addView(cell4);
		rank5.addView(cell5);
		rank6.addView(cell6);

		// TODO: add rows
		rank7.addView(cell7);
		rank8.addView(cell8);
		rank9.addView(cell9);
		// TODO:add row
		rank10.addView(cell10);

		// Scores column
		TextView playerScores = new TextView(this);
		playerScores.setText("Scores");
		playerScores.setTypeface(Typeface.SERIF, Typeface.BOLD);

		TextView Score1 = new TextView(this);
		Score1.setText("ListOfHighScores.get(0).getScores()");
		Score1.setGravity(Gravity.CENTER_HORIZONTAL);

		TextView Score2 = new TextView(this);
		Score2.setText("ListOfHighScores.get(1).getScores()");
		Score2.setGravity(Gravity.CENTER_HORIZONTAL);

		TextView Score3 = new TextView(this);
		Score3.setText("ListOfHighScores.get(2).getScores()");
		Score3.setGravity(Gravity.CENTER_HORIZONTAL);

		TextView Score4 = new TextView(this);
		Score4.setText("ListOfHighScores.get(3).getScores()");
		Score4.setGravity(Gravity.CENTER_HORIZONTAL);

		TextView Score5 = new TextView(this);
		Score5.setText("ListOfHighScores.get(4).getScores()");
		Score5.setGravity(Gravity.CENTER_HORIZONTAL);

		TextView Score6 = new TextView(this);
		Score6.setText("ListOfHighScores.get(5).getScores()");
		Score6.setGravity(Gravity.CENTER_HORIZONTAL);

		TextView Score7 = new TextView(this);
		Score7.setText("ListOfHighScores.get(6).getScores()");
		Score7.setGravity(Gravity.CENTER_HORIZONTAL);

		TextView Score8 = new TextView(this);
		Score8.setText("ListOfHighScores.get(7).getScores()");
		Score8.setGravity(Gravity.CENTER_HORIZONTAL);

		TextView Score9 = new TextView(this);
		Score9.setText("ListOfHighScores.get(8).getScores()");
		Score9.setGravity(Gravity.CENTER_HORIZONTAL);

		TextView Score10 = new TextView(this);
		Score10.setText("ListOfHighScores.get(9).getScores()");
		Score10.setGravity(Gravity.CENTER_HORIZONTAL);

		TextView day4Low = new TextView(this);
		day4Low.setText("ListOfHighScores.get(1).getScores()");
		day4Low.setGravity(Gravity.CENTER_HORIZONTAL);

		rankLabels.addView(playerScores);
		rank1.addView(Score1);
		rank2.addView(Score2);
		rank3.addView(Score3);

		rank4.addView(Score4);
		rank5.addView(Score5);
		rank6.addView(Score6);

		rank7.addView(Score7);
		rank8.addView(Score8);
		rank9.addView(Score9);

		rank10.addView(Score10);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
