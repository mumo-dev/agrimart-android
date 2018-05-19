package com.example.mumo.agrimart.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mumo.agrimart.R;
import com.example.mumo.agrimart.model.Product;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.DataViewHolder> {

    private Context mContext;
    private List<Product> mProductList;

    public ProductsAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.product_list_item, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        Product product = mProductList.get(position);
        holder.mNameTextView.setText(product.getName());
        holder.mPriceTextView.setText(product.getPrice());
//        holder.mImageView.setImageDrawable(R.drawable.food9);
    }

    @Override
    public int getItemCount() {
        if (mProductList == null) return 0;
        return mProductList.size();
    }

    public void setProducts(List<Product> products){
        mProductList = products;
        notifyDataSetChanged();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {

        private TextView mNameTextView, mPriceTextView;
        private ImageView mImageView;

        public DataViewHolder(View itemView) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.product_name);
            mPriceTextView = itemView.findViewById(R.id.product_price);
            mImageView = itemView.findViewById(R.id.product_image);
        }

        public void bind() {

        }
    }
}
