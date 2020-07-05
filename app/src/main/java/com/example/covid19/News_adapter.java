package com.example.covid19;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid19.Models.Xml_news.RSSFeed;

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
    public void onBindViewHolder(@NonNull News_adapter.ViewHolder holder, int position) {
        holder.description.setVisibility(View.GONE);
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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            description=itemView.findViewById(R.id.description);
            title=itemView.findViewById(R.id.title);
             share=itemView.findViewById(R.id.share);

        }
    }
}
