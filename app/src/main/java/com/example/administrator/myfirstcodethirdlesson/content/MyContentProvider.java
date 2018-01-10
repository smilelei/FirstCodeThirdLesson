package com.example.administrator.myfirstcodethirdlesson.content;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Switch;

import com.example.administrator.myfirstcodethirdlesson.SixLesson.db.MyDbHelper;

/**
 * Created by Administrator on 2018/1/2.
 */

public class MyContentProvider extends ContentProvider{
    public  static final int TABLE_DIR = 1;
    public static final int TABLE_ITEM = 2;
    public static final int TABLE2_DIR = 3;
    public static final int TABLE2_ITEM = 4;

    public static String author = "com.example.app.provider";
    MyDbHelper dbHelper;

    private static UriMatcher uriMatcher;
    static {
        uriMatcher  = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(author,"books",TABLE_DIR);
        uriMatcher.addURI(author,"books/#",TABLE_ITEM);
        uriMatcher.addURI(author,"category",TABLE2_DIR);
        uriMatcher.addURI(author,"category/#",TABLE2_ITEM);
    }
    @Override
    public boolean onCreate() {
        dbHelper = new MyDbHelper(getContext(),"BookStory.db",null,2);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor  cursor = null;
        switch (uriMatcher.match(uri)){
           case TABLE_DIR:
               cursor = database.query("books",projection,selection,selectionArgs,null,null,sortOrder);
               break;
           case TABLE_ITEM:
               String booksId =uri.getPathSegments().get(1);
               cursor = database.query("books",null,"id=?",new String[]{booksId},null,null,sortOrder);
               break;
           case TABLE2_DIR:
               cursor = database.query("catgory",projection,selection,selectionArgs,null,null,sortOrder);
               break;
           case TABLE2_ITEM:
               String categorId = uri.getPathSegments().get(1);
               cursor = database.query("catgory",null,"id=?",new String[]{categorId},null,null,sortOrder);
               break;
       }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case TABLE_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.app.provider.books";
            case TABLE_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.app.provider.books";
            case TABLE2_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.app.provider.category";
            case TABLE2_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.app.provider.category";
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Uri returnUri = null;
        switch (uriMatcher.match(uri)){
            case TABLE_DIR:
            case TABLE_ITEM:
                long newBookId = database.insert("books",null,values);
                returnUri = Uri.parse("content://"+author+"/books/"+newBookId);
            case TABLE2_DIR:
            case TABLE2_ITEM:
                long newCategoryId = database.insert("category",null,values);
                returnUri = Uri.parse("content://"+author+"/category/"+newCategoryId);
        }
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int deleteRow = 0;
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)){
            case TABLE_DIR:
                deleteRow = database.delete("books",selection,selectionArgs);
                break;
            case TABLE_ITEM:
                String bookId = uri.getPathSegments().get(1);
                deleteRow = database.delete("books","id=?",new String[]{bookId});
            case TABLE2_DIR:
                deleteRow = database.delete("category",selection,selectionArgs);
                break;
            case TABLE2_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                deleteRow = database.delete("category","id=?",new String[]{categoryId});
        }
        return deleteRow;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int updateRow = 0;
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)){
            case TABLE_DIR:
            case TABLE_ITEM:
                updateRow = database.update("books",values,selection,selectionArgs);
                break;
            case TABLE2_DIR:
            case TABLE2_ITEM:
                updateRow = database.update("category",values,selection,selectionArgs);
                break;
        }
        return updateRow;
    }
}
