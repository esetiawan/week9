package id.ac.week9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ArrayList<Mahasiswa> arrdata = new ArrayList<Mahasiswa>();
    BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.navigation);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment f;
                switch(item.getItemId()) {
                    case R.id.item_add:
                        f = new AddFragment();
                        getSupportFragmentManager().beginTransaction().
                                replace(R.id.container, f).commit();
                        return true;
                    case R.id.item_list:
                        f = new ListFragment();
                        getSupportFragmentManager().beginTransaction().
                                replace(R.id.container, f).commit();
                        return true;
                }
                return false;
            }
        });
    }
}