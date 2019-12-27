package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.demo.Model.PatientBookingDetails;
import com.example.demo.Model.Users;
import com.example.demo.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AppointmentLetter extends AppCompatActivity {

    RecyclerView mRecyclerview;
    List<PatientBookingDetails> myBookingList;

    ProgressDialog progressDialog;


    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;

    MyBookingAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_letter);



        mRecyclerview = (RecyclerView) findViewById(R.id.recycleView);

        // Set the lay out
        GridLayoutManager gridLayoutManager = new GridLayoutManager(AppointmentLetter.this,1);
        mRecyclerview.setLayoutManager(gridLayoutManager);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Booked List....");

        myBookingList = new ArrayList<>();


        myAdapter = new MyBookingAdapter(AppointmentLetter.this, myBookingList);
        mRecyclerview.setAdapter(myAdapter);



        /*

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        progressDialog.show();


        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(parentDbName).child(Prevalent.currentOnlineUser.getPhone()).exists()) {
                    // Users usersData = dataSnapshot.child(parentDbName).child(phone).getValue(Users.class);

                    myBookingList.clear();

                    for (DataSnapshot itemSnapShot: dataSnapshot.getChildren()){

                        PatientBookingDetails patientBookingDetails = itemSnapShot.getValue(PatientBookingDetails.class);

                        myBookingList.add(patientBookingDetails);


                    }

                    myAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();

                } else {
                    Toast.makeText(AppointmentLetter.this, "You have not booked yet..Let Book a appintro_search_doctor.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AppointmentLetter.this, NavigationDrawerActivity.class);
                    startActivity(intent);
                    progressDialog.dismiss();
                }
            }

         */

        databaseReference = FirebaseDatabase.getInstance().getReference("Booking");
        progressDialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // if (dataSnapshot.child(parentDbName).child(Prevalent.currentOnlineUser.getPhone()).exists()) {
                //PatientBookingDetails patientBookingDetails = dataSnapshot.getValue(PatientBookingDetails.class);

               // if (patientBookingDetails.getCurrentOnlineUser().equals(Prevalent.currentOnlineUser.getPhone())) {

                    myBookingList.clear();

                    for (DataSnapshot itemSnapShot : dataSnapshot.getChildren()) {

                        PatientBookingDetails patientBookingDetails = itemSnapShot.getValue(PatientBookingDetails.class);

                        patientBookingDetails.setKey(itemSnapShot.getKey());

                        if (patientBookingDetails.getCurrentOnlineUser().equals(Prevalent.currentOnlineUser.getPhone())) {

                            myBookingList.add(patientBookingDetails);

                        } else {
                            Toast.makeText(AppointmentLetter.this, "Your Booked Doctors Shown Here.", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }

                    }
                    myAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

    }


}
