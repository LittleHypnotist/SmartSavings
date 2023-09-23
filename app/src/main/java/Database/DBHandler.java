package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import Modal.MovementsModal;


public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "smartSavings";

    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "movements";

    private static final String ID_COL = "id";

    private static final String DESC_COL = "description";

    //private static final String CAT_COL = "category";

    private static final String VAL_COL = "value";

    //private static final String DAY_COL = "day";

    //private static final String OPT_COL = "option";


    public DBHandler(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db){
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + DESC_COL + " TEXT,"
                //+ CAT_COL + " TEXT,"
                + VAL_COL + " FLOAT)";
        //+ DAY_COL + " DATE,"
        //+ OPT_COL + " INTEGER)";

        db.execSQL(query);
    }

    //public void addNewCourse (String description, String category, float value, String day, int option){
    public void addNewCourse (String description, float value){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DESC_COL, description);
        //values.put(CAT_COL, category);
        values.put(VAL_COL, value);
        //values.put(DAY_COL, day);
        //values.put(OPT_COL, option);

        db.insert(TABLE_NAME, null, values);

        db.close();

    }

    public ArrayList<MovementsModal> readMovements(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorMovements = db.rawQuery(" SELECT * FROM " + TABLE_NAME, null);

        ArrayList<MovementsModal> movementsModalsArrayList = new ArrayList<>();

        if (cursorMovements.moveToFirst()) {
            do {
                movementsModalsArrayList.add(new MovementsModal(
                        cursorMovements.getString(1),
                        cursorMovements.getString(2)
                ));
            } while (cursorMovements.moveToNext());
        }
        cursorMovements.close();
        return movementsModalsArrayList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
