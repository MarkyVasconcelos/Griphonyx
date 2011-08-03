package br.com.griphonyx;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import br.com.grif.R;
import br.com.griphonyx.dao.Filmes;
import br.com.griphonyx.jdbc.FilmeJdbc;

public class GriphonyxActivity extends Activity {

	Context ctx;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ctx = this;
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
				FilmeJdbc filmeJdbc = new FilmeJdbc();
				ArrayList<Filmes> lista = filmeJdbc.listaFilme(ctx,text.getText().toString());
				for(Filmes ag : lista){
					data.add("Filme: "+ag.getNomeFilme()+" Nota: "+ag.getNota());
					adapter.notifyDataSetChanged();
				}
			}
		});

		list.setTextFilterEnabled(true);
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				AlertDialog.Builder adb=new AlertDialog.Builder(GriphonyxActivity.this);
				adb.setTitle("LVSelectedItemExample");
				adb.setMessage("Selected Item is = "+list.getItemAtPosition(position));
				adb.setPositiveButton("Ok", null);
				adb.show();
			}
		});
	}
}