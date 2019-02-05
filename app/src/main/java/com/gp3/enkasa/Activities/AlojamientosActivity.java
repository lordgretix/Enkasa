package com.gp3.enkasa.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.gp3.enkasa.Fragments.AlojamientoFragment;
import com.gp3.enkasa.Models.Json.Models.Data;
import com.gp3.enkasa.Models.Json.JsonData;
import com.gp3.enkasa.Models.Json.Models.Traducciones;
import com.gp3.enkasa.R;

import java.util.ArrayList;
import java.util.HashMap;

public class AlojamientosActivity extends AppCompatActivity implements AlojamientoFragment.OnListFragmentInteractionListener {

    public static String LANG = "es";
    public static JsonData jsonData = null;
    public static HashMap<String, ArrayList<String>> provs;

    private TextView mTextMessage;
    private ConstraintLayout mFilterOptions;
    private ImageView mFilterOptionIcon;
    private BottomNavigationView mBottomNavigationView;
    private Spinner mSelectMunicipio;
    private Spinner mSelectTerritorio;

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

        mBottomNavigationView = findViewById(R.id.navigation);

        //Filters
        mFilterOptions = findViewById(R.id.filter_options);
        mFilterOptionIcon = findViewById(R.id.filter_options_icon);

        provs = jsonData.getData().poblacionesByProvincias();

        mSelectTerritorio = findViewById(R.id.selectTerritorio);
        ArrayAdapter<String> territorioAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1);
        territorioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSelectTerritorio.setAdapter(territorioAdapter);

        for(String provincia : provs.keySet()){
            territorioAdapter.add(provincia);
        }
        territorioAdapter.notifyDataSetChanged();

        mSelectMunicipio = findViewById(R.id.selectMunicipio);

        mSelectTerritorio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ArrayAdapter<String> municipioAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, android.R.id.text1);
                municipioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSelectMunicipio.setAdapter(municipioAdapter);

                for(Object poblacion : provs.get(mSelectTerritorio.getAdapter().getItem(position))){
                    String pob = (String) poblacion;
                    if(pob.length()>15){
                        pob=pob.substring(0,15);
                    }
                    municipioAdapter.add(pob);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ArrayAdapter<String> municipioAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, android.R.id.text1);
                municipioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSelectMunicipio.setAdapter(municipioAdapter);
            }
        });




        mFilterOptionIcon.setOnClickListener(new View.OnClickListener() {
            private boolean expanded = false;

            @Override
            public void onClick(View v) {

                if (expanded) {
                    collapse(mFilterOptions);
                } else {
                    expand(mFilterOptions);
                }
                expanded = !expanded;
            }

            private void expand(final View v) {

                v.setVisibility(View.VISIBLE);
                Animation a = new Animation() {
                    @Override
                    protected void applyTransformation(float interpolatedTime, Transformation t) {
                        v.getLayoutParams().height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                        v.requestLayout();
                    }

                    @Override
                    public boolean willChangeBounds() {
                        return true;
                    }
                };
                v.startAnimation(a);
            }

            private void collapse(final View v) {

                Animation a = new Animation() {
                    @Override
                    protected void applyTransformation(float interpolatedTime, Transformation t) {
                        if (interpolatedTime == 1) {
                            v.setVisibility(View.GONE);
                        } else {
                            v.getLayoutParams().height = 1;
                            v.requestLayout();
                        }
                    }

                    @Override
                    public boolean willChangeBounds() {
                        return true;
                    }
                };

                v.startAnimation(a);
            }

        });

        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentManager manager = getSupportFragmentManager();

        Fragment fragment = manager.findFragmentById(R.id.fragment_alojamiento_list);

        if (fragment == null) {
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
