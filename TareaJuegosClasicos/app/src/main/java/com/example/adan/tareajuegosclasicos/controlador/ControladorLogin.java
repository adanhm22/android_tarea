package com.example.adan.tareajuegosclasicos.controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.adan.tareajuegosclasicos.modelo.Persona;

public abstract class ControladorLogin extends SQLiteOpenHelper {

    public static volatile String dbName="prueba.db", tableName="users",
            userCol="user",passwordCol="password",nameCol="name";

    public ControladorLogin(Context context, String name) {
        super(context, name, null, 1);
    }


    public static ControladorLogin newInstance(Context context) {
        ControladorLogin controlador = new ControladorLogin(context, dbName) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL("CREATE TABLE " + tableName + " ("
                        + userCol + " TEXT PRIMARY KEY," + passwordCol + " TEXT," + nameCol + " TEXT)");
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS " + tableName);
                onCreate(db);
            }

            @Override
            public boolean login(String user, String password) throws Exception {
                //TODO:login

                Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM "
                        + tableName + " WHERE " + userCol + " = '" + user + "'", null);
                if (cursor.getCount() <= 0)
                    throw new Exception("no hay datos");
                else {
                    cursor.moveToNext();
                    String passwordTable = cursor.getString(cursor.getColumnIndex(passwordCol));
                    System.out.println(password + " " + passwordTable);
                    if (password.equals(passwordTable)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }


            @Override
            public boolean login(Persona person) throws Exception {
                return this.login(person.getEmail(), person.getPassword());
            }

            @Override
            public boolean registro(String user, String password, String name) throws Exception {
                //TODO:registro
                if (user.trim().isEmpty() || password.trim().isEmpty() || name.trim().isEmpty())
                    throw new Exception("alguno de los campos está vacío");
                Cursor cursor = getWritableDatabase().rawQuery("SELECT * FROM "
                        + tableName + " WHERE " + userCol + " = '" + user + "'", null);
                if (cursor.getCount() != 0)
                    throw new Exception("ya existe ese usuario");
                else {
                    ContentValues content = new ContentValues();
                    content.put(userCol, user);
                    content.put(passwordCol, password);
                    content.put(nameCol, name);
                    SQLiteDatabase db = getWritableDatabase();
                    if (db.insert(tableName, null, content) != -1) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }

            @Override
            public boolean registro(Persona person) throws Exception {
                return this.registro(person.getEmail(), person.getPassword(), person.getNombre());
            }

        };

        return controlador;

    }

    public static ControladorLogin newInstance(Context contexto, String database, String table,
                                               String user, String password, String name){
             dbName=database;
            tableName =table;
            userCol=user;
            passwordCol=password;
            nameCol=name;
         return newInstance(contexto);
    }

    public abstract boolean login(String user,String password) throws Exception;

    public abstract boolean login (Persona person) throws Exception;

    public abstract boolean registro (String user,String password,String name) throws Exception;

    public abstract boolean registro (Persona person) throws Exception;
}
