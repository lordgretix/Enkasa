package com.gp3.enkasa;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {

    static final int REGISTER_CANCEL = 0;
    static final int REGISTER_SUCCESS = 1;

    private int result=REGISTER_CANCEL;

    private Button mBtnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registrarse);

        mBtnRegister = (Button) findViewById(R.id.register_button);

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = REGISTER_SUCCESS;
                finish();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        setResult(result);
    }
}
