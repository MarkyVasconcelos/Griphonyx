package br.com.griphonyx.jdbc;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.griphonyx.dao.Movie;

public class MovieJdbc {

	public ArrayList<Movie> listaFilme(Context ctx, String filtro){
		ArrayList<Movie> lista = null;

		SQLiteDatabase db = ctx.openOrCreateDatabase("griffith", Context.MODE_PRIVATE, null);
		Cursor c = db.query("movies", null, "o_title like '"+filtro+"%'", null, null, null, "o_title");

		if (c.moveToFirst()){
			lista = new ArrayList<Movie>();
			do {
				Movie agenda = new Movie();
				agenda.setNomeFilme(c.getString(c.getColumnIndex("o_title")));
				agenda.setSinopse(c.getString(c.getColumnIndex("plot")));
				agenda.setNota(c.getInt(c.getColumnIndex("rating")));
				agenda.setAssistido(c.getString(c.getColumnIndex("seen")).equals("1")?true:false);
				lista.add(agenda);
			} while (c.moveToNext());
		}

		return lista;
	}
	
	public String getReview(Context ctx, String title){
		SQLiteDatabase db = ctx.openOrCreateDatabase("griffith", Context.MODE_PRIVATE, null);
		Cursor c = db.query("movies", null, "o_title like '"+title+"%'", null, null, null, null);

		String retorno = null;
		if(c.getCount()>0){
			c.moveToFirst();
			retorno = c.getString(c.getColumnIndex("plot"));
		}

		return retorno;
	}
	
}
