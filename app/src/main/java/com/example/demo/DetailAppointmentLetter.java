package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailAppointmentLetter extends AppCompatActivity {


    TextView mBookingDate,mBookingTime,mDoctorName,mPatientName,mSlot,mAge,mGender,mPhoneNo,mAddress,mAppointmentDate;
    String key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_appointment_letter);

        mBookingDate = (TextView)findViewById(R.id.booking_date);
        mBookingTime = (TextView)findViewById(R.id.booking_time);
        mDoctorName = (TextView)findViewById(R.id.tvDoctorName);
        mPatientName = (TextView)findViewById(R.id.tvPatientName);
        mSlot = (TextView)findViewById(R.id.tvSlot);
        mAge = (TextView)findViewById(R.id.tvAge);
        mGender = (TextView)findViewById(R.id.tvGender);
        mPhoneNo = (TextView)findViewById(R.id.tvPhoneNo);
        mAddress = (TextView)findViewById(R.id.tvAddress);
        mAppointmentDate = (TextView)findViewById(R.id.tvDate);

        Bundle mBundle = getIntent().getExtras();

        if(mBundle!=null){


            mBookingDate.setText("Booking Date: " + mBundle.getString("BookingDate"));
            mBookingTime.setText("Booking Time: " + mBundle.getString("BookingTime"));
            mPatientName.setText("Dear: " + mBundle.getString("PatientName"));
            mAge.setText("Age: " + mBundle.getString("PatientAge"));
            mGender.setText("Gender: " + mBundle.getString("PatientGender"));
            mAddress.setText("Address: " + mBundle.getString("PatientAddress"));
            mPhoneNo.setText("Phone No: " + mBundle.getString("PatientPhoneno"));
            mAppointmentDate.setText("Date: " + mBundle.getString("AppointmentDate"));
            mSlot.setText("Slot: " + mBundle.getString("AppointmentSlot"));
            mDoctorName.setText("With Dr. " + mBundle.getString("BookedDoctorName"));

            key = mBundle.getString("keyValue");

        }

        }

        // Deleting data from database

    public void btn_cancel_appointment(View view) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Cancelling appointment");
        progressDialog.setMessage("Please wait, while we are cancelling your appointment");
        progressDialog.setCanceledOnTouchOutside(false);

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Booking");
        reference.child(key).removeValue();
       // Toast.makeText(DetailAppointmentLetter.this,"Booking Cancelled Successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),AppointmentLetter.class));
        finish();

    }
}
