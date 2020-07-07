package com.Covid.covid19;


import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.Covid.covid19.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
    ArrayList<Model> listdata;
    ArrayList<Model> list2;
   // private ItemFilter mFilter = new ItemFilter();
    public ArrayList<Model>filteredData ;
    // RecyclerView recyclerView;
    public MyListAdapter(ArrayList<Model>listdata) {
        this.listdata = listdata;
        this.list2=listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.recyle_country_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Model myListData = listdata.get(position);
        Log.i("inmain", listdata.get(position).getCountry());
       // holder.heading.setText(listdata.get(position).getCountry());
        holder.Confirmed.setText( Integer.toString(listdata.get(position).getConfirmed()));
        holder.Active.setText(Integer.toString(listdata.get(position).getActive()));
        holder.Death.setText(Integer.toString(listdata.get(position).getDeaths()));
        holder.Recovered.setText(Integer.toString(listdata.get(position).getRecovered()));
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = inputFormat.parse(listdata.get(position).getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = outputFormat.format(date);
       // System.out.println(formattedDate);
//        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
//        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyy", Locale.ENGLISH);
//        LocalDate date = LocalDate.parse(listdata.get(position).getDate(), inputFormatter);
//        String formattedDate = outputFormatter.format(date);
       holder.date.setText(formattedDate);


        // holder.imageView.setImageResource(listdata[position].getImgId());
//        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"click on item: "+myListData.getDescription(),Toast.LENGTH_LONG).show();
//            }
//        });

    }
    public void update()
    {
        listdata.notify();
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

//    @Override
//    public Filter getFilter() {
//        return filter;
//    }
//    Filter filter=new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence charSequence) {
//            ArrayList<Model> m1= new ArrayList<>();
//            if(charSequence.toString().isEmpty())
//            {
//                m1.addAll(list2);
//
//            } else  {
//                for (Model movie:list2)
//                {
//                    if(movie.getCountry().toLowerCase().contains(charSequence.toString().toLowerCase()))
//                    {
//                        m1.add(movie);
//                    }
//                }
//            }
//            FilterResults filterResults=new FilterResults();
//            filterResults.values=m1;
//            return filterResults;
//
//        }
//
//        @Override
//        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//            listdata.clear();
//            listdata.addAll((Collection<? extends Model>) filterResults.values);
//            notifyDataSetChanged();
//
//        }
//    };

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView,Confirmed,Death,Active,Recovered,date,heading;

        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);

            //this.imageView = (ImageView) itemView.findViewById(R.id.);
            this.date=itemView.findViewById(R.id.date);
            this.Confirmed=itemView.findViewById(R.id.confirmed_cases);
            this.Recovered=itemView.findViewById(R.id.Recover_cases);
            this.Death=itemView.findViewById(R.id.Death_cases);
            this.Active=itemView.findViewById(R.id.active_cases);
//            this.heading=itemView.findViewById(R.id.country_name);

            this.textView = (TextView) itemView.findViewById(R.id.title);
            //relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}
//private class ItemFilter extends Filter {
//    @Override
//    protected FilterResults performFiltering(CharSequence constraint) {
//
//        String filterString = constraint.toString().toLowerCase();
//
//        FilterResults results = new FilterResults();
//
//
//
//        final ArrayList<Model> list = listdats;
//
//        int count = list.size();
//        final ArrayList<String> nlist = new ArrayList<String>(count);
//
//        String filterableString ;
//
//        for (int i = 0; i < count; i++) {
//            filterableString = list.get(i);
//            if (filterableString.toLowerCase().contains(filterString)) {
//                nlist.add(filterableString);
//            }
//        }
//
//        results.values = nlist;
//        results.count = nlist.size();
//
//        return results;
//    }
//
//    @SuppressWarnings("unchecked")
//    @Override
//    protected void publishResults(CharSequence constraint, FilterResults results) {
//        filteredData = (ArrayList<String>) results.values;
//        notify();
//        MyListAdapter
//        this.notifyDataSetChanged();
//    }
//
//}





