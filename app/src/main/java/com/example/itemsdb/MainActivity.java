package com.example.itemsdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // refs to components of UI
    Button addButton;
    EditText editName, editType, editCost;
    ListView itemsList;
    ArrayAdapter itemsAdapter;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.button);
        editName = findViewById(R.id.editName);
        editType = findViewById(R.id.editType);
        editCost = findViewById(R.id.editCost);
        itemsList = findViewById(R.id.itemsList);

        dbHelper = new DBHelper(MainActivity.this);

        // for working with the list we need an adapter
        // in this case we will use "simple_list_item_1"
        showList(dbHelper);

        // button listeners
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                ItemModel itemModel;
                // checking blank fields (to avoid app crash)
                try {
                    itemModel = new ItemModel(-1,
                            editName.getText().toString(),
                            editType.getText().toString(),
                            Float.parseFloat(editCost.getText().toString()));
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Заполните все поля!", Toast.LENGTH_SHORT).show();
                    itemModel = new ItemModel(-1, "Error", "Error", 0);
                }

                boolean success = dbHelper.addItem(itemModel);

                // so now list will be updated automatically when smth new is added to the db
                showList(dbHelper);
            }
        });

        itemsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long id) {
                ItemModel clickedItem = (ItemModel) adapterView.getItemAtPosition(i);
                dbHelper.deleteItem(clickedItem);
                showList(dbHelper);
                Toast.makeText(MainActivity.this, "Запись удалена", Toast.LENGTH_SHORT).show();

                return true;
            }
        });

        itemsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                ItemModel clickedItem = (ItemModel) adapterView.getItemAtPosition(i);

                try {
                    dbHelper.editItem(clickedItem,
                            editName.getText().toString(),
                            editType.getText().toString(),
                            Float.parseFloat(editCost.getText().toString()));

                    showList(dbHelper);
                    Toast.makeText(MainActivity.this, "Запись изменена", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Заполните поля", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showList(DBHelper dbHelper1) {
        itemsAdapter = new ArrayAdapter<ItemModel>(MainActivity.this, android.R.layout.simple_list_item_1, dbHelper1.getEveryone());
        itemsList.setAdapter(itemsAdapter);
    }
}
