package com.gp3.enkasa.Activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gp3.enkasa.Fragments.DatePickerFragment;
import com.gp3.enkasa.Models.Json.Connection;
import com.gp3.enkasa.Models.Json.Exceptions.JsonDataException;
import com.gp3.enkasa.Models.Json.Models.Data;
import com.gp3.enkasa.Models.Json.Models.Reservas;
import com.gp3.enkasa.Models.Json.Models.Traducciones;
import com.gp3.enkasa.Models.Json.Models.User;
import com.gp3.enkasa.R;

import java.io.IOException;
import java.util.ArrayList;


public class ReservasActivity extends AppCompatActivity {

    private static final String EXTRA_ID = ReservasActivity.class.getName()+".EXTRA_ID";

    private Traducciones mTraduccion;

    private ImageView mImgIcon;
    private TextView mTxtTitle;
    private TextView mTxtResume;
    private EditText mTxtDateStart;
    private EditText mTxtDateEnd;
    private EditText mTxtAmount;
    private EditText mTxtMessage;
    private Button mBtnReserve;

    private Reservas mReserva;

    public static Intent newIntent(Context context, int id) {
        Intent intent = new Intent(context, ReservasActivity.class);
        intent.putExtra(EXTRA_ID, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);

        mTraduccion = AlojamientosActivity.jsonData.getData().getTraduccionByID(getIntent().getIntExtra(EXTRA_ID, 0));

        mReserva = new Reservas();

        mReserva.setAlojamiento(mTraduccion.getIdAlojamiento());
        mReserva.setUsuario(AlojamientosActivity.jsonData.getUser().getID());

        mImgIcon = findViewById(R.id.imgReservasIcon);
        mTxtTitle = findViewById(R.id.txtReservasTitle);
        mTxtResume = findViewById(R.id.txtReservasResume);
        mTxtDateStart = findViewById(R.id.txtReservasDateStart);
        mTxtDateEnd = findViewById(R.id.txtReservasDateEnd);
        mTxtAmount = findViewById(R.id.txtReservasAmount);
        mTxtMessage = findViewById(R.id.txtReservasMessage);
        mBtnReserve = findViewById(R.id.btnReservasReserve);

        mImgIcon.setImageDrawable(getResources().getDrawable(Data.getAlojamientoIcon(this, mTraduccion.getTipo())));
        mTxtTitle.setText(mTraduccion.getNombre());
        mTxtResume.setText(mTraduccion.getResumen());

        mTxtDateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String date = twoDigits(day) + "-" + twoDigits(month+1) + "-" + year;
                        mTxtDateStart.setText(date);
                        mTxtDateStart.setError(null);
                        mReserva.setFechaInicio(year + "-" + twoDigits(month+1) + "-" + twoDigits(day));
                    }

                    private String twoDigits(int n) {
                        return (n<=9) ? ("0"+n) : String.valueOf(n);
                    }
                });
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        mTxtDateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String date = twoDigits(day) + "-" + twoDigits(month+1) + "-" + year;
                        mTxtDateEnd.setText(date);
                        mTxtDateEnd.setError(null);
                        mReserva.setFechaFin(year + "-" + twoDigits(month+1) + "-" + twoDigits(day));
                    }

                    private String twoDigits(int n) {
                        return (n<=9) ? ("0"+n) : String.valueOf(n);
                    }
                });
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        mBtnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validate()) return;

                mReserva.setNumReservas(Integer.parseInt(mTxtAmount.getText().toString()));
                mReserva.setMensaje(mTxtMessage.getText().toString());


                try{
                    String params = "json={" +
                                "'db':'reto_gp3'," +
                                "'user':'gp3'," +
                                "'password':'IFZWx5dEG12yt8QW'," +
                                "'tables':{" +
                                    "'reservas':{" +
                                        "'action':'insert'," +
                                        "'values':[" +
                                            mReserva.toJson() +
                                        "]" +
                                    "}" +
                                "}" +
                            "}";
                    params=params.replaceAll("'", "\"");

                    Connection.pushData(params);

                    User user = AlojamientosActivity.jsonData.getUser();

                    params =  "db=reto_gp3&users_table=usuarios&username_field=usuario&password_field=password&username="+user.getUsername()+"&password="+user.getPassword()+"&data_table[]=reservas&get_user=true";

                    ArrayList<Reservas> reservas = Connection.retriveData(params).getData().getReservas();

                    AlojamientosActivity.jsonData.getData().setReservas(reservas);

                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.reservado), Toast.LENGTH_LONG).show();

                    finish();

                } catch (JsonDataException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private boolean validate(){

        if(mTxtDateStart.getText().toString().isEmpty()){
            mTxtDateStart.setError(getResources().getString(R.string.error_fechaini_miss));
            return false;
        }

        if(mTxtDateEnd.getText().toString().isEmpty()){
            mTxtDateEnd.setError(getResources().getString(R.string.error_fechafin_miss));
            return false;
        }

        if(mTxtAmount.getText().toString().isEmpty() || Integer.parseInt(mTxtAmount.getText().toString())<=0){
            mTxtAmount.setError(getResources().getString(R.string.error_as_zero));
            return false;
        }

        return true;
    }


}
