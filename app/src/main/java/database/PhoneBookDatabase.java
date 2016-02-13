package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import entities.PhoneBook;

/**
 * Created by Dion on 2/1/2016.
 */
public class PhoneBookDatabase extends SQLiteOpenHelper {

    private static final String DB_PHONEBOOK = "phonebook.db";
    private static final int DB_VERSION = 1;

    public static final String PB_TABLE = "phonebook";
    public static final String PB_NAAM = "naam";
    public static final String PB_NUMBER="nummer";

    private static final String SQL_PB_TABLE_QUERY = "create table phonebook(id INTEGER PRIMARY KEY, naam STRING NOT NULL, nummer INTEGER NOT NULL)";


    public PhoneBookDatabase(Context context){
        super(context, DB_PHONEBOOK ,null, DB_VERSION);
        insertDefaultData();
    }

    @Override
    public void onCreate (SQLiteDatabase db){
        db.execSQL(SQL_PB_TABLE_QUERY);
    }

    private void insertDefaultData() {


        ContentValues contactsValues = new ContentValues();
        contactsValues.put(PB_NAAM, "Dion");
        contactsValues.put(PB_NUMBER, 8894863);
        insertContact(PB_TABLE, contactsValues);

        ContentValues contactsValues2 = new ContentValues();
        contactsValues2.put(PB_NAAM, "Kewish");
        contactsValues2.put(PB_NUMBER, 8578474);
        insertContact(PB_TABLE, contactsValues2);

        ContentValues contactsValues3 = new ContentValues();
        contactsValues3.put(PB_NAAM, "Devika");
        contactsValues3.put(PB_NUMBER, 8674573);
        insertContact(PB_TABLE, contactsValues3);

        ContentValues contactsValues4 = new ContentValues();
        contactsValues4.put(PB_NAAM, "Justin");
        contactsValues4.put(PB_NUMBER, 8753094);
        insertContact(PB_TABLE, contactsValues4);
    }


    //name en phonenumber zetten in contentvalues in main activity, hier veranderen in ContentValues contentValues
    // public static void insertContact( ContentValues contentValues){
    public  long insertContact( String name, ContentValues contact){
        SQLiteDatabase db = getWritableDatabase();
        long rowId = db.insert(PB_TABLE, null, contact);
        db.close();
        //return the row ID of the newly inserted row, or -1 if an error occurred
        return rowId;
    }

    public void updateContact ( String name, Integer phoneNumber){

    }

    public PhoneBook  deleteContact(String name, Integer phoneNumber){
        PhoneBook phonebook = null;
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = String.format("%s = ? OR %s = ?", PB_NAAM, PB_NUMBER);
        String[] whereArgs = {name, String.valueOf(phoneNumber)};

        //db.delete(PB_TABLE,"naam = %s", new String[]{name});
        return phonebook;

    }

    public PhoneBook findContactViaName(String name) {
        PhoneBook phonebook = null;
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("select * from %s where %s = '%s'", PB_TABLE, PB_NAAM, name);
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToNext()) {
            phonebook = new PhoneBook(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
        }
        db.close();
        return phonebook;
    }

    public PhoneBook findContactViaNumber(Integer phoneNumber){
        PhoneBook phonebook1 = null;
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("select * from %s where %s = '%s'", PB_TABLE, PB_NUMBER, phoneNumber);
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToNext()) {
            phonebook1 = new PhoneBook(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
        }
        db.close();
        return phonebook1;
    }


    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){

    }


}

