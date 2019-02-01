package com.gp3.enkasa;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.gp3.enkasa.Model.Json.Data;

public class AlojamientosActivity extends AppCompatActivity implements AlojamientoFragment.OnListFragmentInteractionListener {

    public static String LANG = "es";

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.lugares_navigation_search:
                    mTextMessage.setText(R.string.lugares_navigation_search);
                    return true;
                case R.id.lugares_navigation_center:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.lugares_navigation_profile:
                    mTextMessage.setText(R.string.lugares_navigation_profile);
                    return true;
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
            args.putSerializable(AlojamientoFragment.ARG_DATA, getIntent().getExtras().getSerializable(LoginActivity.LUGARES));
            fragment.setArguments(args);

            transaction.add(R.id.fragment_alojamiento_list, fragment);
            transaction.commit();
        }
    }

    @Override
    public void onListFragmentInteraction(Data data, int position) {

    }
}
