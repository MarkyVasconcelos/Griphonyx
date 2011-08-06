package br.com.griphonyx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import br.com.grif.R;
import br.com.griphonyx.dao.Movie;
import br.com.griphonyx.jdbc.MovieJdbc;

public class GriphonyxActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
    	DataBaseHelper myDbHelper;
    	myDbHelper = new DataBaseHelper(this);
 
    	try {
    		myDbHelper.createDataBase();
    	} catch (IOException ioe) {
    		throw new Error("Não foi possível criar o banco de dados.");
    	}
 
    	try {
    		myDbHelper.openDataBase();
    	} catch (SQLException sqle){
    		throw sqle;
    	}
		
		botao();
	}

	@SuppressWarnings("unchecked")
	public void botao(){
		final EditText text = (EditText) findViewById(R.id.text);
		Button button = (Button) findViewById(R.id.button);
		final ListView list = (ListView) findViewById(R.id.listView);

		final List data = new ArrayList();
		final ArrayAdapter adapter = new ArrayAdapter(
				getApplicationContext(), android.R.layout.simple_list_item_1,
				data);
		list.setAdapter(adapter);

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MovieJdbc filmeJdbc = new MovieJdbc();
				ArrayList<Movie> lista = filmeJdbc.listaFilme(GriphonyxActivity.this,text.getText().toString());
				for(Movie ag : lista){
					data.add(ag);
					adapter.notifyDataSetChanged();
				}
			}
		});

		list.setTextFilterEnabled(true);
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
								
				MovieJdbc filmeJdbc = new MovieJdbc();
				Intent reviewActivity = new Intent(GriphonyxActivity.this, ReviewActivity.class);
								
				String titleMovie = ((Movie)list.getItemAtPosition(position)).getNomeFilme();
				String reviewMovie = filmeJdbc.getReview(GriphonyxActivity.this, titleMovie);
				boolean seeMovie = ((Movie)list.getItemAtPosition(position)).isAssistido();
				double rating = ((Movie)list.getItemAtPosition(position)).getNota();
				
				reviewActivity.putExtra("review", reviewMovie);
				reviewActivity.putExtra("see", seeMovie);
				reviewActivity.putExtra("rating", rating);
				startActivity(reviewActivity);
				
			}
		});
	}
}