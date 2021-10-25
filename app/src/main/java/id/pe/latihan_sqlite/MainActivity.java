package id.pe.latihan_sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    protected Cursor cursor;
    dataHelper dbHelper;
    Toast message;
    Button ton2;
    String[] daftarData;
    String[] idData;
    ListView listData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new dataHelper(this);
        listData = (ListView) findViewById(R.id.listData);
        ton2 = (Button) findViewById(R.id.addButton);
        ton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindahAddData = new Intent(MainActivity.this, addData.class);
                startActivity(pindahAddData);
            }
        });
        refreshList();
    }

    public void refreshList() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata",null);
        daftarData = new String[cursor.getCount()];
        idData = new String[cursor.getCount()];
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++)
        {
            cursor.moveToPosition(i);
            daftarData[i] = cursor.getString(1).toString();
            idData[i] = cursor.getString(0).toString();
        }
        listData.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftarData));
        listData.setSelected(true);
        listData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String selection = daftarData[position];
                final String idNya = idData[position];
                final CharSequence[] dialogItem = {"Detail Data", "Update Data", "Delete Data"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                Intent intent1 = new Intent(MainActivity.this, detail.class);
                                intent1.putExtra("id", selection);
                                startActivity(intent1);
                                break;
                            case 1:
                                Intent intent2 = new Intent(MainActivity.this, updateData.class);
                                intent2.putExtra("id", selection);
                                startActivity(intent2);
                                break;
                            case 2:
                                SQLiteDatabase database = dbHelper.getWritableDatabase();
                                String query = "DELETE FROM biodata WHERE nama LIKE '%" + selection +"%'";
                                database.execSQL(query);
                                message.makeText(getApplicationContext(),"Data Sudah Dihapus", message.LENGTH_SHORT).show();
                                refreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
    }
}