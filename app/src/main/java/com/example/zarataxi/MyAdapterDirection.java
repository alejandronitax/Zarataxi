package com.example.zarataxi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

public class MyAdapterDirection extends RecyclerView.Adapter<MyAdapterDirection.MyViewHolder> {

    private List<DirectionItem> directionItems;
    private MiOnItemClickListener listener;


    public interface MiOnItemClickListener {
        void miOnItemClick(int posicionItem);
    }

    MyAdapterDirection(List<DirectionItem> directionItems, MiOnItemClickListener listener) {
        this.directionItems = directionItems;
        this.listener = listener;

    }
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder  implements
            View.OnClickListener {

        // each data item is just a string in this case
        public TextView textView;
        public ImageView imagenView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenView = itemView.findViewById(R.id.imageView7);
            textView= itemView.findViewById(R.id.textView7);
            itemView.setOnClickListener(this);
        }

        void setImagen(DirectionItem directionItem){
            imagenView.setImageResource(directionItem.getImage());
        }

        void setTextView(DirectionItem directionItem){
            textView.setText(directionItem.getText());

        }
        @Override
        public void onClick(View view) {
            listener.miOnItemClick(getLayoutPosition());
        }
    }


    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapterDirection.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        return new MyViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_direction,parent,false
                )
        );
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.setImagen(directionItems.get(position));
        holder.setTextView(directionItems.get(position));



    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return directionItems.size();
    }
}