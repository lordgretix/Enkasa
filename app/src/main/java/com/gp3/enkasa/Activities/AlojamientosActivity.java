package com.gp3.enkasa.Activities;

import android.content.res.Resources;
import android.content.Intent;
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
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.TextView;

import com.gp3.enkasa.Fragments.AlojamientoFragment;
import com.gp3.enkasa.Models.Json.Models.Data;
import com.gp3.enkasa.Models.Json.JsonData;
import com.gp3.enkasa.Models.Json.Models.Traducciones;
import com.gp3.enkasa.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class AlojamientosActivity extends AppCompatActivity implements AlojamientoFragment.OnListFragmentInteractionListener {

    public static String LANG = "es";
    public static JsonData jsonData = null;
    public static HashMap<String, ArrayList<String>> provs;
    public static final String INTENT_DETALLE_ID = AlojamientosActivity.class.getName()+".INTENT_DETALLE_ID";

    private TextView mTextMessage;
    private BottomNavigationView mBottomNavigationView;

    //Filters
    private ConstraintLayout mFilterOptions;
    private ImageView mFilterOptionIcon;
    private Spinner mSelectMunicipio;
    private Spinner mSelectTerritorio;
    private Spinner mSelectSortBy;
    private Switch mSwitchAgroturismo;
    private Switch mSwitchAlbergues;
    private Switch mSwitchCamping;
    private Switch mSwitchRural;
    private Switch mSwitchCertificado;
    private Switch mSwitchRestaurante;
    private Switch mSwitchTienda;
    private Switch mSwitchClub;
    private Switch mSwitchAutocaravana;
    private Switch mSwitchOrden;


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
        mSelectTerritorio = findViewById(R.id.selectTerritorio);
        mSelectMunicipio = findViewById(R.id.selectMunicipio);
        mSelectSortBy = findViewById(R.id.selectSortBy);
        mSwitchAgroturismo = findViewById(R.id.switchAgroturismo);
        mSwitchAlbergues = findViewById(R.id.switchAlbergues);
        mSwitchCamping = findViewById(R.id.switchCamping);
        mSwitchRural = findViewById(R.id.switchRural);
        mSwitchCertificado = findViewById(R.id.switchCertificado);
        mSwitchRestaurante = findViewById(R.id.switchRestaurante);
        mSwitchTienda = findViewById(R.id.switchTienda);
        mSwitchClub = findViewById(R.id.switchClub);
        mSwitchAutocaravana = findViewById(R.id.switchAutocaravana);
        mSwitchOrden = findViewById(R.id.switchOrden);

        provs = jsonData.getData().poblacionesByProvincias();

        // TerritorioSelect
        ArrayAdapter<String> provinciaAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1);
        provinciaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSelectTerritorio.setAdapter(provinciaAdapter);

        provinciaAdapter.add(getResources().getString(R.string.filter_select_todo_option));

        for(String provincia : provs.keySet()){
            provinciaAdapter.add(provincia);
        }
        provinciaAdapter.notifyDataSetChanged();

        ArrayAdapter<String> municipioAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, android.R.id.text1);
        municipioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSelectMunicipio.setAdapter(municipioAdapter);
        municipioAdapter.add(getResources().getString(R.string.filter_select_todo_option));
        municipioAdapter.notifyDataSetChanged();

        mSelectTerritorio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ArrayAdapter<String> municipioAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, android.R.id.text1);
                municipioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSelectMunicipio.setAdapter(municipioAdapter);
                municipioAdapter.add(getResources().getString(R.string.filter_select_todo_option));

                if(position!=0){
                    for(Object poblacion : provs.get(mSelectTerritorio.getAdapter().getItem(position))){
                        String pob = (String) poblacion;
                        if(pob.length()>15){
                            pob=pob.substring(0,15);
                        }
                        municipioAdapter.add(pob);
                    }
                }
                mSelectMunicipio.setSelection(0);
                updateFragmentUI();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ArrayAdapter<String> municipioAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, android.R.id.text1);
                municipioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSelectMunicipio.setAdapter(municipioAdapter);
                updateFragmentUI();
            }
        });

        mSelectMunicipio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateFragmentUI();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                updateFragmentUI();
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

        CompoundButton.OnCheckedChangeListener onSwitchChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateFragmentUI();
            }
        };


        mSwitchAgroturismo.setOnCheckedChangeListener(onSwitchChangeListener);
        mSwitchAlbergues.setOnCheckedChangeListener(onSwitchChangeListener);
        mSwitchCamping.setOnCheckedChangeListener(onSwitchChangeListener);
        mSwitchRural.setOnCheckedChangeListener(onSwitchChangeListener);
        mSwitchCertificado.setOnCheckedChangeListener(onSwitchChangeListener);
        mSwitchRestaurante.setOnCheckedChangeListener(onSwitchChangeListener);
        mSwitchTienda.setOnCheckedChangeListener(onSwitchChangeListener);
        mSwitchClub.setOnCheckedChangeListener(onSwitchChangeListener);
        mSwitchClub.setOnCheckedChangeListener(onSwitchChangeListener);
        mSwitchAutocaravana.setOnCheckedChangeListener(onSwitchChangeListener);
        mSwitchOrden.setOnCheckedChangeListener(onSwitchChangeListener);

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

    public void updateFragmentUI(){

        FragmentManager manager = getSupportFragmentManager();

        AlojamientoFragment fragment = (AlojamientoFragment) manager.findFragmentById(R.id.fragment_alojamiento_list);

        fragment.updateUI(filterTraducciones());
    }

    private ArrayList<Traducciones> filterTraducciones(){
        ArrayList<Traducciones> traducciones = new ArrayList<>();
        String[] tipos = getResources().getStringArray(R.array.alojaminetos_tipos);

        int size= jsonData.getData().getTraducciones(LANG).size();
        for (Traducciones tr : jsonData.getData().getTraducciones(LANG)) {

            if(mSelectTerritorio.getSelectedItemPosition()!=0 && !mSelectTerritorio.getSelectedItem().toString().equals(jsonData.getData().getProvinciaByIDs(tr.getCodPostal(), tr.getCodPoblacion()))) continue;

            if(mSelectMunicipio.getSelectedItemPosition()!=0 && !mSelectMunicipio.getSelectedItem().toString().equals(jsonData.getData().getPoblacionByIDs(tr.getCodPostal(), tr.getCodPoblacion()))) continue;

            if(mSwitchAgroturismo.isChecked() && !tr.getTipo().equals(tipos[2]) && !(mSwitchAlbergues.isChecked() || mSwitchCamping.isChecked() || mSwitchRural.isChecked()) ) continue;

            if(mSwitchAlbergues.isChecked() && !tr.getTipo().equals(tipos[0]) && !( mSwitchCamping.isChecked() || mSwitchRural.isChecked()) ) continue;

            if(mSwitchCamping.isChecked() && !tr.getTipo().equals(tipos[1]) && !( mSwitchRural.isChecked()) ) continue;

            if(mSwitchRural.isChecked() && !tr.getTipo().equals(tipos[3]) ) continue;

            //TODO: Arreglar estos

            if(mSwitchCertificado.isChecked() && tr.getCertificado()!=1) continue;

            if(mSwitchRestaurante.isChecked() && tr.getCertificado()!=1) continue;

            if(mSwitchTienda.isChecked() && tr.getCertificado()!=1) continue;

            if(mSwitchClub.isChecked() && tr.getCertificado()!=1) continue;

            if(mSwitchAutocaravana.isChecked() && tr.getCertificado()!=1) continue;


            traducciones.add(tr);
        }

        switch (mSelectSortBy.getSelectedItemPosition()){
            case 0:
                Collections.sort(traducciones, new Comparator<Traducciones>() {
                    @Override
                    public int compare(Traducciones t1, Traducciones t2) {
                        return t1.getNombre().compareToIgnoreCase(t2.getNombre());
                    }
                });

                break;
            case 1:
                Collections.sort(traducciones, new Comparator<Traducciones>() {
                    @Override
                    public int compare(Traducciones t1, Traducciones t2) {
                        String prov = jsonData.getData().getProvinciaByIDs(t1.getCodPostal(), t1.getCodPoblacion());
                        String prov2 = jsonData.getData().getProvinciaByIDs(t2.getCodPostal(), t2.getCodPoblacion());
                        return prov.compareToIgnoreCase(prov2);
                    }
                });
                break;
            case 2:
                Collections.sort(traducciones, new Comparator<Traducciones>() {
                    @Override
                    public int compare(Traducciones t1, Traducciones t2) {
                        String pob = jsonData.getData().getPoblacionByIDs(t1.getCodPostal(), t1.getCodPoblacion());
                        String pob2 = jsonData.getData().getPoblacionByIDs(t2.getCodPostal(), t2.getCodPoblacion());
                        return pob.compareToIgnoreCase(pob2);
                    }
                });
                break;
        }

        if(!mSwitchOrden.isChecked()){
            Collections.reverse(traducciones);
        }

        return traducciones;
    }

    @Override
    public void onListFragmentInteraction(Traducciones tr) {

        Intent mintent = new Intent(this, DetailsActivity.class);
        mintent.putExtra(INTENT_DETALLE_ID, tr.getIdTraduccion());
        startActivity(mintent);
    }
}
