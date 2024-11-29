package com.mtach.myapplication;

import static com.mtach.myapplication.DBHelper.COL_ID;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ContactTable {
    private DBHelper dbHelper;

    public ContactTable(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void InsertContact(ContactModel cm) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COL_NAME, cm.getName());
        cv.put(DBHelper.COL_EMAIL, cm.getEmail());
        cv.put(DBHelper.COL_PHONE, cm.getPhoneNumber());
        db.insert(DBHelper.TAB_CONTACT, null, cv);
        db.close();
    }

    public void UpdateContact(ContactModel cm) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COL_NAME, cm.getName());
        cv.put(DBHelper.COL_EMAIL, cm.getEmail());
        cv.put(DBHelper.COL_PHONE, cm.getPhoneNumber());
        db.update(DBHelper.TAB_CONTACT, cv, COL_ID + " = ?", new String[]{String.valueOf(cm.getId())});
        db.close();
    }

    public void DeleteContact(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DBHelper.TAB_CONTACT, COL_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    @SuppressLint("Range")
    public ArrayList<ContactModel> ReadContacts() {
        ArrayList<ContactModel> contacts = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DBHelper.TAB_CONTACT, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                ContactModel cm = new ContactModel();
                cm.setId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
                cm.setName(cursor.getString(cursor.getColumnIndex(DBHelper.COL_NAME)));
                cm.setEmail(cursor.getString(cursor.getColumnIndex(DBHelper.COL_EMAIL)));
                cm.setPhoneNumber(cursor.getString(cursor.getColumnIndex(DBHelper.COL_PHONE)));
                contacts.add(cm);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contacts;
    }
}
