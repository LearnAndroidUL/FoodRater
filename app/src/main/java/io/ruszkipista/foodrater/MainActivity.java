package io.ruszkipista.foodrater;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Grid Layout wit 2 columns:
        // recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);

        // connect RecyclerView with our io.ruszkipista.foodrater.NameAdapter
        final FoodAdapter nameAdapter = new FoodAdapter(this);
        recyclerView.setAdapter(nameAdapter);

        nameAdapter.addFood();
        nameAdapter.addFood();
        nameAdapter.addFood();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add a name row
                Food food = nameAdapter.addFood();
                Snackbar.make(view, getString(R.string.snackbar_added, food.getName()),
                        Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }
}