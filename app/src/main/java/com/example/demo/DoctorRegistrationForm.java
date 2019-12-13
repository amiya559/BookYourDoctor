package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorRegistrationForm extends AppCompatActivity {

    EditText signupEmail,signupPswrd,confirmPswrd;
    Button signupBtn;
    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_registration_form);

        signupEmail = (EditText) findViewById(R.id.sign_up_email);
        signupPswrd = (EditText) findViewById(R.id.sign_up_pswrd);
        confirmPswrd = (EditText) findViewById(R.id.sign_up_confirmpswrd);


        signupBtn = (Button) findViewById(R.id.registerbutton);
        progressBar = (ProgressBar) findViewById(R.id.registerProgressbar);

        firebaseAuth = FirebaseAuth.getInstance();

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringEmail = signupEmail.getText().toString().trim();
                String stringPswrd = signupPswrd.getText().toString().trim();
                String stringCnfPswrd = confirmPswrd.getText().toString().trim();

                if (stringEmail.isEmpty() ||  stringPswrd.isEmpty() || stringCnfPswrd.isEmpty()) {
                    Toast.makeText(DoctorRegistrationForm.this, "Fields can't be blank", Toast.LENGTH_LONG).show();
                }

                progressBar.setVisibility(View.VISIBLE);


                if(stringPswrd.length()<6){
                    Toast.makeText(DoctorRegistrationForm.this, "Password Too Short", Toast.LENGTH_SHORT).show();
                }

                progressBar.setVisibility(View.VISIBLE);


                if(stringPswrd.equals(stringCnfPswrd)){

                    firebaseAuth.createUserWithEmailAndPassword(stringEmail, stringPswrd)
                            .addOnCompleteListener(DoctorRegistrationForm.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    progressBar.setVisibility(View.GONE);

                                    if (task.isSuccessful()) {

                                        Toast.makeText(DoctorRegistrationForm.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),Upload_Doctors.class));

                                    } else {

                                        Toast.makeText(DoctorRegistrationForm.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });



                }
                else{
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(DoctorRegistrationForm.this, "Password Mismatch", Toast.LENGTH_SHORT).show();
                }


            }

        });


    }

}
