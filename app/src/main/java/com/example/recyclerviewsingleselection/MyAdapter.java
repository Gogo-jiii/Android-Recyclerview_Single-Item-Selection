package com.example.recyclerviewsingleselection;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ModelClass> arrayList;
    int singleitem_selection_position = -1;

    public MyAdapter(MainActivity context, ArrayList<ModelClass> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ModelClass modelClass = arrayList.get(position);
        holder.textView.setText(modelClass.getName());

        if (singleitem_selection_position == position) {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.highlight));
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ConstraintLayout rowItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.textView);
            this.rowItem = itemView.findViewById(R.id.rowitem);

            rowItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSingleSelection(getAdapterPosition());
                }
            });
        }
    }

    private void setSingleSelection(int adapterPosition) {
        if (adapterPosition == RecyclerView.NO_POSITION) return;

        notifyItemChanged(singleitem_selection_position);
        singleitem_selection_position = adapterPosition;
        notifyItemChanged(singleitem_selection_position);

    }
}
