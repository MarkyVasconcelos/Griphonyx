package br.com.griphonyx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RatingBar;
import br.com.grif.R;

public class RatingActivity extends Activity {

	private Intent intent;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rating);
		
		intent = getIntent();
		System.out.println(intent.getFloatExtra("rating", 0));
		final float rate = intent.getFloatExtra("rating", 0);
		
		RatingBar rating = (RatingBar) findViewById(R.id.RatingValue);
		
		rating.setEnabled(true);
		rating.setRating(rate);
		rating.setNumStars(10);
		
	}
}
