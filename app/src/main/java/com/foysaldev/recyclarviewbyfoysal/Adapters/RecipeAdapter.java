package com.foysaldev.recyclarviewbyfoysal.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.foysaldev.recyclarviewbyfoysal.Models.RecipeModel;
import com.foysaldev.recyclarviewbyfoysal.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.viewHolder> implements Filterable {

    ArrayList<RecipeModel> list;
    ArrayList<RecipeModel> listfull;
    Context context;

    public RecipeAdapter(Context context ,ArrayList<RecipeModel> list) {
        this.context = context;
        this.listfull = list;
        this.list = new ArrayList<>(listfull);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_recyclarview, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        RecipeModel model = list.get(position);
        holder.imageView.setImageResource(model.getPic());
        holder.textView.setText(model.getText());

        switch (position) {
            case 0:
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "Image one is Clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                holder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "Text one Is Clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case 1:
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "Image Two is Clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                holder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "Text Two Is Clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                break;

            default:
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return recipeFilter;
    }
    private final Filter recipeFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            ArrayList<RecipeModel> recipeFilterlist = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                recipeFilterlist.addAll(listfull);
            } else {
                String filterPattern = constraint.toString().trim();

                for (RecipeModel recipeModel : listfull) {
                    if (recipeModel.getText().toLowerCase().contains(filterPattern))
                        recipeFilterlist.add(recipeModel);
                }
            }
            FilterResults results = new FilterResults();
            results.values = recipeFilterlist;
            results.count = recipeFilterlist.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            list.clear();
            list.addAll((ArrayList)results.values);
            notifyDataSetChanged();

        }
    };

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
