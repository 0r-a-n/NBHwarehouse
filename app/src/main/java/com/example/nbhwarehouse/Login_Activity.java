package com.example.nbhwarehouse;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_Activity extends AppCompatActivity {

    private static final String TAG = "Login_Activity";
    private EditText email, password;
    private Button btnLogin, btnGoRegister;
    private TextView emailWarn, passWarn;
    private ConstraintLayout parent;
    private FirebaseAuth mAuth;
    private String Email, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initLogin();
            }
        });

        btnGoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login_Activity.this, Registration_Activity.class);
                startActivity(i);
            }
        });


    }

    private void initLogin() {
        Log.d(TAG, "initLogin: Started");
        Email = email.getText().toString();
        pwd = password.getText().toString();

        if (validateData()) {
            mAuth.signInWithEmailAndPassword(Email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        Intent i = new Intent(Login_Activity.this, KitsHome_Activity.class);
                        startActivity(i);
                        finish();
                        //showSnackBar();
                    } else {
                        Toast.makeText(Login_Activity.this, "فشلت عملية تسجيل الدخول", Toast.LENGTH_SHORT).show();
                    }
                }
            });// End authentication method
        }
    }

    private boolean validateData() {
        Log.d(TAG, "validateData: Started");

        if (Email.equals("")) {
            emailWarn.setVisibility(View.VISIBLE);
            emailWarn.setText("ادخل البريد الإلكتروني");
            return false;
        } else if (pwd.equals("")) {
            passWarn.setVisibility(View.VISIBLE);
            passWarn.setText("ادخل كلمة المرور");
            return false;
        } else return true;
    }

    private void initViews() {
        Log.d(TAG, "initViews: Started");

        //edit text
        email = findViewById(R.id.sellerEmail);
        password = findViewById(R.id.password);

        btnLogin = findViewById(R.id.btnRegister);
        btnGoRegister = findViewById(R.id.btnGoRegesier);

        //Text view for warning message
        emailWarn = findViewById(R.id.SEmailWarn);
        passWarn = findViewById(R.id.passWarn);

        mAuth = FirebaseAuth.getInstance();
        parent = findViewById(R.id.parent);
    }
}