package be.manu.statsforleague.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Champions.db";
    public static final String CHAMPION_TABLE_NAME = "champion";
    public static final String CHAMPION_COLUMN_ID = "_id";
    public static final String CHAMPION_COLUMN_NAME = "name";
    public static final String CHAMPION_COLUMN_TITLE = "title";
    public static final String CHAMPION_COLUMN_BIO = "bio";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + CHAMPION_TABLE_NAME +
                        "(" + CHAMPION_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        CHAMPION_COLUMN_NAME + " TEXT, " +
                        CHAMPION_COLUMN_TITLE + " TEXT, " +
                        CHAMPION_COLUMN_BIO + " TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CHAMPION_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertChampion(String name, String title, String bio) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(CHAMPION_COLUMN_NAME, name);
        contentValues.put(CHAMPION_COLUMN_TITLE, title);
        contentValues.put(CHAMPION_COLUMN_BIO, bio);

        db.insert(CHAMPION_TABLE_NAME, null, contentValues);
        return true;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, CHAMPION_TABLE_NAME);
    }

    public boolean updateChampion(Integer id, String name, String title, String bio) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CHAMPION_COLUMN_NAME, name);
        contentValues.put(CHAMPION_COLUMN_TITLE, title);
        contentValues.put(CHAMPION_COLUMN_BIO, bio);
        db.update(CHAMPION_TABLE_NAME, contentValues, CHAMPION_COLUMN_ID + " = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteChampion(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(CHAMPION_TABLE_NAME,
                CHAMPION_COLUMN_ID + " = ? ",
                new String[]{Integer.toString(id)});
    }

    public Cursor getChampion(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + CHAMPION_TABLE_NAME + " WHERE " +
                CHAMPION_COLUMN_ID + "=?", new String[]{Integer.toString(id)});
    }

    public Cursor getAllChampions() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + CHAMPION_TABLE_NAME, null);
    }
}
