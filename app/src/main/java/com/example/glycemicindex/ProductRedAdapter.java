package com.example.glycemicindex;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductRedAdapter extends RecyclerView.Adapter<ProductRedAdapter.ProductRedHolder> implements Filterable {

    private ArrayList<Product> productArrayList;
    private ArrayList<Product> productArrayListFull;
    private ProductGreenAdapter.CallBack callback;
    private Context context;

    public ProductRedAdapter(ArrayList<Product> productArrayList, Context context) {
        this.context = context;
        this.productArrayList = productArrayList;
        productArrayListFull = new ArrayList<>(productArrayList);
    }

    @NonNull
    @Override
    public ProductRedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_product, parent, false);

        return new ProductRedHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRedHolder holder, int position) {
        holder.bind(productArrayList.get(position));
    }

    public void setCallback(ProductGreenAdapter.CallBack callback) {
        this.callback = callback;
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Product> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(productArrayListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Product item : productArrayListFull) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            productArrayList.clear();
            productArrayList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public void sortByName() {
        Collections.sort(productArrayList, Product.sortByName);
        notifyDataSetChanged();
    }

    public void sortByIndex() {
        Collections.sort(productArrayList, Product.sortByIndex);
        notifyDataSetChanged();
    }

    public void reverseByName(){
        Collections.reverse(productArrayList);
        notifyDataSetChanged();
    }

    public class ProductRedHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_item)
        LinearLayout ll_item;

        @BindView(R.id.txt_name)
        TextView txt_name;

        @BindView(R.id.txt_index)
        TextView txt_index;

        @BindView(R.id.imgView_index)
        ImageView imgView_index;

        Product bindProduct;

        public ProductRedHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Product product) {
            bindProduct = product;
            txt_name.setText(product.name);
            txt_index.setText(product.index);
            imgView_index.setColorFilter(ContextCompat.getColor(context, R.color.colorAccent));
        }

        @OnClick(R.id.ll_item)
        public void onClickItem() {
            callback.selecteditem(bindProduct);
        }
    }

    public interface CallBack {
        void selecteditem(Product product);
    }
}
