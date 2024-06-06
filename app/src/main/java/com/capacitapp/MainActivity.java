package com.capacitapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.capacitapp.DBHelper.DBHelper;
import com.capacitapp.adapters.CursoAdapter;
import com.capacitapp.models.Curso;
import com.capacitapp.utils.UserPreferences;
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

    private static final int REQUEST_PERMISSION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);

        imgProfile = findViewById(R.id.profile_image);

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

        checkPermissions();
    }

    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_MEDIA_IMAGES}, REQUEST_PERMISSION);
            } else {
                loadProfileImage();
            }
        } else {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
            } else {
                loadProfileImage();
            }
        }
    }

    private void loadProfileImage() {
        String imageUri = UserPreferences.getUserProfileImage(this);
        if (imageUri != null) {
            imgProfile.setImageURI(Uri.parse(imageUri));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Gracias por conceder los permisos.", Toast.LENGTH_SHORT).show();
                loadProfileImage();
            } else {
                Toast.makeText(this, "No podemos realizar el registro si no nos concedes los permisos para leer el almacenamiento.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void searchCourses(String query) {
        List<Curso> cursos = dbHelper.searchCursos(query);
        if (cursos.isEmpty()) {
            Toast.makeText(this, "No se encontraron resultados", Toast.LENGTH_SHORT).show();
            Log.d("MainActivity", "No se encontraron resultados para: " + query);
        } else {
            Log.d("MainActivity", "Resultados encontrados para: " + query);
            for (Curso curso : cursos) {
                Log.d("MainActivity", "Curso: " + curso.getName());
            }
            showSearchResults(cursos);
        }
    }

    private void showSearchResults(List<Curso> cursos) {
        cursoAdapter.updateCursos(cursos);
    }
}
