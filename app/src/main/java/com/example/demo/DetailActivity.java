package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    TextView doctorName,doctorDescription,doctorLocation,doctorSpecialization,doctorPrice,doctorDays;
    ImageView doctorimage;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        doctorName = (TextView) findViewById(R.id.txtDoctorName);
        doctorSpecialization = (TextView) findViewById(R.id.txtSpecialization);
        doctorPrice = (TextView) findViewById(R.id.txtPrice);
        doctorLocation = (TextView) findViewById(R.id.txtLocation);
        doctorDays = (TextView) findViewById(R.id.txtDays);
        doctorDescription = (TextView) findViewById(R.id.txtDescription);
        doctorimage = (ImageView) findViewById(R.id.ivImage);

        Bundle mBundle = getIntent().getExtras();

        if(mBundle!=null){

            doctorName.setText(mBundle.getString("Title"));
            doctorSpecialization.setText(mBundle.getString("Specialization"));
            doctorPrice.setText(mBundle.getString("Price"));
            doctorLocation.setText(mBundle.getString("Description"));
            doctorDays.setText(mBundle.getString("Days"));
            doctorDescription.setText(mBundle.getString("Location"));
            //doctorimage.setImageResource(mBundle.getInt("Image"));

            Glide.with(this)
                    .load(mBundle.getString("Image"))
            .into(doctorimage);
        }

    }


    public void bookNowBtn(View view) {
        Intent intent=new Intent(DetailActivity.this,PatientDetails.class);
        startActivity(intent);
    }




}
