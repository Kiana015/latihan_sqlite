package id.pe.latihan_sqlite;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class updateData extends AppCompatActivity {
    dataHelper dbHelper = new dataHelper(this);
    protected Cursor cursor;
    EditText nama,alamat,noHp;
    Button submit;
    Toast message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        nama = (EditText) findViewById(R.id.nama);
        alamat = (EditText) findViewById(R.id.alamat);
        noHp = (EditText) findViewById(R.id.noHp);
        submit = (Button) findViewById(R.id.submit);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata WHERE nama LIKE '%"+getIntent().getStringExtra("id")+"%'",null);
        cursor.moveToFirst();
        if(cursor.getCount() == 1)
        {
            cursor.moveToPosition(0);
            nama.setText(cursor.getString(1).toString());
            alamat.setText(cursor.getString(2).toString());
            noHp.setText(cursor.getString(3).toString());
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                String query = "UPDATE biodata SET "
                        + dataHelper.FeedEntry.TITLE + "='" + nama.getText().toString() + "', "
                        + dataHelper.FeedEntry.URL + "='" + alamat.getText().toString() + "', "
                        + dataHelper.FeedEntry.CONTENT + "='" + noHp.getText().toString() + "'"
                        + " WHERE nama LIKE '%" + getIntent().getStringExtra("id")+"%'";
                database.execSQL(query);
                message.makeText(getApplicationContext(),"Data Sudah Diubah", message.LENGTH_SHORT).show();
                Intent back = new Intent(updateData.this, MainActivity.class);
                startActivity(back);
            }
        });

    }
}