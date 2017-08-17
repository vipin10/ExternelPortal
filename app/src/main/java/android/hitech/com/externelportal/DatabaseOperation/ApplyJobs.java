package android.hitech.com.externelportal.DatabaseOperation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ApplyJobs extends SQLiteOpenHelper {
    SQLiteDatabase database;
    ContentValues cv;
    long l;
    boolean result;

    public ApplyJobs(Context context) {
        super(context, "relevant_jobs", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table apply_jobs (id TEXT,Data TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists apply_jobs");
    }

    public long addEntry(int i) {
        database = this.getWritableDatabase();
        cv = new ContentValues();
        cv.put("id", String.valueOf(i));
        cv.put("Data", "applied");
        l = database.insert("apply_jobs", null, cv);
        return l;
    }

    public boolean getEntry(int position) {
        database = this.getReadableDatabase();
        String[] selection = {String.valueOf(position)};
        Cursor cursor = database.rawQuery("select * from apply_jobs where id = ?", selection);
        if (cursor.getCount() >= 1) {
            cursor.close();
            result = true;
        }
        return result;
    }
}
