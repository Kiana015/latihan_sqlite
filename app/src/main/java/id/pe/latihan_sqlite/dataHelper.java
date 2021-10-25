package id.pe.latihan_sqlite;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.HashMap;
public class dataHelper extends SQLiteOpenHelper{
    Cursor cursor;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "biodata.db";
    public dataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "biodata";
        public static final String TITLE = "nama";
        public static final String URL = "alamat";
        public static final String CONTENT = "no_hp";
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABEL =
                "CREATE TABLE " + dataHelper.FeedEntry.TABLE_NAME + " (id INTEGER PRIMARY KEY," +
                        dataHelper.FeedEntry.TITLE + " TEXT," +
                        dataHelper.FeedEntry.URL+ " TEXT,"+
                        dataHelper.FeedEntry.CONTENT+ " TEXT)";
        db.execSQL(TABEL);
    }
   @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        /*
        String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + dataHelper.FeedEntry.TABLE_NAME;
        String SQL_DELETE_ENTRIES_2 =
                "DROP TABLE IF EXISTS " + dataHelper.FeedEntry.TABLE_NAME;
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_ENTRIES_2);
        onCreate(db);
         */
    }
}
