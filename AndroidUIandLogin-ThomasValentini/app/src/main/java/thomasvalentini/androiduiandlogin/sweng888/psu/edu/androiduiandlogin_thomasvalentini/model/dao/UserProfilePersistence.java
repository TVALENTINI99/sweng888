package thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;

import java.util.ArrayList;

import thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.model.entity.UserProfile;

public class UserProfilePersistence implements IPersistence{
    //Class Variables
    public DatabaseAccess databaseAccess;
    //Constructor
    public UserProfilePersistence(Context context){this.databaseAccess=DatabaseAccess.getsInstance(context);}
    //Class Methods
    @Override
    public void insert(Object o) {
        FirebaseAuth mAuth=FirebaseAuth.getInstance();
        // Cast the generic object to have access to the profile info.
        UserProfile profile = (UserProfile) o;
        mAuth.createUserWithEmailAndPassword(profile.getEmail(), profile.getPassword());


        SQLiteDatabase sqLiteDatabase = databaseAccess.getWritableDatabase();
        // The ContentValues object create a map of values, where the columns are the keys
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserProfileTable.COLUMN_NAME_NAME, profile.getName());
        contentValues.put(UserProfileTable.COLUMN_NAME_SURNAME, profile.getSurname());
        contentValues.put(UserProfileTable.COLUMN_NAME_USERNAME, profile.getUsername());
        contentValues.put(UserProfileTable.COLUMN_NAME_BIRTHDAY, profile.getBirthday());
        contentValues.put(UserProfileTable.COLUMN_NAME_MOBILE_PHONE, profile.getMobile_phone());
        contentValues.put(UserProfileTable.COLUMN_NAME_EMAIL, profile.getEmail());
        contentValues.put(UserProfileTable.COLUMN_NAME_PASSWORD, profile.getPassword());


        // Insert the ContentValues into the Movie table.
        sqLiteDatabase.insert(UserProfileTable.TABLE_NAME, null, contentValues);

        sqLiteDatabase.close();
    }

    @Override
    public void delete(Object o) {

        UserProfile profile = (UserProfile) o;

        // Define which column will be the parameter for deleting the record.
        String selection = UserProfileTable.COLUMN_NAME_USERNAME + "LIKE ? ";

        // Arguments must be identidied in the placehold order
        String [] selectionArgs = { profile.getUsername().trim() };

        // Get database instance
        SQLiteDatabase sqLiteDatabase = databaseAccess.getWritableDatabase();
        sqLiteDatabase.delete(UserProfileTable.TABLE_NAME, selection, selectionArgs);
        sqLiteDatabase.close();
    }

    @Override
    public void edit(Object o) {
        // TODO IF IS NEEDED AT A LATER DATE
    }

    @Override
    public ArrayList getDataFromDB() {

        // Create ArrayList of profiles
        ArrayList<UserProfile> profiles = null;

        // Instatiate the database.
        SQLiteDatabase sqLiteDatabase = databaseAccess.getWritableDatabase();

        // Gather all the records found for the UserProfile table.
        Cursor cursor = sqLiteDatabase.rawQuery(UserProfileTable.select_all(), null);

        // It will iterate since the first record gathered from the database.
        cursor.moveToFirst();

        // Check if there exist other records in the cursor
        profiles = new ArrayList<>();

        if(cursor != null && cursor.moveToFirst()){

            do {
                String name = cursor.getString(cursor.getColumnIndex(UserProfileTable.COLUMN_NAME_NAME));
                String surname = cursor.getString(cursor.getColumnIndex(UserProfileTable.COLUMN_NAME_SURNAME));
                String username = cursor.getString(cursor.getColumnIndex(UserProfileTable.COLUMN_NAME_USERNAME));
                String birthday = cursor.getString(cursor.getColumnIndex(UserProfileTable.COLUMN_NAME_BIRTHDAY));
                String mobile_phone = cursor.getString(cursor.getColumnIndex(UserProfileTable.COLUMN_NAME_MOBILE_PHONE));
                String email = cursor.getString(cursor.getColumnIndex(UserProfileTable.COLUMN_NAME_EMAIL));
                String password = cursor.getString(cursor.getColumnIndex(UserProfileTable.COLUMN_NAME_PASSWORD));

                // Convert to UserProfile object.
                UserProfile profile = new UserProfile(name,surname,username,birthday,mobile_phone,email,password);
                profiles.add(profile);

            } while (cursor.moveToNext()) ;
        }
        sqLiteDatabase.close();

        return profiles;
    }
    public boolean loginAttempt(String username,String password){
        SQLiteDatabase sqLiteDatabase=databaseAccess.getWritableDatabase();
        // The ContentValues object create a map of values, where the columns are the keys
        System.out.println(UserProfileTable.check_login(username, password));
        Cursor cursor = sqLiteDatabase.rawQuery(UserProfileTable.check_login(username,password),null);
        if(cursor.getCount() <= 0){
            cursor.close();
            sqLiteDatabase.close();
            return false;
        }
        cursor.close();
        sqLiteDatabase.close();
        return true;

    }
    public UserProfile getProfile(String login){
        SQLiteDatabase sqLiteDatabase=databaseAccess.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(UserProfileTable.select_unique(login),null);
        if(cursor.getCount() > 1){
            cursor.close();
            sqLiteDatabase.close();
            return null;
        }
        else{
            if(cursor != null && cursor.moveToFirst()) {
                String name = cursor.getString(cursor.getColumnIndex(UserProfileTable.COLUMN_NAME_NAME));
                String surname = cursor.getString(cursor.getColumnIndex(UserProfileTable.COLUMN_NAME_SURNAME));
                String username = cursor.getString(cursor.getColumnIndex(UserProfileTable.COLUMN_NAME_USERNAME));
                String birthday = cursor.getString(cursor.getColumnIndex(UserProfileTable.COLUMN_NAME_BIRTHDAY));
                String mobile_phone = cursor.getString(cursor.getColumnIndex(UserProfileTable.COLUMN_NAME_MOBILE_PHONE));
                String email = cursor.getString(cursor.getColumnIndex(UserProfileTable.COLUMN_NAME_EMAIL));
                String password = cursor.getString(cursor.getColumnIndex(UserProfileTable.COLUMN_NAME_PASSWORD));

                // Convert to UserProfile object.
                UserProfile profile = new UserProfile(name, surname, username, birthday, mobile_phone, email, password);
                sqLiteDatabase.close();
                return profile;
            }
            else{

                return null;
            }
        }
    }
    public boolean checkUser(UserProfile profile){
        SQLiteDatabase sqLiteDatabase=databaseAccess.getWritableDatabase();
        // The ContentValues object create a map of values, where the columns are the keys
        Cursor cursor = sqLiteDatabase.rawQuery(UserProfileTable.check_users(profile.getUsername(),profile.getEmail()),null);
        if(cursor.getCount() <= 0){
            cursor.close();
            sqLiteDatabase.close();
            return true;
        }
        cursor.close();
        sqLiteDatabase.close();
        return false;
    }

}
