package com.yahoo.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class AddActivity extends AppCompatActivity {

    TodoDbHelper dbHelper;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add2);
        dbHelper = TodoDbHelper.getsInstance(this);
        spinner = (Spinner) findViewById(R.id.addSpinner);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.todo_priority, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void onAddItem(View v) {
        Todo item = new Todo();
        EditText newItem = (EditText) findViewById(R.id.addEditText);
        String itemText = newItem.getText().toString();
        //itemsAdapter.add(itemText);
        item.setTodoItem(itemText);
        newItem.setText("");
        long id = dbHelper.addTodoItem(item);
        //writeItems();
        Intent i = new Intent();
        setResult(RESULT_OK, i);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onItemSelectedListener() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*String priority = spinner.getItemAtPosition(todoPosition).toString();
                if (priority !=null) {
                    editData.putExtra("priroity", priority);
                }*/
                Toast.makeText(AddActivity.this, "selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
