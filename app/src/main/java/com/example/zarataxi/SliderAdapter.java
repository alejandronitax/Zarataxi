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

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private List<SliderItem> sliderItems;
    private ViewPager2 viewPager2;

    class SliderViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagenView;
        private TextView textView;

        SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenView = itemView.findViewById(R.id.imageViewVarius);
            textView= itemView.findViewById(R.id.textViewVarius);
        }

        void setImagen(SliderItem sliderItem){
            imagenView.setImageResource(sliderItem.getImage());
        }

        void setTextView(SliderItem sliderItem){
            textView.setText(sliderItem.getText());
        }

    }


     SliderAdapter(List<SliderItem> sliderItems, ViewPager2 viewPager2) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
    }



    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.slider_item_container,parent,false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setImagen(sliderItems.get(position));
        holder.setTextView(sliderItems.get(position));
        if(position == sliderItems.size() - 2 ){
            viewPager2.post(runnable);

        }

    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            sliderItems.addAll(sliderItems);
            notifyDataSetChanged();
        }
    };
}
