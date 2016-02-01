package com.ist402.jdm5908.todotoday;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    // Define the database and table
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "toDo_Today";
    private static final String DATABASE_TABLE = "toDo_Items";

    // Define the column names for the table
    private static final String KEY_TASK_ID = "_id";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_IS_DONE = "is_done";

    private int taskCount;  // Counts number of tasks in the list

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String sqlStatement = "CREATE TABLE " + DATABASE_TABLE + "(" + KEY_TASK_ID + " INTEGER PRIMARY KEY, " + KEY_DESCRIPTION + " TEXT, " + KEY_IS_DONE + " INTEGER" + ")";
        database.execSQL(sqlStatement);
        taskCount = 0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(database);
    }

    //************** Database operations: ADD, EDIT, DELETE

    // Add a ToDO task to the database
    public void addToDoItem(ToDo_Item task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        taskCount++;

        // Add key-value pair information for the task description
        values.put(KEY_TASK_ID, taskCount);

        // Add key-value pair information for the task description
        values.put(KEY_DESCRIPTION, task.getDescription());  // Task name

        // Add key-value pair information for is_done
        values.put(KEY_IS_DONE, task.getIs_done());

        // Insert the row in the table
        db.insert(DATABASE_TABLE, null, values);

        // Close the database connection
        db.close();
    }


}
