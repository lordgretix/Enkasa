package com.gp3.enkasa.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.gp3.enkasa.Fragments.AlojamientoFragment;
import com.gp3.enkasa.Fragments.DatePickerFragment;
import com.gp3.enkasa.MainActivity;
import com.gp3.enkasa.Models.Json.JsonData;
import com.gp3.enkasa.Models.Json.Models.Traducciones;
import com.gp3.enkasa.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class AlojamientosActivity extends AppCompatActivity implements AlojamientoFragment.OnListFragmentInteractionListener {

    private static int PERFIL_REQUEST = 1;
    public static JsonData jsonData = null;
    public static HashMap<String, ArrayList<String>> provs;
    public static final String INTENT_DETALLE_ID = AlojamientosActivity.class.getName()+".INTENT_DETALLE_ID";

   // private TextView mTextMessage;
    private BottomNavigationView mBottomNavigationView;

    private TextView mLblEmpty;

    //Filters
    private EditText mTxtBuscar;
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
                case R.id.lugares_navigation_map:

                    Intent intent = new Intent (getApplicationContext(),MapsGlobalActivity.class);
                    startActivity(intent);

                    break;
                case R.id.lugares_navigation_reservas:

                    Intent intent2 = new Intent(getApplicationContext(), MisReservasActivity.class);
                    startActivity(intent2);

                    break;
                case R.id.lugares_navigation_profile:

                    Intent intent3 = new Intent(getApplicationContext(),PerfilActivity.class);
                    startActivityForResult(intent3, PERFIL_REQUEST);

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

        mLblEmpty = findViewById(R.id.lblEmpty);

        //Filters
        mTxtBuscar = findViewById(R.id.txtBuscar);
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

        mFilterOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        provs = jsonData.getData().poblacionesByProvincias();

        // TerritorioSelect
        ArrayAdapter<String> provinciaAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1);
        provinciaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSelectTerritorio.setAdapter(provinciaAdapter);

        provinciaAdapter.add(getResources().getString(R.string.filter_select_todo_option));

        for(String provincia : provs.keySet()){
            provinciaAdapter.add(provincia);
        }
        mSelectTerritorio.setSelection(0);
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

        mTxtBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateFragmentUI();
            }
        });

        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentManager manager = getSupportFragmentManager();

        Fragment fragment = manager.findFragmentById(R.id.fragment_alojamiento_list);

        if (fragment == null) {
            FragmentTransaction transaction = manager.beginTransaction();
            fragment = AlojamientoFragment.newInstance(1);

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

        ArrayList<String> tipo = new ArrayList<>();

        if(mSwitchAlbergues.isChecked()) tipo.add(tipos[0]);
        if(mSwitchCamping.isChecked()) tipo.add(tipos[1]);
        if(mSwitchAgroturismo.isChecked()) tipo.add(tipos[2]);
        if(mSwitchRural.isChecked()) tipo.add(tipos[3]);

        if(mSelectTerritorio.getSelectedItem()==null) mSelectTerritorio.setSelection(0);
        if(mSelectMunicipio.getSelectedItem()==null) mSelectMunicipio.setSelection(0);

        for (Traducciones tr : jsonData.getData().getTraducciones(MainActivity.getCurrentLang())) {

            if(!tr.getNombre().toLowerCase().contains(mTxtBuscar.getText().toString().toLowerCase()) && !tr.getDescripcion().toLowerCase().contains(mTxtBuscar.getText().toString().toLowerCase())) continue;

            if(mSelectTerritorio.getSelectedItemPosition()!=0 && !mSelectTerritorio.getSelectedItem().toString().equals(jsonData.getData().getProvinciaByIDs(tr.getCodPostal(), tr.getCodPoblacion()))) continue;

            if(mSelectMunicipio.getSelectedItemPosition()!=0 && !mSelectMunicipio.getSelectedItem().toString().equals(jsonData.getData().getPoblacionByIDs(tr.getCodPostal(), tr.getCodPoblacion()))) continue;

            if(!tipo.isEmpty() && !tipo.contains(tr.getTipo())) continue;

            if(mSwitchCertificado.isChecked() && tr.getCertificado()!=1) continue;

            if(mSwitchRestaurante.isChecked() && tr.getRestaurante()!=1) continue;

            if(mSwitchTienda.isChecked() && tr.getTienda()!=1) continue;

            if(mSwitchClub.isChecked() && tr.getClub()!=1) continue;

            if(mSwitchAutocaravana.isChecked() && tr.getAutocarabana()!=1) continue;

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

        if(traducciones.size()==0){
            mLblEmpty.setVisibility(View.VISIBLE);
        }else{
            mLblEmpty.setVisibility(View.GONE);
        }

        return traducciones;
    }

    @Override
    public void onListFragmentInteraction(Traducciones tr) {

        Intent mintent = new Intent(this, DetailsActivity.class);
        mintent.putExtra(INTENT_DETALLE_ID, tr.getIdTraduccion());
        startActivity(mintent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode== PERFIL_REQUEST){
            switch (resultCode){
                case PerfilActivity.LANG_ES:
                    MainActivity.setLocale(this, "es");
                    break;
                case PerfilActivity.LANG_EUS:
                    MainActivity.setLocale(this, "eu");
                    break;
                case PerfilActivity.LOGED_OUT:
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }

        recreate();
    }
}
