package com.ist402.jdm5908.todotoday;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

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
        String sqlStatement = "CREATE TABLE " + DATABASE_TABLE + "("
                + KEY_TASK_ID + " INTEGER PRIMARY KEY, "
                + KEY_DESCRIPTION + " TEXT, "
                + KEY_IS_DONE + " INTEGER" + ")";
        database.execSQL(sqlStatement);
        taskCount = 0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(database);
    }

    //************** Database operations: ADD, EDIT, DELETE
    // Add a task to the database
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

    // Edit a task in the database
    public void editTaskItem(ToDo_Item task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_DESCRIPTION, task.getDescription());
        values.put(KEY_IS_DONE, task.getIs_done());

        db.update(DATABASE_TABLE, values, KEY_TASK_ID + " = ? ",
                new String[]{
                        String.valueOf(task.get_id())
                });
        db.close();
    }

    // Return a secific task in the database
    public ToDo_Item getToDo_Task(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                DATABASE_TABLE,
                new String[]{KEY_TASK_ID, KEY_DESCRIPTION, KEY_IS_DONE},
                KEY_TASK_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        ToDo_Item task = new ToDo_Item(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getInt(2));
        db.close();
        return task;
    }

    // Delete a specific task from the database
    public void deleteTaskItem(ToDo_Item task) {
        SQLiteDatabase database = this.getReadableDatabase();

        // Delete the table row
        database.delete(DATABASE_TABLE, KEY_TASK_ID + " = ?",
                new String[]
                        {String.valueOf(task.get_id())});
        database.close();
    }

    public int getTaskCount() {
        return taskCount;
    }

    // Add a task to the database
    public ArrayList<ToDo_Item> getAllTaskItems() {
        ArrayList<ToDo_Item> taskList = new ArrayList<>();
        String queryList = "SELECT * FROM " + DATABASE_TABLE;

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(queryList, null);

        // Collect each row in the table
        if (cursor.moveToFirst()) {
            do {
                ToDo_Item task = new ToDo_Item();
                task.set_id(cursor.getInt(0));
                task.setDescription(cursor.getString(1));
                task.setIs_done(cursor.getInt(2));

                // Add to the query list
                taskList.add(task);
            }
            while (cursor.moveToNext());
        }
        return taskList;
    }
}
