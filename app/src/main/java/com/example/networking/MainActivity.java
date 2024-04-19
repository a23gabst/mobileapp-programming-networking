package com.example.networking;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "HTTPS_URL_TO_JSON_DATA_CHANGE_THIS_URL";
    private final String JSON_FILE = "mountains.json";

    ArrayList<Mountain> items = new ArrayList<>();
    ArrayList<RecyclerViewItem> recyclerViewItems = new ArrayList<>();
    RecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items.add(new Mountain("Matterhorn"));
        items.add(new Mountain("Mont Blanc"));
        items.add(new Mountain("Denali"));
        items.add(new Mountain("K2"));

        for (int i=0;i< items.size();i++) {
            Log.d("Injera", items.get(i).toString());
            recyclerViewItems.add(new RecyclerViewItem(items.get(i).toString()));
        }

        adapter = new RecyclerViewAdapter(this, recyclerViewItems, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(RecyclerViewItem item) {
                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView view = findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);

        new JsonFile(this, this).execute(JSON_FILE);
    }


    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", json);
    }

}
