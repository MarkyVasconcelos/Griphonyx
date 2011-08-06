package br.com.griphonyx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import br.com.grif.R;

public class ReviewActivity extends Activity {
	
	private Intent intent;
	private String review;
	private boolean see;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.review);
		
		intent = getIntent();
		review = "Review: "+intent.getStringExtra("review");
		see = intent.getBooleanExtra("see",false);
		
		EditText reviewMovie = (EditText) findViewById(R.id.reviewText);
		CheckBox seeMovie = (CheckBox) findViewById(R.id.seeMovie);
		
		reviewMovie.setText(this.review);
		seeMovie.setSelected(see);
		seeMovie.setChecked(see);
		
		Button button = (Button) findViewById(R.id.oBtRating);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent ratingActivity = new Intent(ReviewActivity.this, RatingActivity.class);
				
				ratingActivity.putExtra("rating", intent.getDoubleExtra("rating", 0));
				startActivity(ratingActivity);
			}
		});
	}
}
