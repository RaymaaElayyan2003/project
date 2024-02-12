package com.example.studentsinfo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements Filterable {
    Context context;
    List<Students> list;
    List<Students> listFull;

    public RecyclerViewAdapter(Context context, List<Students> list) {
        this.context = context;
        this.list = list;
        this.listFull = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Students student = list.get(position);
//        holder.profileImage.setImageResource(student.getImageProf());
        holder.username.setText(student.getUserName());
        holder.userDesc.setText(student.getUserDes());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ViewActivity.class);
//            intent.putExtra("imageProf", student.getImageProf());
            intent.putExtra("stuname_ID", student.getUserName());
            intent.putExtra("stuDesc", student.getUserDes());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return FilterzStu;
    }

    private Filter FilterzStu = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence chrSq) {
            String searchText = chrSq.toString().toLowerCase();
            List<Students> tempList = new ArrayList<>();
            if (searchText.isEmpty()) {
                tempList.addAll(listFull);
            } else {
                for (Students item : listFull) {
                    // Use equalsIgnoreCase() for case-insensitive comparison
                    if (item.getUserName().toLowerCase().contains(searchText)) {
                        tempList.add(item);
                    }
                }
            }
            FilterResults filterresults = new FilterResults();
            filterresults.values = tempList;
            return filterresults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((Collection<? extends Students>) results.values);
            notifyDataSetChanged(); // Notify adapter of data change
        }
    };

    public void updateList(List<Students> filteredList) {
        list.clear();
        list.addAll(filteredList);
        notifyDataSetChanged();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profileImage;
        TextView username, userDesc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profileImage);
            username = itemView.findViewById(R.id.username);
            userDesc = itemView.findViewById(R.id.usernameDesc);
        }
    }
}