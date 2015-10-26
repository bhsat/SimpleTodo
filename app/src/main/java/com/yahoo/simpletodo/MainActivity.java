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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> items;
   // ArrayList<Todo> todoItems;
    //ArrayAdapter<String> itemsAdapter;
    private ArrayList<HashMap<String, String>> list;
    SimpleAdapter todoItemsAdapter;
    ListView lvItems;
    private final int REQUEST_CODE = 20;
    private final int REQUEST_ADDCODE = 10;
    TodoDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvItems = (ListView) findViewById(R.id.listItems);
        dbHelper = TodoDbHelper.getsInstance(this);
        //readItems();
        //todoItems = new ArrayList<>();
        //todoItems.addAll(dbHelper.getAllItems());
        list = new ArrayList<HashMap<String, String>>();
        for(Todo todo : dbHelper.getAllItems()) {
            HashMap<String, String> map = new HashMap<>();
            map.put("item", todo.getTodoItem());
            map.put("priority", todo.getTodoPriority());
            list.add(map);
        }
        todoItemsAdapter = new SimpleAdapter(this, list, R.layout.todo_list, new String[]{"item", "priority"}, new int[]{R.id.items, R.id.priority});
        lvItems.setAdapter(todoItemsAdapter);
        setupListViewListener();
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> map = (HashMap<String, String>) todoItemsAdapter.getItem(position);
                String itemValue = map.get("item");
                String priority = map.get("priority");
                //Toast.makeText(MainActivity.this, "clicked-"+items, Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this, EditItemActivity.class);
                i.putExtra("item", itemValue);
                i.putExtra("todoPosition", position);
                i.putExtra("priority", priority);
                startActivityForResult(i, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                String text = data.getExtras().getString("editItem");
                int pos = data.getExtras().getInt("todoPosition");
                String priority = data.getExtras().getString("priority");
                list.remove(pos);
                Todo todo = new Todo(text, priority, pos);
                dbHelper.updateItem(todo);
                HashMap<String, String> map = new HashMap<>();
                map.put("item", text);
                map.put("priority", priority);
                list.add(pos, map);
                //todoItems.add(pos, item);
                //items.add(pos, text);
                todoItemsAdapter.notifyDataSetChanged();
                //writeItems();
            } else if (requestCode == REQUEST_ADDCODE) {
                //ArrayList<HashMap<String, String>> itemList = new ArrayList<HashMap<String, String>>();
                list.clear();
                for (Todo todo : dbHelper.getAllItems()) {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("item", todo.getTodoItem());
                    map.put("priroity", todo.getTodoPriority());
                    list.add(map);
                }
                //todoItems.addAll(ite);
                todoItemsAdapter.notifyDataSetChanged();
            }
        }
    }

    private void readItems() {
        File fileDir = getFilesDir();
        File todoFile = new File(fileDir, "todofile.txt");
        try {
            items = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            items = new ArrayList<String>();
        }
    }

    private void writeItems() {
        File fileDir = getFilesDir();
        File todoFile = new File(fileDir, "todofile.txt");
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean setupListViewListener() {
        lvItems.setLongClickable(true);
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                todoItemsAdapter.notifyDataSetChanged();
                dbHelper.deleteItem(position);
                list.remove(position);
                return true;
            }
        });
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        if (id == R.id.add_button) {
            Intent addIntent = new Intent(MainActivity.this, AddActivity.class);
            startActivityForResult(addIntent, REQUEST_ADDCODE);
        }

        return super.onOptionsItemSelected(item);
    }
}
