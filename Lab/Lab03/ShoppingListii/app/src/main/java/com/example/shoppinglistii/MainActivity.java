package com.example.shoppinglistii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // default the item 1
    public static final int ITEM_REQUEST = 1;
    // assign 10 empty text view in a array
    private final TextView[] item = new TextView[10];
    // creating array list of items
    private ArrayList<String> itemList = new ArrayList<>(10);
    //    variable for edit text
    private EditText storeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assigning the empty text view items with array item list by id
        item[0] = findViewById(R.id.item1);
        item[1] = findViewById(R.id.item2);
        item[2] = findViewById(R.id.item3);
        item[3] = findViewById(R.id.item4);
        item[4] = findViewById(R.id.item5);
        item[5] = findViewById(R.id.item6);
        item[6] = findViewById(R.id.item7);
        item[7] = findViewById(R.id.item8);
        item[8] = findViewById(R.id.item9);
        item[9] = findViewById(R.id.item10);
        // reference the Edit Text field for map address
        storeEditText = findViewById(R.id.map_address);

        if (savedInstanceState != null) {
            itemList = savedInstanceState.getStringArrayList("ItemListStringArray");
            if (itemList != null && itemList.size() > 0) {
                for (int i = 0; i < itemList.size(); i++) {
                    if (itemList.size() > 10) {
                        Toast.makeText(MainActivity.this, "Cannot add more items to the list", Toast.LENGTH_LONG).show();
                        break;
                    }
                    item[i].setVisibility(View.VISIBLE);
                    item[i].setText(itemList.get(i));
                }
            }
        }

    }

    // Save the Activity instance state with onSaveInstanceState
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (itemList.size() != 0) {
            outState.putStringArrayList("ItemListStringArray", itemList);
        }
    }

    public void addItem(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, ITEM_REQUEST);
    }

    // from second activity to main activity

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ITEM_REQUEST) {
            if (resultCode == RESULT_OK) {
                String itemString = data.getStringExtra(SecondActivity.EXTRA_ITEMS);
                itemList.add(itemString);
                for (int i = 0; i < itemList.size(); i++) {
                    if (itemList.size() > 10) {
                        Toast.makeText(MainActivity.this, "Cannot add more items to the list", Toast.LENGTH_LONG).show();
                        break;
                    }
                    item[i].setVisibility(View.VISIBLE);
                    item[i].setText(itemList.get(i));
                }
            }
        }
    }

    // function for search store
    @SuppressLint("QueryPermissionsNeeded")
    public void searchStore(View view) {
        // get text as string
        String location = storeEditText.getText().toString();
        // default address
        String url = null;
        // encode the url in utf8
        try {
            url = URLEncoder.encode(location, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // search query for the map api
        Uri address = Uri.parse("https://www.google.com/maps/search/?api=1&query=" + url);
        // implicit intent for the address
        Intent intent = new Intent(Intent.ACTION_VIEW, address);
        // open in the map apps or show error
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "There was a problem with the store search.", Toast.LENGTH_LONG).show();
        }
    }
}