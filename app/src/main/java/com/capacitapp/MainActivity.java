package com.capacitapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.capacitapp.DBHelper.DBHelper;
import com.capacitapp.adapters.CursoAdapter;
import com.capacitapp.models.Curso;
import com.google.android.material.tabs.TabLayout;
import com.capacitapp.adapters.MyViewPagerAdapter;

import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private MyViewPagerAdapter myViewPagerAdapter;
    private ImageView imgProfile;

    private SearchView searchView;
    private DBHelper dbHelper;

    private RecyclerView recyclerView;
    private CursoAdapter cursoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.view_pager);
        searchView = findViewById(R.id.search);
        myViewPagerAdapter = new MyViewPagerAdapter(this);

        viewPager2.setAdapter(myViewPagerAdapter);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cursoAdapter = new CursoAdapter(new ArrayList<>());
        recyclerView.setAdapter(cursoAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });



        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        imgProfile = findViewById(R.id.profile_image);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
                startActivity(intent);
            }
        });

        /*viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });*/

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchCourses(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchCourses(newText);
                return false;
            }
        });
    }

    private void searchCourses(String query) {
        List<Curso> cursos = dbHelper.searchCursos(query);
        if (cursos.isEmpty()) {
            Toast.makeText(this, "No se encontraron resultados", Toast.LENGTH_SHORT).show();
        } else {
            showSearchResults(cursos);
        }
    }

    private void showSearchResults(List<Curso> cursos) {
        cursoAdapter.updateCursos(cursos);
    }
}
