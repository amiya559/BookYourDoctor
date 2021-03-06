package com.example.demo;

import android.content.Context;
import android.content.Intent;
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
    public void onBindViewHolder(@NonNull final BookingViewHolder holder, final int i) {

       holder.mBookingDate.setText("Booking Date: " + myBookingList.get(i).getBookingDate());
        // holder.mBookingTime.setText("Booking Time: " + myBookingList.get(i).getBookingTime());
        holder.mDoctorName.setText("With Dr. " + myBookingList.get(i).getRegistredDoctorName());
        holder.mPatientName.setText("Dear: " + myBookingList.get(i).getPatientName());
        holder.mSlot.setText("Slot: " + myBookingList.get(i).getSlot());
        //holder.mAge.setText("Age: " + myBookingList.get(i).getPatientAge());
        //holder.mGender.setText("Gender: " + myBookingList.get(i).getPatientGender());
        //holder.mPhoneNo.setText("Phone No: " + myBookingList.get(i).getPatientPhoneNo());
        //holder.mAddress.setText("Address: " + myBookingList.get(i).getPatientAddress());
        holder.mAppointmentDate.setText("Date: " + myBookingList.get(i).getAppointmentDate());




        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,DetailAppointmentLetter.class);
                intent.putExtra("BookingDate",myBookingList.get(holder.getAdapterPosition()).getBookingDate());
                intent.putExtra("BookingTime",myBookingList.get(holder.getAdapterPosition()).getBookingTime());
                intent.putExtra("PatientName",myBookingList.get(holder.getAdapterPosition()).getPatientName());
                intent.putExtra("PatientAge",myBookingList.get(holder.getAdapterPosition()).getPatientAge());
                intent.putExtra("PatientGender",myBookingList.get(holder.getAdapterPosition()).getPatientGender());
                intent.putExtra("PatientAddress",myBookingList.get(holder.getAdapterPosition()).getPatientAddress());
                intent.putExtra("PatientPhoneno",myBookingList.get(holder.getAdapterPosition()).getPatientPhoneNo());
                intent.putExtra("AppointmentDate",myBookingList.get(holder.getAdapterPosition()).getAppointmentDate());
                intent.putExtra("AppointmentSlot",myBookingList.get(holder.getAdapterPosition()).getSlot());
                intent.putExtra("BookedDoctorName",myBookingList.get(holder.getAdapterPosition()).getRegistredDoctorName());
                intent.putExtra("keyValue",myBookingList.get(holder.getAdapterPosition()).getKey());
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return myBookingList.size();
    }
}

class BookingViewHolder extends RecyclerView.ViewHolder{

    TextView mBookingDate,mBookingTime,mDoctorName,mPatientName,mSlot,mAge,mGender,mPhoneNo,mAddress,mAppointmentDate;
    CardView mCardView;


    public BookingViewHolder(@NonNull View itemView) {
        super(itemView);
        mBookingDate = itemView.findViewById(R.id.booking_date);
        //mBookingTime = itemView.findViewById(R.id.booking_time);
        mDoctorName = itemView.findViewById(R.id.tvDoctorName);
        mPatientName = itemView.findViewById(R.id.tvPatientName);
        mSlot = itemView.findViewById(R.id.tvSlot);
        //mAge = itemView.findViewById(R.id.tvAge);
        //mGender = itemView.findViewById(R.id.tvGender);
        //mPhoneNo = itemView.findViewById(R.id.tvPhoneNo);
        //mAddress = itemView.findViewById(R.id.tvAddress);
        mAppointmentDate = itemView.findViewById(R.id.tvDate);
        mCardView = itemView.findViewById(R.id.myCardView);

    }
}
