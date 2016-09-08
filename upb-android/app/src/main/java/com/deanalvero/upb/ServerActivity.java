package com.deanalvero.upb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class ServerActivity extends AppCompatActivity {

    private EditText mEditTextIP, mEditTextPort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);
        setupOnCreate();
    }

    private void setupOnCreate() {
        mEditTextIP = (EditText) findViewById(R.id.editText_ip);
        mEditTextPort = (EditText) findViewById(R.id.editText_port);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(LoginActivity.newIntent(ServerActivity.this,
                        mEditTextIP.getText().toString(),
                        mEditTextPort.getText().toString()));
            }
        });
    }
}
