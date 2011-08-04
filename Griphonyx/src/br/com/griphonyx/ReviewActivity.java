package br.com.griphonyx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import br.com.grif.R;

public class ReviewActivity extends Activity {
	
	private Intent intent;
	private String review;
	private boolean seeMovie;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.review);
		
		//recebe a intent enviada
		intent = getIntent();
		//armazena os números digitados
		review = "Review: "+intent.getStringExtra("review");
		seeMovie = intent.getBooleanExtra("seeMovie",false);
		
		EditText text = (EditText) findViewById(R.id.reviewText);
		text.setText(review);			
	}
}
