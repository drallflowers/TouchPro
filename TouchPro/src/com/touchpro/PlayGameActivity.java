package com.touchpro;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PlayGameActivity extends Activity {
	
	private static long startTime = System.currentTimeMillis();
	private Handler timerHandler = new Handler();
	private Runnable timerRunnable = new Runnable() {
		@Override
		public void run() {
			long millis = System.currentTimeMillis() - startTime;
			int seconds = (int) (millis / 1000);
			final TextView counter = (TextView) findViewById(R.id.counter);
			counter.setText("Score: " + seconds);
			timerHandler.postDelayed(this, 500);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play_game_activity);

		final Button gameButton = (Button) findViewById(R.id.gameButton);
		OnTouchListener listener = new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					startTime = System.currentTimeMillis();
					timerHandler.postDelayed(timerRunnable, 0);
					final TextView counter = (TextView) findViewById(R.id.counter);
					counter.setText("Score: 0");
					Toast.makeText(getApplicationContext(), "Game Started", Toast.LENGTH_SHORT).show();
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					timerHandler.removeCallbacks(timerRunnable);
					Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_SHORT).show();
				}
				return false;
			}
		};
		gameButton.setOnTouchListener(listener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
