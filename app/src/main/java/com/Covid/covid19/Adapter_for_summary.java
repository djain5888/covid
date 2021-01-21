package com.Covid.covid19;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.Covid.covid19.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class Adapter_for_summary extends RecyclerView.Adapter<Adapter_for_summary.ViewHolder> implements Filterable, GestureDetector.OnGestureListener {
    private  List<get_summary> listdata;
    private List<get_summary> list2;
    Context c1;
    // RecyclerView recyclerView;
    public Adapter_for_summary(List<get_summary> listdata, Context c1) {
        this.listdata = listdata;
        this.list2=listdata;
        this.c1=c1;
    }



    @Override
    public Adapter_for_summary.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.recycler_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull Adapter_for_summary.ViewHolder holder, final int position) {
        final List<get_summary> myListData = listdata;
        final String s=listdata.get(position).getCountry();
        Log.i("inmain", listdata.get(position).getCountry());
        holder.explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(view.getContext(), full_country_detail.class);
                i1.putExtra("country",s);
                view.getContext().startActivity(i1);

            }
        });
        holder.textView.setText( listdata.get(position).getCountry());
        holder.Confirmed.setText(Integer.toString( listdata.get(position).getTotalConfirmed()));
        holder.Death.setText(Integer.toString( listdata.get(position).getTotalDeaths()));
        holder.Recovered.setText(Integer.toString(listdata.get(position).getTotalRecovered()));
        holder.new_confirmed.setText("+"+Integer.toString(listdata.get(position).getNewConfirmed()));
        holder.new_deaths.setText(Integer.toString(listdata.get(position).getNewDeaths()));
        holder.new_recovered.setText("-"+Integer.toString(listdata.get(position).getNewRecovered()));
        holder.active.setText("Active: "+Integer.toString(listdata.get(position).getTotalConfirmed()-listdata.get(position).getTotalDeaths()-listdata.get(position).getTotalRecovered()));
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = inputFormat.parse(listdata.get(position).getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = outputFormat.format(date);
        holder.Date.setVisibility(View.GONE);
        // System.out.println(formattedDate);
//        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
//        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyy", Locale.ENGLISH);
//        LocalDate date = LocalDate.parse(listdata.get(position).getDate(), inputFormatter);
//        String formattedDate = outputFormatter.format(date);
      //  holder.Date.setText(formattedDate);
        Collections.sort(listdata);

       // notifyDataSetChanged();


    }




    @Override
    public int getItemCount() {
        return listdata.size();
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<get_summary> m1 = new ArrayList<>();
            Log.i("aotit0",list2.get(2).getCountry().toLowerCase());


            if(charSequence.toString().isEmpty())
            {
                Log.i("aotit0",list2.get(2).getCountry().toLowerCase());


           //     m1.clear();
                m1.addAll(list2);

            } else  {
               // m1.clear();
                Log.i("gotit0",list2.get(0).getCountry().toLowerCase());
                for (int i=0;i<listdata.size();i++)
                {
                    Log.i("gotit0",listdata.get(i).getCountry().toLowerCase());
                    if(listdata.get(i).getCountry().toLowerCase().contains(charSequence.toString().toLowerCase()))
                    {


                        Log.i("gotit0",listdata.get(i).getCountry().toLowerCase());
                        m1.add(listdata.get(i));
                    }
                    else
                    {
                        //m1.clear();

                       // m1.getCountries().add(list2.getCountries().get(2));
                    }
                }
            }
            FilterResults filterResults=new FilterResults();
            filterResults.values=m1;
            return filterResults;

        }


        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            Log.i("thefilter","hello");
          //  listdata.clear();
            listdata= (List<get_summary>) filterResults.values;
            notifyDataSetChanged();

        }
    };}

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        if(motionEvent.getX() >motionEvent1.getX()){
            Log.i("thevalue","heefef");
            Toast.makeText(c1,
                    "Swipe right - finish()",
                    Toast.LENGTH_SHORT).show();


        }

        return false;
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView,Confirmed,Death,Recovered,Date,active,new_confirmed,new_deaths,new_recovered;
        public Button explore;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            //this.imageView = (ImageView) itemView.findViewById(R.id.);
            this.new_confirmed=itemView.findViewById(R.id.new_Confirmed);
            this.new_recovered=itemView.findViewById(R.id.new_Recover);
            this.new_deaths=itemView.findViewById(R.id.new_Deaths);
            this.Confirmed=itemView.findViewById(R.id.confirmed_cases);
            this.active=itemView.findViewById(R.id.active);
            this.Recovered=itemView.findViewById(R.id.Recover_cases);
            this.Death=itemView.findViewById(R.id.Death_cases);
            this.explore=itemView.findViewById(R.id.explore);
            this.textView = (TextView) itemView.findViewById(R.id.title);
            this.Date=itemView.findViewById(R.id.date);
            //relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}

