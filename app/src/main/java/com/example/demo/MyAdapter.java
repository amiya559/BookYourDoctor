package com.example.demo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<DoctorViewHolder>{

    private Context mContext;
    private List<DoctorDetails> myDoctorList;



    // Animation
    private int lastPosition = -1;



    public MyAdapter(Context mContext, List<DoctorDetails> myDoctorList) {
        this.mContext = mContext;
        this.myDoctorList = myDoctorList;
    }

    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_row_data,viewGroup,false);


        return new DoctorViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final DoctorViewHolder doctorViewHolder, int i) {

        Glide.with(mContext)
                .load(myDoctorList.get(i).getDoctorImage())
                .into(doctorViewHolder.imageView);


      //  doctorViewHolder.imageView.setImageResource(myDoctorList.get(i).getDoctorImage());
        doctorViewHolder.mTitle.setText(myDoctorList.get(i).getDoctorName());
        doctorViewHolder.mSpecialization.setText(myDoctorList.get(i).getSpecialization());
        doctorViewHolder.mGender.setText(myDoctorList.get(i).getGender());
        doctorViewHolder.mLocations.setText(myDoctorList.get(i).getLocations());
        doctorViewHolder.mdays.setText(myDoctorList.get(i).getDays());
        doctorViewHolder.mPrice.setText(myDoctorList.get(i).getPrice());
        doctorViewHolder.mdescription.setText(myDoctorList.get(i).getDescription());


        // Display the detail of appintro_search_doctor by clicking on card
        doctorViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,DetailActivity.class);

                intent.putExtra("Image",myDoctorList.get(doctorViewHolder.getAdapterPosition()).getDoctorImage());
                intent.putExtra("Title",myDoctorList.get(doctorViewHolder.getAdapterPosition()).getDoctorName());
                intent.putExtra("Specialization",myDoctorList.get(doctorViewHolder.getAdapterPosition()).getSpecialization());
                intent.putExtra("Price",myDoctorList.get(doctorViewHolder.getAdapterPosition()).getPrice());
                intent.putExtra("Description",myDoctorList.get(doctorViewHolder.getAdapterPosition()).getDescription());
                intent.putExtra("Days",myDoctorList.get(doctorViewHolder.getAdapterPosition()).getDays());
                intent.putExtra("Location",myDoctorList.get(doctorViewHolder.getAdapterPosition()).getLocations());
                mContext.startActivity(intent);


            }
        });

        setAnimation(doctorViewHolder.itemView,i);
    }


    public void setAnimation(View viewToAnimate, int position){

        if (position > lastPosition){

            ScaleAnimation animation = new ScaleAnimation(0.0f,1.0f,0.0f,1.0f,
                    Animation.RELATIVE_TO_SELF,0.5f,
                    Animation.RELATIVE_TO_SELF,0.5f);

            animation.setDuration(1500);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;

        }


    }

    @Override
    public int getItemCount() {
        return myDoctorList.size();
    }

    public void filteredList(ArrayList<DoctorDetails> filterList) {

        myDoctorList = filterList;
        notifyDataSetChanged();

    }
}

class DoctorViewHolder extends RecyclerView.ViewHolder{


    ImageView imageView;
    TextView mTitle,mSpecialization,mGender,mLocations,mdays,mPrice,mdescription;
    CardView mCardView;

    public DoctorViewHolder( View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.ivImage);
        mTitle = itemView.findViewById(R.id.tvTitle);
        mSpecialization = itemView.findViewById(R.id.tvSpecialization);
        mGender = itemView.findViewById(R.id.tvGender);
        mLocations = itemView.findViewById(R.id.tvLocation);
        mdays = itemView.findViewById(R.id.tvDays);
        mPrice = itemView.findViewById(R.id.tvPrice);
        mdescription = itemView.findViewById(R.id.tvDescription);
        mCardView = itemView.findViewById(R.id.myCardView);

    }
}
