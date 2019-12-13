package com.example.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.Model.PatientBookingDetails;

import java.util.List;

public class MyBookingAdapter extends RecyclerView.Adapter<BookingViewHolder> {


    private Context mContext;
    private List<PatientBookingDetails> myBookingList;

    public MyBookingAdapter(Context mContext, List<PatientBookingDetails> myBookingList) {
        this.mContext = mContext;
        this.myBookingList = myBookingList;
    }


    // Connect The Template RecycleRow Booking Data
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_row_bookingdata,viewGroup,false);

        return new BookingViewHolder(mView);
    }


    // Set the text from database through Model Class
    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int i) {

        holder.mDoctorName.setText(myBookingList.get(i).getRegistredDoctorName());
        holder.mPatientName.setText(myBookingList.get(i).getPatientName());
        holder.mSlot.setText(myBookingList.get(i).getSlot());
        holder.mAge.setText(myBookingList.get(i).getPatientAge());
        holder.mGender.setText(myBookingList.get(i).getPatientGender());
        holder.mPhoneNo.setText(myBookingList.get(i).getPatientPhoneNo());
        holder.mAddress.setText(myBookingList.get(i).getPatientAddress());

    }

    @Override
    public int getItemCount() {
        return myBookingList.size();
    }
}

class BookingViewHolder extends RecyclerView.ViewHolder{

    TextView mDoctorName,mPatientName,mSlot,mAge,mGender,mPhoneNo,mAddress;

    public BookingViewHolder(@NonNull View itemView) {
        super(itemView);
        mDoctorName = itemView.findViewById(R.id.tvDoctorName);
        mPatientName = itemView.findViewById(R.id.tvPatientName);
        mSlot = itemView.findViewById(R.id.tvSlot);
        mAge = itemView.findViewById(R.id.tvAge);
        mGender = itemView.findViewById(R.id.tvGender);
        mPhoneNo = itemView.findViewById(R.id.tvPhoneNo);
        mAddress = itemView.findViewById(R.id.tvAddress);
    }
}
