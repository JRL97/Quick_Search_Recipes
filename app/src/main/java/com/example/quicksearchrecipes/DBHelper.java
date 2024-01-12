package com.example.quicksearchrecipes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;
public class DBHelper extends SQLiteOpenHelper {

    public static final String DBName = "quicksearchrecipes.db";

    public DBHelper(@Nullable Context context) {
        super(context, DBName, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table users(username TEXT primary key, password TEXT)");
        //Attempting to create recipe table
        sqLiteDatabase.execSQL("create table recipes( recipe_name TEXT primary key, ingredient_no TEXT, cook_time TEXT, instructions TEXT, ingredients TEXT)");
        //create ingredient table
//        sqLiteDatabase.execSQL("create table ingredients(iid INT primary key AUTOINCREMENT, ingredient_name TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists users");
        //Recipe table
        sqLiteDatabase.execSQL("drop table if exists recipes");
//        sqLiteDatabase.execSQL("drop table if exists ingredients");
    }

    public boolean insertData(String username, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = myDB.insert("users", null, contentValues);
        if (result == -1) return false;
        else return true;
    }

    //Insert recipe data and Ingredient data.
    public boolean insertData2(String recipe_name, String ingredient_no, String cook_time, String instructions, String ingredients) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put("rid", rid);
        contentValues.put("recipe_name", recipe_name);
        contentValues.put("ingredient_no", ingredient_no);
        contentValues.put("cook_time", cook_time);
        contentValues.put("instructions", instructions);
        contentValues.put("ingredients", ingredients);
        long result = myDB.insert("recipes", null, contentValues);
        if (result == -1) return false;
        else return true;
    }

//    public boolean insertData(Integer iid, String ingredient_name) {
//        SQLiteDatabase myDB = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("iid", iid);
//        contentValues.put("ingredient_name", ingredient_name);
//        long result = myDB.insert("ingredients", null, contentValues);
//        if (result == -1) return false;
//        else return true;
//    }

    public boolean checkUsername(String username) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username = ?", new String[]{username});
        if (cursor.getCount()>0)
            return true;
        else return false;
    }

    public boolean checkUser(String username, String pwd){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username = ? and password=?", new String[]{username, pwd});
        if (cursor.getCount()>0)
            return true;
        else return false;
    }
}
