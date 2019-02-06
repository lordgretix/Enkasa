package com.gp3.enkasa.Activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.gp3.enkasa.Models.Json.Models.Traducciones;
import com.gp3.enkasa.R;

import java.util.Calendar;

public class BookingActivity  extends AppCompatActivity {

    private TextView mUsername;
    private TextView mAloj_name;
    private TextView mDireccion;
    private EditText mNumReserva;
    private SeekBar mSeekBar;
    private Button mCheckIn;
    private Button mCheckOut;
    private int id;
    private Traducciones mTraducciones;

    Calendar c;
    DatePickerDialog dpd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        mUsername= findViewById(R.id.textUsername);
        mAloj_name=findViewById(R.id.textAlojamientoName);
        mDireccion=findViewById(R.id.textDireccion);

/*        mCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c= Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month=c.get(Calendar.MONTH);
                int year=c.get(Calendar.YEAR);
                dpd = new DatePickerDialog(BookingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        mCheckIn.setText(mDay + "/" + mMonth +"/" +mYear);
                    }
                },day,month,year);
                dpd.show();
            }
        });

        mCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c= Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month=c.get(Calendar.MONTH);
                int year=c.get(Calendar.YEAR);
                dpd = new DatePickerDialog(BookingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        mCheckOut.setText(mDay + "/" + mMonth +"/" +mYear);
                    }
                },day,month,year);
                dpd.show();
            }
        });
*/
        if (savedInstanceState != null){
            id = savedInstanceState.getInt(DetailsActivity.SAVE_DETAIL_ID);
            mTraducciones =  AlojamientosActivity.jsonData.getData().getTraduccionByID(id);

        }else {
            id = getIntent().getIntExtra(DetailsActivity.SAVE_DETAIL_ID,0);
            mTraducciones =  AlojamientosActivity.jsonData.getData().getTraduccionByID(id);
        }

    }

}
