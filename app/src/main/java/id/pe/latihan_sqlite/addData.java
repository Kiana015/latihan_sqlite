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
public class addData extends AppCompatActivity {
    dataHelper dbHelper = new dataHelper(this);
    EditText nama,alamat,noHp;
    Button submit;
    Toast message;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        nama = findViewById(R.id.nama);
        alamat = findViewById(R.id.alamat);
        noHp = findViewById(R.id.noHp);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db  = dbHelper.getWritableDatabase();
                SQLiteDatabase cek = dbHelper.getReadableDatabase();
                cursor = cek.rawQuery("SELECT * FROM biodata",null);
                int jumlah_data = cursor.getCount() + 1;
                db.execSQL("INSERT INTO biodata VALUES (" +
                        "'"+jumlah_data+"', " +
                        "'"+nama.getText().toString()+"'," +
                        "'"+alamat.getText().toString()+"'," +
                        "'"+noHp.getText().toString()+"')");
                message.makeText(getApplicationContext(),"Data Sudah Dimasukan", message.LENGTH_SHORT).show();
                Intent back = new Intent(addData.this, MainActivity.class);
                startActivity(back);
            }
        });
    }
}