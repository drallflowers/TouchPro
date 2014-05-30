package com.touchpro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void playGame(View view) {
		Intent intent = new Intent(getApplicationContext(), PlayGameActivity.class);
	    startActivity(intent);
	}

	public void viewHighScores(View view) {
		Intent intent = new Intent(getApplicationContext(), GetHighScoresActivity.class);
		startActivity(intent);
		//Toast.makeText(getApplicationContext(), "TODO: View High Scores Button Pressed", Toast.LENGTH_SHORT).show();
	}
}
