package com.foysaldev.recyclarviewbyfoysal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foysaldev.recyclarviewbyfoysal.Adapters.RecipeAdapter;
import com.foysaldev.recyclarviewbyfoysal.Classes.RecyclerItemClickListener;
import com.foysaldev.recyclarviewbyfoysal.Models.RecipeModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<RecipeModel> list;
    RecipeAdapter myadapter;
    String[] recipeHeading;
    int[] imageResourceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclarView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        list = new ArrayList<RecipeModel>();

        recipeHeading = new String[]{
                "Fried Chicken",
                "Homburger", "Burger",
                "Tomato", "Food No 5",
                "Food No 6", "Food No 7", "Food No 8"};

        imageResourceId = new int[]{
                R.drawable.image1,
                R.drawable.image2,
                R.drawable.image3,
                R.drawable.image4,
                R.drawable.image5,
                R.drawable.image6,
                R.drawable.image7,
                R.drawable.image8,};
        getData();

//      LinearLayoutManager layoutManager = new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL ,true);
//      recyclerView.setLayoutManager(layoutManager);

//      StaggeredGridLayoutManager staggered = new StaggeredGridLayoutManager (2, StaggeredGridLayoutManager.HORIZONTAL);
//      recyclerView.setLayoutManager(staggered);
//      GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
//      recyclerView.setLayoutManager(gridLayoutManager);


        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener
                (this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        switch (position) {
                            case 0:
                                Intent intent = new Intent(MainActivity.this, ScrollingActivity.class);
                                startActivity(intent);
                                break;

                            case 1:
                                Toast.makeText(MainActivity.this, "Second Item Is Clicked", Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Toast.makeText(MainActivity.this, "Third Item Is Clicked", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                        }

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                        switch (position) {
                            case 0:
                                Toast.makeText(MainActivity.this, "Get  20% Discount", Toast.LENGTH_SHORT).show();
                                break;

                            case 1:
                                Toast.makeText(MainActivity.this, "LongItem Clicked 1", Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Toast.makeText(MainActivity.this, "LongItem Clicked 2", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                        }

                    }
                }
                ));
    }

    private void getData() {
        for (int i = 0; i < recipeHeading.length; i++) {
            RecipeModel recipeModel = new RecipeModel(imageResourceId[i], recipeHeading[i]);
            list.add(recipeModel);
        }
        myadapter = new RecipeAdapter(this,list);
        recyclerView.setAdapter(myadapter);
        myadapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        MenuItem menuItem = menu.findItem(R.id.SearchViewid);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MIN_VALUE);
        searchView.setQueryHint("Search Here");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myadapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}