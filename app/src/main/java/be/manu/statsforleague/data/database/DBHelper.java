package be.manu.statsforleague.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import be.manu.statsforleague.data.model.ChampionDTO;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Champions.db";
    private static final String CHAMPION_TABLE_NAME = "champion";
    private static final String CHAMPION_COLUMN_ID = "_id";
    private static final String CHAMPION_COLUMN_NAME = "name";
    private static final String CHAMPION_COLUMN_TITLE = "title";
    private static final String CHAMPION_COLUMN_BIO = "bio";
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

    public ChampionDTO getChampion(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        ChampionDTO championDTO = new ChampionDTO();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + CHAMPION_TABLE_NAME + " WHERE " +
                    CHAMPION_COLUMN_ID + "=?", new String[]{Integer.toString(id)});
            cursor.moveToNext();
            championDTO = createChampionDTO(cursor);
        } finally {
            close(db, cursor);
        }
        return championDTO;
    }

    public List<ChampionDTO> getAllChampions() {
        List<ChampionDTO> championDTOS = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + CHAMPION_TABLE_NAME, null);
            while (cursor.moveToNext()) {
                championDTOS.add(createChampionDTO(cursor));
            }
        } finally {
            close(db, cursor);
        }

        return championDTOS;
    }

    private void close(SQLiteDatabase db, Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
        if (db != null) {
            db.close();
        }
    }

    @NonNull
    private ChampionDTO createChampionDTO(Cursor cursor) {
        ChampionDTO championDTO = new ChampionDTO();
        championDTO.setId(cursor.getInt(0));
        championDTO.setName(cursor.getString(1));
        championDTO.setTitle(cursor.getString(2));
        championDTO.setShortBio(cursor.getString(3));
        return championDTO;
    }
}
