package com.gp3.enkasa.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.gp3.enkasa.Fragments.MisReservasFragment;
import com.gp3.enkasa.MainActivity;
import com.gp3.enkasa.Models.Json.Connection;
import com.gp3.enkasa.Models.Json.Exceptions.JsonDataException;
import com.gp3.enkasa.Models.Json.Models.Reservas;
import com.gp3.enkasa.R;

import java.io.IOException;

public class MisReservasActivity extends AppCompatActivity implements MisReservasFragment.OnListFragmentInteractionListener {

    private static final int UPDATE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_reservas);

        FragmentManager manager = getSupportFragmentManager();

        Fragment fragment = manager.findFragmentById(R.id.fragment_mis_reservas_list);

        if (fragment == null) {
            FragmentTransaction transaction = manager.beginTransaction();
            fragment = MisReservasFragment.newInstance(1);

            transaction.add(R.id.fragment_mis_reservas_list, fragment);
            transaction.commit();
        }
    }

    @Override
    public void onListFragmentInteraction(Reservas rs) {

        int id = AlojamientosActivity.jsonData.getData().getTraduccionByAlojaminetoID(rs.getAlojamiento(), MainActivity.getCurrentLang()).getId();
        Intent intent = ReservasActivity.newIntent(this, id, rs.getId());

        startActivityForResult(intent, UPDATE);

    }

    @Override
    public void onListFragmentDeleteIconClick(final Reservas rs) {

        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage(R.string.reserva_delete_confirm)
                .setPositiveButton(R.string.confirm_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlojamientosActivity.jsonData.getData().getReservas().remove(rs);
                        String params = "json={" +
                                "'db':'reto_gp3'," +
                                "'user':'gp3'," +
                                "'password':'IFZWx5dEG12yt8QW'," +
                                "'tables':{" +
                                "'reservas':{" +
                                "'action':'delete'," +
                                "'where':[" +
                                "{" +
                                "'field':'id'," +
                                "'value':" + rs.getId() +
                                "}" +
                                "]" +
                                "}" +
                                "}" +
                                "}";
                        params = params.replaceAll("'", "\"");
                        try {
                            Connection.pushData(params);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JsonDataException e) {
                            e.printStackTrace();
                            System.out.println(e.getMessage());
                        }
                        updateFragmentUI();
                    }

                })
                .setNegativeButton(R.string.confirm_no, null)
                .show();
    }

    public void updateFragmentUI() {

        FragmentManager manager = getSupportFragmentManager();

        MisReservasFragment fragment = (MisReservasFragment) manager.findFragmentById(R.id.fragment_mis_reservas_list);

        fragment.updateUI(AlojamientosActivity.jsonData.getData().getReservasByUserID(AlojamientosActivity.jsonData.getUser().getID()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == UPDATE) {
            if (resultCode == ReservasActivity.UPDATED) {
                recreate();
            }
        }
    }
}
