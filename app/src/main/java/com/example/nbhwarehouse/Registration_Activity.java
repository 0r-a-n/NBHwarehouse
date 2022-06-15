package com.example.nbhwarehouse;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private static final String TAG = "Registration_Activity";
    private EditText firstName, lastName, email, password, phoneNumber;
    private Button btnRegister;
    private TextView firstNameWarn, lastNameWarn, emailWarn, passWarn, phoneWarn;
    private Spinner citySpinner;
    private FirebaseAuth mAuth;
    private ConstraintLayout parent;
    private String fName, lName, Email, pwd, phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initViews();

        citySpinner =findViewById(R.id.SLocationSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cities, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        citySpinner.setAdapter(adapter);
        citySpinner.setOnItemSelectedListener(this);

        
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initRegister();
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "You selected: "+parent.getSelectedItem(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    
    private void initRegister(){
        Log.d(TAG, "initRegister: Started");
        fName = firstName.getText().toString();
        lName = lastName.getText().toString();
        Email = email.getText().toString();
        pwd = password.getText().toString();
        phone = phoneNumber.getText().toString();

        if (validateData()){
            mAuth.createUserWithEmailAndPassword(Email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        warningsDisappear();
                        //Toast.makeText(Registration_Activity.this,"user regestered...",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Registration_Activity.this, KitsHome_Activity.class);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(Registration_Activity.this,"عذراً، حدث خطأ ما",Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }
    }

    private void warningsDisappear(){
        Log.d(TAG, "showSnackBar: Started");
        firstNameWarn.setVisibility(View.GONE);
        lastNameWarn.setVisibility(View.GONE);
        emailWarn.setVisibility(View.GONE);
        passWarn.setVisibility(View.GONE);
        phoneWarn.setVisibility(View.GONE);
    }

    private boolean validateData(){
        Log.d(TAG, "validateData: Started");
        if(fName.equals("")){
            firstNameWarn.setVisibility(View.VISIBLE);
            firstNameWarn.setText("ادخل الاسم الأول");
            return false;
        }else if(lName.equals("")){
            lastNameWarn.setVisibility(View.VISIBLE);
            lastNameWarn.setText("ادخل الاسم الأخير");
            return false;
        }else if(Email.equals("")){
            emailWarn.setVisibility(View.VISIBLE);
            emailWarn.setText("ادخل البريد الإلكتروني");
            return false;
        }else if(pwd.equals("")){
            passWarn.setVisibility(View.VISIBLE);
            passWarn.setText("ادخل كلمة المرور");
            return false;
        }else if(phone.equals("")){
            phoneWarn.setVisibility(View.VISIBLE);
            phoneWarn.setText("ادخل رقم الهاتف");
            return false;
        }else return true;
    }
    
    private void initViews(){
        Log.d(TAG, "initViews: Started");

        //edit text
        firstName= findViewById(R.id.sellerFName);
        lastName= findViewById(R.id.sellerLName);
        email= findViewById(R.id.sellerEmail);
        password= findViewById(R.id.password);
        phoneNumber= findViewById(R.id.sellerPhone);

        btnRegister= findViewById(R.id.btnRegister);
        mAuth = FirebaseAuth.getInstance();

        //Text view for warning message
        firstNameWarn= findViewById(R.id.SFNameWarn);
        lastNameWarn=findViewById(R.id.SLNameWarn);
        emailWarn= findViewById(R.id.SEmailWarn);
        passWarn= findViewById(R.id.passWarn);
        phoneWarn= findViewById(R.id.SPhoneWarn);
        
        citySpinner= findViewById(R.id.SLocationSpinner);
        parent=findViewById(R.id.parent);
    }
}