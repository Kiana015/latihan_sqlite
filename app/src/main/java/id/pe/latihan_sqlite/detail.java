package id.pe.latihan_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class detail extends AppCompatActivity {
    dataHelper dbHelper = new dataHelper(this);
    Cursor cursor;

    TextView id, nama, alamat, no_hp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        id = (TextView) findViewById(R.id.idValue);
        nama = (TextView) findViewById(R.id.namaValue);
        alamat = (TextView) findViewById(R.id.alamatValue);
        no_hp = (TextView) findViewById(R.id.hpView);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        cursor = db.rawQuery("SELECT * FROM biodata where nama LIKE '%"+getIntent().getStringExtra("id")+"%'",null);
        cursor.moveToFirst();
        if(cursor.getCount() == 1)
        {
            cursor.moveToPosition(0);
            id.setText(cursor.getString(0).toString());
            nama.setText(cursor.getString(1).toString());
            alamat.setText(cursor.getString(2).toString());
            no_hp.setText(cursor.getString(3).toString());
        }
    }
}