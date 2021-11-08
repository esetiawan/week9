package id.ac.week10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements AddFragment.OnFragmentInteractionListener{
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

    @Override
    public void onFragmentInteraction(Mahasiswa m) {
        arrdata.add(m);
        //Toast.makeText(this,"Activity menerima data mhs",
        //        Toast.LENGTH_LONG).show();
        Log.i("INFO","Activity menerima data mhs");
    }
}