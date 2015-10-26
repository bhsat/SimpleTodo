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

public class EditItemActivity extends AppCompatActivity {

    private String itemText;
    private EditText editListItem;
    private int position;
    private String priority;
    ArrayAdapter<CharSequence> adapter;
    Spinner spinner;
    Intent editData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.todo_priority, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        itemText = getIntent().getStringExtra("item");
        position = getIntent().getIntExtra("todoPosition", 0);
        priority = getIntent().getStringExtra("priority");
        editListItem = (EditText) findViewById(R.id.editText);
        editListItem.setText(itemText);
        editListItem.setSelection(itemText.length());
        onItemSelectedListener();
        //setContentView(editListItem);
    }

    public void editItem(View v) {
        EditText editItem = (EditText) findViewById(R.id.editText);
        editData = new Intent();
        editData.putExtra("editItem", editItem.getText().toString());
        editData.putExtra("todoPosition", position);
        setResult(RESULT_OK, editData);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_item, menu);
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
                Toast.makeText(EditItemActivity.this, "selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
