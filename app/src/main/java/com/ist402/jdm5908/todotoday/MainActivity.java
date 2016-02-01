package com.ist402.jdm5908.todotoday;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Test 1: create the database
        DBHelper database = new DBHelper(this);

        // Adding items
        database.addToDoItem(new ToDo_Item(1, "Read Hamlet", 1));
        database.addToDoItem(new ToDo_Item(2, "Study for exam", 1));
        database.addToDoItem(new ToDo_Item(3, "Call Andy and Sam", 0));
        database.addToDoItem(new ToDo_Item(4, "Create a newletter", 1));
        database.addToDoItem(new ToDo_Item(5, "Buy a dog", 0));

        // Display all the task items in the table
        String taskItemList = "\n";
        ArrayList<ToDo_Item> taskList = database.getAllTaskItems();
        for (int i = 0; i < database.getTaskCount(); i++) {
            ToDo_Item task = taskList.get(i);
            taskItemList += "\n" + task.getDescription() + "\t" + task.getIs_done();
        }
        Log.v("DATABASE RECORDS", taskItemList);

        // Test 2: modify a record
        database.editTaskItem(new ToDo_Item(1, "Read newspaper", 1));

        // Test 3: display a specific record
        ToDo_Item anItem = database.getToDo_Task(2);
        Log.v("DATABASE RECORDS", anItem.getDescription());

        // Test 4: delete a record
        database.deleteTaskItem(new ToDo_Item(15, "Buy a dog", 0));

        // Display all the task items in the table
        taskItemList = "\n";
        taskList = database.getAllTaskItems();
        for (int i = 0; i < database.getTaskCount(); i++) {
            ToDo_Item task = taskList.get(i);
            taskItemList += "\n" + task.getDescription() + "\t" + task.getIs_done();
        }
        Log.v("DATABASE RECORDS", taskItemList);
    }
}
