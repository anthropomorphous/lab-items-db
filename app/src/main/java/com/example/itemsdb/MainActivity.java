package com.example.itemsdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // refs to components of UI
    Button addButton;
    EditText editName, editType, editCost;
    ListView itemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.button);
        editName = findViewById(R.id.editName);
        editType = findViewById(R.id.editType);
        editCost = findViewById(R.id.editCost);
        itemsList = findViewById(R.id.itemsList);

        // button listeners
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // checking blank fields (to avoid app crash)
                try {
                    ItemModel itemModel = new ItemModel(-1,
                            editName.getText().toString(),
                            editType.getText().toString(),
                            Float.parseFloat(editCost.getText().toString()));

                    Toast.makeText(MainActivity.this, itemModel.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Заполните все поля!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
