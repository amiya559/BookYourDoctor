package com.example.demo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;

public class Upload_Doctors extends AppCompatActivity {

    ImageView doctorImage;
    Uri uri;
    EditText txt_name,txt_specialization,txt_gender,txt_locations,txt_days,txt_price,txt_description;
    String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload__doctors);

        doctorImage = (ImageView)findViewById(R.id.iv_doctorImage);
        txt_name = (EditText) findViewById(R.id.txt_doctor_name);
        txt_specialization = (EditText) findViewById(R.id.text_specialization);
        txt_gender = (EditText) findViewById(R.id.txt_doctor_gender);
        txt_locations = (EditText) findViewById(R.id.txt_doctor_location);
        txt_days = (EditText) findViewById(R.id.txt_doctor_days);
        txt_price = (EditText) findViewById(R.id.txt_doctor_price);
        txt_description = (EditText) findViewById(R.id.text_description);

    }

    public void btnSelectImage(View view) {

        Intent photoPicker = new Intent(Intent.ACTION_PICK);
        photoPicker.setType("image/*");
        startActivityForResult(photoPicker,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            uri = data.getData();
            doctorImage.setImageURI(uri);
        }

        else Toast.makeText(this, "You Have Not Picked Any Image", Toast.LENGTH_SHORT).show();

    }

    public void uploadImage(){

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("DoctorsImage")
                .child(uri.getLastPathSegment());

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Details Uploading....");
        progressDialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageUrl = urlImage.toString();
                uploadDoctors();

                progressDialog.dismiss();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
            }
        });


    }

    public void btnUploadDoctors(View view) {
        uploadImage();
    }

    public void uploadDoctors(){



        DoctorDetails doctorDetails = new DoctorDetails(

                txt_name.getText().toString(),
                txt_specialization.getText().toString(),
                txt_gender.getText().toString(),
                txt_locations.getText().toString(),
                txt_days.getText().toString(),
                txt_price.getText().toString(),
                txt_description.getText().toString(),
                imageUrl
        );

        String myCurrentDateTime = DateFormat.getDateTimeInstance()
                .format(Calendar.getInstance().getTime());


        FirebaseDatabase.getInstance().getReference("Doctors")
                .child(myCurrentDateTime).setValue(doctorDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    Toast.makeText(Upload_Doctors.this, "Doctor Details Upload Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Upload_Doctors.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
