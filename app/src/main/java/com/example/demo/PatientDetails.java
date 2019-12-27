package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.Model.MyDateFragment;
import com.example.demo.Model.PatientBookingDetails;
import com.example.demo.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PatientDetails extends AppCompatActivity {

    private EditText doctorNameEditText,patientNameEditText,ageEditText,genderEditText, phoneEditText, addressEditText;
    private Button confirmBkngBtn;
    private RadioButton radioButtonMorning,radioButtonNoon,radioButtonEvening;
    String slot = "";
    public static EditText dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

        radioButtonMorning =(RadioButton) findViewById(R.id.radio_morning);
        radioButtonNoon =(RadioButton) findViewById(R.id.radio_noon);
        radioButtonEvening =(RadioButton) findViewById(R.id.radio_evening);
        confirmBkngBtn = (Button) findViewById(R.id.confirm_bkng_btn);
        doctorNameEditText =(EditText) findViewById(R.id.register_doctor_name);
        patientNameEditText = (EditText) findViewById(R.id.patient_name);
        genderEditText = (EditText) findViewById(R.id.patient_gender);
        phoneEditText = (EditText) findViewById(R.id.patient_phone_number);
        addressEditText = (EditText) findViewById(R.id.patient_address);
        ageEditText = (EditText) findViewById(R.id.patient_age);


        dateText = (EditText) findViewById(R.id.appointment_date);

        confirmBkngBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check();
            }
        });



    }

    private void Check() {
        if (radioButtonMorning.isChecked()){
            slot = "Morning";
        }
        if (radioButtonNoon.isChecked()){
            slot = "Noon";
        }
        if (radioButtonEvening.isChecked()){
            slot = "Evening";
        }
        if (TextUtils.isEmpty(doctorNameEditText.getText().toString()))
        {
            Toast.makeText(this, "Please provide the appintro_search_doctor name you are registering for.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(patientNameEditText.getText().toString()))
        {
            Toast.makeText(this, "Please provide patient full name.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(ageEditText.getText().toString()))
        {
            Toast.makeText(this, "Please provide patient age.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(genderEditText.getText().toString()))
        {
            Toast.makeText(this, "Please provide your gender.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(phoneEditText.getText().toString()))
        {
            Toast.makeText(this, "Please provide your phone number.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(addressEditText.getText().toString()))
        {
            Toast.makeText(this, "Please provide your address.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(dateText.getText().toString()))
        {
            Toast.makeText(this, "Please pick your appointment date.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            ConfirmBooking();
        }
    }

    public void btn_pickDate(View view) {

        DialogFragment fragment = new MyDateFragment();
        fragment.show(getSupportFragmentManager(),"date picker");

    }

    public static void populateSetDateText(int year,int month,int day){

        dateText.setText(day+ "-" +month+ "-" +year);
    }

    private void ConfirmBooking() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Booking Under Process....");
        progressDialog.show();


        final String saveCurrentDate, saveCurrentTime;

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        String currentUserPhone = Prevalent.currentOnlineUser.getPhone();


        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
        saveCurrentTime = currentTime.format(calForDate.getTime());


        PatientBookingDetails bookingDetails = new PatientBookingDetails(

                currentUserPhone,
                saveCurrentDate,
                saveCurrentTime,
                slot,
                doctorNameEditText.getText().toString(),
                patientNameEditText.getText().toString(),
                genderEditText.getText().toString(),
                ageEditText.getText().toString(),
                phoneEditText.getText().toString(),
                addressEditText.getText().toString(),
                dateText.getText().toString()
        );

        String myCurrentDateTime = DateFormat.getDateTimeInstance()
                .format(Calendar.getInstance().getTime());


        FirebaseDatabase.getInstance().getReference("Booking")
                .child(myCurrentDateTime).setValue(bookingDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    Toast.makeText(PatientDetails.this, "your appointment has been booked successfully.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PatientDetails.this, PopActivity.class);
                    startActivity(intent);
                    progressDialog.dismiss();
                    finish();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(PatientDetails.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }


}

