package com.example.covid19;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid19.Models.Xml_news.RSSFeed;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class News_adapter extends RecyclerView.Adapter<News_adapter.ViewHolder>{
    RSSFeed f1;
    public News_adapter(RSSFeed f1)
    {
        this.f1=f1;
    }
    @NonNull
    @Override
    public News_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.news_card, parent, false);
        News_adapter.ViewHolder viewHolder = new News_adapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull News_adapter.ViewHolder holder, final int position) {
        holder.description.setVisibility(View.GONE);
        holder.explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = f1.getArticleList().get(position).getLink();
                Intent i = new Intent(view.getContext(),Web_news.class);
                i.putExtra("url",f1.getArticleList().get(position).getLink());
              //  i.setData(Uri.parse(url));
                view.getContext().startActivity(i);
               // view.startActivity(i);
            }
        });
       // holder.description.setText(f1.getArticleList().get(position).getDescription());
        holder.title.setText(f1.getArticleList().get(position).getTitle());


    }

    @Override
    public int getItemCount() {
        return f1.getArticleList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView description,title;
        ImageView share;
        Button explore;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            description=itemView.findViewById(R.id.description);
            explore=itemView.findViewById(R.id.explore);
            title=itemView.findViewById(R.id.title);
             share=itemView.findViewById(R.id.share);


        }
    }
}
