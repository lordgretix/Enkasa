package com.gp3.enkasa.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.gp3.enkasa.Fragments.AlojamientoFragment;
import com.gp3.enkasa.Models.Json.Models.Data;
import com.gp3.enkasa.Models.Json.JsonData;
import com.gp3.enkasa.Models.Json.Models.Traducciones;
import com.gp3.enkasa.R;

public class AlojamientosActivity extends AppCompatActivity implements AlojamientoFragment.OnListFragmentInteractionListener {

    public static String LANG = "es";
    public static JsonData jsonData = null;

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.lugares_navigation_search:
                    mTextMessage.setText(R.string.lugares_navigation_search);
                    break;
                case R.id.lugares_navigation_center:
                    mTextMessage.setText(R.string.title_dashboard);
                    break;
                case R.id.lugares_navigation_profile:
                    mTextMessage.setText(R.string.lugares_navigation_profile);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alojamientos);

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentManager manager = getSupportFragmentManager();

        Fragment fragment = manager.findFragmentById(R.id.fragment_alojamiento_list);

        if(fragment==null){
            FragmentTransaction transaction = manager.beginTransaction();
            fragment = new AlojamientoFragment();

            Bundle args = new Bundle();
            args.putInt(AlojamientoFragment.ARG_COLUMN_COUNT, 1);

            fragment.setArguments(args);

            transaction.add(R.id.fragment_alojamiento_list, fragment);
            transaction.commit();
        }

    }

    @Override
    public void onListFragmentInteraction(Traducciones tr) {

    }
}
