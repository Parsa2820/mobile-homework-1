package ir.parsa2820.terminator.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AgendaDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Agenda.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + AgendaContract.AgendaEntry.TABLE_NAME + " (" +
                    AgendaContract.AgendaEntry.COLUMN_NAME_NAME + " TEXT," +
                    AgendaContract.AgendaEntry.COLUMN_NAME_COURSE_ID + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + AgendaContract.AgendaEntry.TABLE_NAME;

    public AgendaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
