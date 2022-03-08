package ir.alroid.myirancell.ui.irancell_services;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ir.alroid.myirancell.R;
import ir.alroid.myirancell.data.room.entity.IrancellService;
import ir.alroid.myirancell.databinding.ItemServiceBinding;
import ir.alroid.myirancell.utils.ItemAnimation;

public class AdapterIrancellService extends RecyclerView.Adapter<AdapterIrancellService.ServiceViewHolder> {

    private List<IrancellService> list;
    private Context ctx;
    private OnItemClickListener mItemClickListener;

    // Constructor
    public AdapterIrancellService(Context ctx, List<IrancellService> list, int animationType) {
        this.list = list;
        this.ctx = ctx;
        this.animationType = animationType;
    }

    public void setList(List<IrancellService> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ServiceViewHolder(ItemServiceBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {

        IrancellService service = this.list.get(position);

        holder.binding.tvName.setText(service.getName());
        holder.binding.ivService.setImageResource(service.getImgRes());

        holder.binding.lytParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(view, list.get(position), position);
                }
            }
        });

        setAnimation(holder.itemView, position);

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
    class ServiceViewHolder extends RecyclerView.ViewHolder {

        ItemServiceBinding binding;

        public ServiceViewHolder(@NonNull ItemServiceBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////

    // item click listener interface
    public interface OnItemClickListener {
        void onItemClick(View view, IrancellService irancellService, int position);
    }

    // set Animation
    private int lastPosition = -1;
    private int animationType = 0;

    private void setAnimation(View itemView, int position) {
        if (position > lastPosition) {
            ItemAnimation.animate(itemView, position, animationType);
            lastPosition = position;
        }
    }

    private boolean onAttach = true;

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                onAttach = false;
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        super.onAttachedToRecyclerView(recyclerView);
    }


    public void onItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

}
