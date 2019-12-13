package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CreateDoctorProfile extends AppCompatActivity {

    RecyclerView mRecyclerview;
    List<DoctorDetails> myDoctorList;
   // DoctorDetails mDoctorDetails;

    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;


    ProgressDialog progressDialog;
    MyAdapter myAdapter;

    EditText txt_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_doctor_profile);

        mRecyclerview = (RecyclerView) findViewById(R.id.recycleView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(CreateDoctorProfile.this,1);
        mRecyclerview.setLayoutManager(gridLayoutManager);

        // Searching
        txt_search = (EditText)findViewById(R.id.txt_searchtxt);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading List....");

        myDoctorList = new ArrayList<>();



        myAdapter = new MyAdapter(CreateDoctorProfile.this,myDoctorList);
        mRecyclerview.setAdapter(myAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Doctors");

        progressDialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                myDoctorList.clear();

                for (DataSnapshot itemSnapShot: dataSnapshot.getChildren()){

                    DoctorDetails doctorDetails = itemSnapShot.getValue(DoctorDetails.class);

                    myDoctorList.add(doctorDetails);


                }

                myAdapter.notifyDataSetChanged();
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                progressDialog.dismiss();
            }
        });


        txt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                    filter(s.toString());

            }
        });


    }

    private void filter(String text) {

        ArrayList<DoctorDetails> filterList = new ArrayList<>();

        for (DoctorDetails item: myDoctorList){
            if (item.getDescription().toLowerCase().contains(text.toLowerCase())){
                filterList.add(item);
            }
            else if (item.getSpecialization().toLowerCase().contains(text.toLowerCase())){
                filterList.add(item);
            }


        }
        myAdapter.filteredList(filterList);
    }

    //public void btn_upload_activity(View view) {

      //  startActivity(new Intent(this,NavigationDrawerActivity.class));
    //}
}
