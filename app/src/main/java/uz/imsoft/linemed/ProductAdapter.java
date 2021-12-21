package uz.imsoft.linemed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> implements Filterable {


    private List<ProductNote> mData, temp_array;
    private ArrayList<ProductNote> filters = null;
    private CustomFilter customFilter;

    private final ProductInterface productInterface;
    private ProductInterface mClickListener;

    public ProductAdapter(ProductInterface productInterface) {
        this.productInterface = productInterface;
    }

    @Override
    public  ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false));

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProductNote object = mData.get(position);

        holder.myTextView.setText(object.getNomi());

    }

    public void swapData(List<ProductNote> productNoteList) {
        mData = productNoteList;
        temp_array = productNoteList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        if (filters == null) {
            if (mData == null)
                mData = new ArrayList<>();
            return mData.size();
        } else {
            return filters.size();
        }

    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);

            myTextView = itemView.findViewById(R.id.item_product_name);
        }


        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.addCartItem( mData.get(getAdapterPosition()), view);
        }
    }

    @Override
    public Filter getFilter() {
        if (customFilter == null) {
            customFilter = new CustomFilter();
        }
        return customFilter;
    }

    class CustomFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {

                filters = new ArrayList<>();


                for (int i = 0; i < temp_array.size(); i++) {
                    String abc = temp_array.get(i).getType_id()+"";
                    if (abc.toUpperCase().contentEquals(constraint)) {

                        filters.add(temp_array.get(i));
                    }
                }


                results.count = filters.size();
                results.values = filters;
            } else {
                results.count = temp_array.size();
                results.values = temp_array;
                filters = null;
            }
            productInterface.listSize(results.count != 0);
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mData = (ArrayList<ProductNote>) results.values;
            notifyDataSetChanged();
        }
    }
    public void setClickListener(ProductInterface itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ProductInterface {
        void addCartItem(ProductNote note, View view);
        void listSize(boolean value);
    }
};