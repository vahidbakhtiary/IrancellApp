package ir.alroid.myirancell.ui.product;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ir.alroid.myirancell.data.room.entity.Product;
import ir.alroid.myirancell.databinding.ItemProductBinding;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ProductViewHolder> {

    private List<Product> list;
    private ViewModelProduct viewModel;

    // Constructor
    public AdapterProduct(ViewModelProduct viewModel) {
        this.list = new ArrayList<>();
        this.viewModel = viewModel;
    }

    public void setList(List<Product> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        Product product = this.list.get(position);

        holder.binding.day.setText(product.getDay());
        holder.binding.gigabyte.setText(product.getGigabyte());
        holder.binding.price.setText(product.getPrice());

        holder.binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.delete(product)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe();
            }
        });

        holder.binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // show dialog
                Bundle args = new Bundle();
                args.putInt("id", product.getId());

                ActionBottomDialogFragment bottomDialogFragment =
                        ActionBottomDialogFragment.newInstance();

                bottomDialogFragment.setArguments(args);

                bottomDialogFragment.show(((AppCompatActivity) view.getContext()).getSupportFragmentManager(),
                        ActionBottomDialogFragment.TAG);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////

    class ProductViewHolder extends RecyclerView.ViewHolder {

        ItemProductBinding binding;

        public ProductViewHolder(@NonNull ItemProductBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;

        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////

    // Bottom Sheet (Edit button in items):
    public void showBottomSheet(View view) {
        ActionBottomDialogFragment bottomDialogFragment =
                ActionBottomDialogFragment.newInstance();

        bottomDialogFragment.show(((AppCompatActivity) view.getContext()).getSupportFragmentManager(),
                ActionBottomDialogFragment.TAG);
    }

}
