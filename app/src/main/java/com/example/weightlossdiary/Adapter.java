package com.example.weightlossdiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context context;
    private List<Quote> quoteList;

    public Adapter(Context context, List<Quote> quoteList) {
        this.context = context;
        this.quoteList = quoteList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.entry_row, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Quote quote = quoteList.get(position);
        holder.quote.setText(quote.getTitle());
        holder.author.setText(quote.getAuthor());
        holder.username.setText(quote.getUsername());
    }

    @Override
    public int getItemCount() {
        return quoteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView quote;
        public TextView author;
        public TextView username;


        public ViewHolder(View view, Context cont) {
            super(view);
            context = cont;
            quote = view.findViewById(R.id.postQuote);
            author = view.findViewById(R.id.postAuthor);
            username = view.findViewById(R.id.postUsername);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //go to next activity
                }
            });
        }
    }
}
