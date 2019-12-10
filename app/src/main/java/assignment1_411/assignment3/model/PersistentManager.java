package assignment1_411.assignment3.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

public class PersistentManager extends SQLiteOpenHelper {

    Context mContext;
    SQLiteDatabase mSQLiteDatabase;
    File dbFile;

    public PersistentManager(Context context) {
        super(context,"student",null,1);
        this.mContext = context;
        dbFile = mContext.getDatabasePath("student");
        mSQLiteDatabase.openOrCreateDatabase(dbFile, null);
        mSQLiteDatabase = this.getWritableDatabase();
        onCreate(mSQLiteDatabase);
    }

    public boolean checkDataBase(){
        File databaseFile = new File(dbFile.getPath());
        return databaseFile.exists();
    }

    //Add objects to database
    public void addStudent(String FirstName, String LastName, int cwid){



        if (checkDataBase()){
            System.out.println("DID IT");
        }
        //Should throw exception here
        else if(!checkDataBase()){
            System.out.println("DID SHIT");
        }

        ContentValues cv = new ContentValues();
        cv.put("FirstName", FirstName);
        //System.out.println(random());
        cv.put("LastName", LastName);
        //System.out.println(key);
        cv.put("cwid", cwid);
        Log.d("Students", FirstName + LastName + " is entered into the database at cwid: " + cwid);
        mSQLiteDatabase.insert("Student", null , cv);
        //db.close();
    }

    public void addCourse(String CourseID, String Grade, int cwid){
        if (checkDataBase()){
            System.out.println("DID IT");
        }
        //Should throw exception here
        else if(!checkDataBase()){
            System.out.println("DID SHIT");
        }

        ContentValues cv = new ContentValues();


        cv.put("CourseID", CourseID);
        cv.put("Grade", Grade);
        cv.put("cwid", cwid);

        Log.d("Courses", "Course enterd: " + CourseID + " Grade: " + Grade + " at cwid: " + cwid);
        mSQLiteDatabase.insert("CourseEnrollment", null , cv);
        //db.close();
    }

    public void updateFirstNameColumn(int cwid, String NewFirstName){
        Cursor c = mSQLiteDatabase.rawQuery("UPDATE Student SET FirstName = " + "\'" + NewFirstName + "\'" +" WHERE cwid = " + cwid, null);
        if (c.getCount() > 0) {
            while (c.moveToNext()) {
            }
        }
    }

    public void updateLastNameColumn(int cwid, String NewLastName){
        Cursor c = mSQLiteDatabase.rawQuery("UPDATE Student SET LastName = " +  "\'" + NewLastName + "\'" +" WHERE cwid = " + cwid , null);
        if (c.getCount() > 0) {
            while (c.moveToNext()) {
            }
        }
    }

    public void updatecwidColumn(int Oldcwid, int Newcwid){
        Cursor c = mSQLiteDatabase.rawQuery("UPDATE Student SET cwid = " +   Newcwid  +" WHERE cwid = " + Oldcwid, null);
        if (c.getCount() > 0) {
            while (c.moveToNext()) {
            }
        }
    }

    public void updatecwidCourses(int Oldcwid, int Newcwid){
        Cursor c = mSQLiteDatabase.rawQuery("UPDATE CourseEnrollment SET cwid = " +   Newcwid  +" WHERE cwid = " + Oldcwid, null);
        if (c.getCount() > 0) {
            while (c.moveToNext()) {
            }
        }
    }

    //Get Objects from database
    public ArrayList<Student> retrieveStudents() {
        ArrayList<Student> objList;
        StudentDB.getInstance().setPersonList(objList = new ArrayList<>());
        Cursor cursor = mSQLiteDatabase.query("Student", null,null,null,null,null,null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Log.d("Retrieved ", cursor.getString(cursor.getColumnIndex("FirstName")) + cursor.getString(cursor.getColumnIndex("LastName")) + " " + cursor.getInt(cursor.getColumnIndex("cwid")));

                ArrayList<CourseEnrollment> oList = new ArrayList<>();
                Cursor c = mSQLiteDatabase.query("CourseEnrollment", null,null,null,null,null,null);
                if (c.getCount() > 0) {
                    while (c.moveToNext()) {
                        if(cursor.getInt(cursor.getColumnIndex("cwid")) == c.getInt(c.getColumnIndex("cwid"))) {
                            oList.add(new CourseEnrollment(c.getString(c.getColumnIndex("CourseID")), c.getString(c.getColumnIndex("Grade"))));
                            Log.d("Retrieved ", c.getString(c.getColumnIndex("CourseID")) + c.getString(c.getColumnIndex("Grade")) + " " + c.getInt(c.getColumnIndex("cwid")));
                        }
                    }
                }
                else if (cursor.getCount() == 0){
                    oList = null;
                }
                StudentDB.getInstance().createStudentObjects(cursor.getString(cursor.getColumnIndex("FirstName")), cursor.getString(cursor.getColumnIndex("LastName")), cursor.getInt(cursor.getColumnIndex("cwid")), oList);

            }
        }

        else if (cursor.getCount() == 0){
            objList = null;
        }
        return objList;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("DROP TABLE Student");
        //db.execSQL("DROP TABLE CourseEnrollment;");
        db.execSQL("CREATE TABLE IF NOT EXISTS Student (FirstName TEXT NOT NULL, LastName TEXT NOT NULL, cwid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS CourseEnrollment (CourseID TEXT PRIMARY KEY NOT NULL, Grade TEXT NOT NULL, cwid INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
