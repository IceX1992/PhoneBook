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

    private static final String DB_PHONEBOOK = "phonebook";
    private static final int DB_VERSION = 1;

    public static final String PB_TABLE = "phonebook";
    public static final String PB_NAAM = "Naam";
    public static final String PB_NUMBER="Nummer";

    private static final String SQL_PB_TABLE_QUERY = "create table phonebook(id INTEGER PRIMARY KEY, naam STRING NOT NULL, nummer INTEGER NOT NULL)";



    public PhoneBookDatabase(Context context){
        super(context, DB_PHONEBOOK ,null, DB_VERSION);
    }

  /*  public PhoneBookDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
*/


    @Override
    public void onCreate (SQLiteDatabase db){
        db.execSQL(SQL_PB_TABLE_QUERY);
        /*db.execSQL("CREATE TABLE CONTACTS ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "PHONE_NUMBER INTEGER); ");
*/
        ContentValues contactsValues = new ContentValues();
        contactsValues.put(PB_NAAM, "Dion");
        contactsValues.put(PB_NUMBER, 8894863);

        ContentValues contactsValues2 = new ContentValues();
        contactsValues2.put(PB_NAAM, "Kewish");
        contactsValues2.put(PB_NUMBER, 8578474);

        ContentValues contactsValues3 = new ContentValues();
        contactsValues3.put(PB_NAAM, "Devika");
        contactsValues3.put(PB_NUMBER, 8674573);

        ContentValues contactsValues4 = new ContentValues();
        contactsValues4.put(PB_NAAM, "Justin");
        contactsValues4.put(PB_NUMBER, 8753094);
    }


    //name en phonenumber zetten in contentvalues in main activity, hier veranderen in ContentValues contentValues
    // public static void insertContact( ContentValues contentValues){
    public static void insertContact( String name, Integer phoneNumber){
        ContentValues contact = new ContentValues();
        contact.put(PB_NAAM, name);
        contact.put(PB_NUMBER, phoneNumber);
    }

    public void updateContact (SQLiteDatabase db, String name, Integer phoneNumber){

    }


    public/*List<PhoneBook>   */ PhoneBook findContactViaName(String name){


        PhoneBook phonebook = null;
      //  List<PhoneBook> phonebookList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("select * from %s where %s = '%s'", PB_TABLE, PB_NAAM, name);
        Cursor cursor = db.rawQuery(sql, null);
        /*
        while(cursor.moveToNext()){
            phonebook = new PhoneBook(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            phonebookList.add(phonebook);
        }
        db.close();
        return phonebookList;
       //db.execSQL("SELECT * FROM CONTACTS WHERE NAME=" + name);
    */
        if(cursor.moveToFirst()){
            phonebook = new PhoneBook(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
        }
        db.close();
        return phonebook;

    }

    public void findContactViaNumber (SQLiteDatabase db, Integer phoneNumber){
        db.execSQL("SELECT * FROM CONTACTS WHERE PHONE_NUMBER = phoneNumber");
    }



    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){

    }


}
