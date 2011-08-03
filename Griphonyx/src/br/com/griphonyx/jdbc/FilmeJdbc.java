package br.com.griphonyx.jdbc;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.griphonyx.dao.Filmes;

public class FilmeJdbc {

	public ArrayList<Filmes> listaFilme(Context ctx, String filtro){
		ArrayList<Filmes> lista = null;

		SQLiteDatabase db = ctx.openOrCreateDatabase("griffith", Context.MODE_PRIVATE, null);
		Cursor c = db.query("movies", null, "o_title like '"+filtro+"%'", null, null, null, "o_title");

		if (c.moveToFirst()){
			lista = new ArrayList<Filmes>();
			do {
				Filmes agenda = new Filmes();
				agenda.setNomeFilme(c.getString(c.getColumnIndex("o_title")));
				agenda.setSinopse(c.getString(c.getColumnIndex("plot")));
				agenda.setNota(c.getInt(c.getColumnIndex("rating")));
				lista.add(agenda);
			} while (c.moveToNext());
		}

		return lista;
	}
	
}
