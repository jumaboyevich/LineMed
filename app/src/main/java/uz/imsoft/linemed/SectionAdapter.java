package uz.imsoft.linemed;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder> {


    private List<SectionNote> mData;
    private int indicator_position = -1, previousItem = -1;
//    private Typeface face1, face2;
    Context mContext;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

      //        face1 = ResourcesCompat.getFont(parent.getContext(), R.font.gilroy_extra_bold);
//        face2 = ResourcesCompat.getFont(parent.getContext(), R.font.gilroy_light);
        mContext=parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_section, parent, false));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        SectionNote object = mData.get(position);

        holder.img_section.setImageDrawable(mContext.getDrawable(object.getImg()));
        holder.txt_section.setText(object.getNomi());

        if (indicator_position == position || indicator_position == -1) {
            holder.img_section.setBorderWidth(5);
            holder.img_section.setAlpha((float) 1.0);
            holder.img_section.setForeground(new ColorDrawable(ContextCompat.getColor(mContext, R.color.transparent)));
            holder.txt_section.setAlpha((float) 1.0);
        } else {
            holder.img_section.setBorderWidth(0);
            holder.img_section.setAlpha((float) 0.6);
            holder.img_section.setForeground(ContextCompat.getDrawable(mContext, R.drawable.back_section_foreground));
            holder.txt_section.setAlpha((float) 0.6);
        }
//        holder.item_indicator.setSelected(holder.itemView.isSelected());

        holder.itemView.setOnClickListener(v -> {

            previousItem = indicator_position;
            indicator_position = position;

            notifyItemChanged(previousItem);
            notifyItemChanged(indicator_position);

        });

    }

    public void swapData(List<SectionNote> sectionNoteList) {
        mData = sectionNoteList;

        notifyDataSetChanged();
    }

    public void updateIndocator(int position) {
        indicator_position = position;

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mData != null)
            mData.size();
        else
            mData = new ArrayList<>();
        return mData.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder  {

        private final CircleImageView img_section;
        private final TextView txt_section;

        ViewHolder(View itemView) {
            super(itemView);

            img_section = itemView.findViewById(R.id.img_section);
            txt_section = itemView.findViewById(R.id.txt_section);
        }


    }


    // allows clicks events to be caught
//    void setClickListener(ItemClickListener itemClickListener) {
//        this.mClickListener = itemClickListener;
//    }
//
//    // parent activity will implement this method to respond to click events
//    public interface ItemClickListener {
//        void onItemClick(View view, SavdoNote savdoNote, int listposition);
//    }
};