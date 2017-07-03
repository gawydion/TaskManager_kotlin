package pl.edu.pwr.kotlin.mytasks.support;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.Date;

/* Klasa wspomagająca obsługę bazy SQLite */
public class DBhandler {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "CRM.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Tasks.TABLE_NAME + " (" +
                    Tasks._ID + " INTEGER PRIMARY KEY," +
                    Tasks.COLUMN_NAME_NAME + " TEXT," +
                    Tasks.COLUMN_NAME_TYPE + " TEXT," +
                    Tasks.COLUMN_NAME_DESC + " TEXT," +
                    Tasks.COLUMN_NAME_DATE + " TEXT)";

    /* Definiujemy nazwy kolumn i tabeli */
    public static class Tasks implements BaseColumns {
        public static final String TABLE_NAME = "Tasks";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_DESC = "desc";
        public static final String COLUMN_NAME_DATE = "dueDate";
    }

    private static final String TAG = "DBhandler";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    private final Context mCtx;

    private String orderBy = null;

    public class DatabaseHelper extends SQLiteOpenHelper {
        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + Tasks.TABLE_NAME;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }

    public DBhandler(Context ctx) {
        this.mCtx = ctx;
    }

    public DBhandler open() throws SQLException {
        mDbHelper = new DBhandler.DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createTask(String name, String type, String desc, String dueDate) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(Tasks.COLUMN_NAME_NAME, name);
        initialValues.put(Tasks.COLUMN_NAME_TYPE, type);
        initialValues.put(Tasks.COLUMN_NAME_DESC, desc);
        initialValues.put(Tasks.COLUMN_NAME_DATE, dueDate);

        return mDb.insert(Tasks.TABLE_NAME, null, initialValues);
    }


    //db.delete("tablename","id=? and name=?",new String[]{"1","jack"});
    public boolean deleteTask(String name, String desc) {
        int doneDelete = 0;
        doneDelete = mDb.delete(Tasks.TABLE_NAME, "name=? and desc=?" , new String[]{name,desc});

        Log.w(TAG, Integer.toString(doneDelete));   // Logujemy ilosc usunietych wpisow
        return doneDelete > 0;
    }

    public boolean deleteAllTasks() {
        int doneDelete = 0;
        doneDelete = mDb.delete(Tasks.TABLE_NAME, null , null);

        Log.w(TAG, Integer.toString(doneDelete));   // Logujemy ilosc usunietych wpisow
        return doneDelete > 0;
    }

    public Cursor fetchAllTasks() {
        Cursor mCursor = mDb.query(Tasks.TABLE_NAME, new String[]{Tasks._ID, Tasks.COLUMN_NAME_NAME,
                Tasks.COLUMN_NAME_TYPE, Tasks.COLUMN_NAME_DESC, Tasks.COLUMN_NAME_DATE}, null, null, null, null, orderBy, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        return mCursor;
    }

    public void setOrderBy(String txt) {
        orderBy = txt;
    }
}