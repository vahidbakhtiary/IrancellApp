package ir.alroid.myirancell.ui.media;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.alroid.myirancell.data.room.entity.IrancellService;
import ir.alroid.myirancell.data.room.entity.Media;
import ir.alroid.myirancell.databinding.ItemMediaBinding;
import ir.alroid.myirancell.databinding.ItemServiceBinding;
import ir.alroid.myirancell.ui.irancell_services.AdapterIrancellService;
import ir.alroid.myirancell.utils.ItemAnimation;

public class AdapterMedia extends RecyclerView.Adapter<AdapterMedia.MyViewHolder> {

    private Context ctx;
    private List<Media> mediaList;
    private OnItemClickListener mItemClickListener;

    // Constructor
    public AdapterMedia(Context ctx, List<Media> mediaList, int animationType) {
        this.mediaList = mediaList;
        this.ctx = ctx;
        this.animationType = animationType;
    }

    public void setList(List<Media> mediaList) {
        this.mediaList = mediaList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(ItemMediaBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Media media = this.mediaList.get(position);

        holder.binding.imgMedia.setImageDrawable(media.getImgDrawable());

        holder.binding.lytParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(view, mediaList.get(position), position);
                }
            }
        });

        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return this.mediaList.size();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    class MyViewHolder extends RecyclerView.ViewHolder {

        ItemMediaBinding binding;

        public MyViewHolder(@NonNull ItemMediaBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////

    // item click listener interface
    public interface OnItemClickListener {
        void onItemClick(View view, Media media, int position);
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
