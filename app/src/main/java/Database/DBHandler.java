package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;

import Modal.MovementsModal;


public class DBHandler extends SQLiteOpenHelper {

    //Database name and version
    private static final String DB_NAME = "smartSavings";
    private static final int DB_VERSION = 1;


    //Table Movements
    private static final String TABLE_MOVEMENTS = "movements";

    private static final String ID_COL = "id";

    private static final String DESC_COL = "description";

    private static final String CAT_COL = "category";

    private static final String VAL_COL = "value";

    private static final String DAY_COL = "day";

    private static final String OPT_COL = "option";


    //Table Categories
    private static final String TABLE_CATEGORIES = "categories";
    private static final String ID_CAT_COL = "id";
    private static final String DESC_CAT = "desc_category";



    public DBHandler(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    //Create the tables for the Database
    @Override
    public void onCreate(SQLiteDatabase db){
        String queryMovements = "CREATE TABLE " + TABLE_MOVEMENTS +
                " (" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + DESC_COL + " TEXT,"
                + CAT_COL + " INTEGER,"
                + VAL_COL + " FLOAT,"
                + DAY_COL + " TEXT,"
                + OPT_COL + " INTEGER,"
                + "FOREIGN KEY (" + CAT_COL +") REFERENCES " + TABLE_CATEGORIES + "(" + ID_CAT_COL + "))";

        db.execSQL(queryMovements);

        String queryCategories = "CREATE TABLE " + TABLE_CATEGORIES +
                " (" + ID_CAT_COL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + DESC_CAT + " TEXT)";

                db.execSQL(queryCategories);
    }


    //Add Movements
    //public void addNewCourse (String description, String category, float value, String day, int option){
    public void addNewMovement(String description, float value, String day, int option, long category){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DESC_COL, description);
        values.put(CAT_COL, category);
        values.put(VAL_COL, value);
        values.put(DAY_COL, day);
        values.put(OPT_COL, option);

        db.insert(TABLE_MOVEMENTS, null, values);

        db.close();

    }

    //Add Categories
    public void addNewCategory (String description){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DESC_CAT, description);

        db.insert(TABLE_CATEGORIES, null, values);

        db.close();
    }

    //Read movements
    public ArrayList<MovementsModal> readMovements(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorMovements = db.rawQuery(" SELECT * FROM " + TABLE_MOVEMENTS, null);

        ArrayList<MovementsModal> movementsModalsArrayList = new ArrayList<>();

        if (cursorMovements.moveToFirst()) {
            do {
                movementsModalsArrayList.add(new MovementsModal(
                        cursorMovements.getString(2),
                        cursorMovements.getString(3),
                        cursorMovements.getInt(5)
                ));
            } while (cursorMovements.moveToNext());
        }
        cursorMovements.close();
        return movementsModalsArrayList;
    }

    //Read categories descs
    public ArrayList<String> getCategoryDescriptions(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorCatDescs = db.rawQuery(" SELECT " + DESC_CAT + " FROM " + TABLE_CATEGORIES, null);

        ArrayList<String> categoryDescriptions = new ArrayList<>();

        if (cursorCatDescs.moveToFirst()){
            do {
                categoryDescriptions.add(cursorCatDescs.getString(0));
            }while (cursorCatDescs.moveToNext());
        }
        cursorCatDescs.close();
        return categoryDescriptions;
    }

    public int getCategoryIdFromDescription(String categoryDescription) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + ID_CAT_COL + " FROM " + TABLE_CATEGORIES + " WHERE " + DESC_CAT + "=?", new String[]{categoryDescription});

        int categoryId = -1;

        if (cursor.moveToFirst()) {
            categoryId = cursor.getInt(0);
        }

        cursor.close();
        db.close();

        return categoryId;
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVEMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        onCreate(db);
    }
}
