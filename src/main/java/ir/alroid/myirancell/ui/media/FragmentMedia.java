package ir.alroid.myirancell.ui.media;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ir.alroid.myirancell.R;
import ir.alroid.myirancell.data.room.entity.IrancellService;
import ir.alroid.myirancell.data.room.entity.Media;
import ir.alroid.myirancell.databinding.FragmentMediaBinding;
import ir.alroid.myirancell.utils.ItemAnimation;
import ir.alroid.myirancell.utils.Tools;

public class FragmentMedia extends Fragment {

    FragmentMediaBinding binding;

    private View mParentView;
    private List<Media> mediaList = new ArrayList<>();
    private int animationType = ItemAnimation.FADE_IN;
    AdapterMedia mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // binding
        binding = FragmentMediaBinding.inflate(inflater);

        Tools.setSystemBarColor(getActivity(), R.color.banner_media);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // init recycler
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false));
        binding.recyclerView.setHasFixedSize(true);

        // get sample data
        mediaList = getMediaList();

        // init adapter
        mAdapter = new AdapterMedia(getActivity(), mediaList, animationType);
        binding.recyclerView.setAdapter(mAdapter);

        // on item list clicked
        mAdapter.onItemClickListener(new AdapterMedia.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Media media, int position) {

                Intent intent = new Intent(getActivity(), ActivityMedia.class)
                        .putExtra("position", position);

                startActivity(intent);

            }
        });


    }

    private List<Media> getMediaList() {
        List<Media> mediaList = new ArrayList<>();

        for (int i = 1; i <= 6; i++) {
            switch (i) {
                case 1:
                    mediaList.add(new Media(ContextCompat.getDrawable(getActivity(), R.drawable.banner_media_01)));
                    break;
                case 2:
                    mediaList.add(new Media(ContextCompat.getDrawable(getActivity(), R.drawable.banner_media_02)));
                    break;
                case 3:
                    mediaList.add(new Media(ContextCompat.getDrawable(getActivity(), R.drawable.banner_media_03)));
                    break;
                case 4:
                    mediaList.add(new Media(ContextCompat.getDrawable(getActivity(), R.drawable.banner_media_04)));
                    break;
                case 5:
                    mediaList.add(new Media(ContextCompat.getDrawable(getActivity(), R.drawable.banner_media_05)));
                    break;
                case 6:
                    mediaList.add(new Media(ContextCompat.getDrawable(getActivity(), R.drawable.banner_media_06)));
                    break;
            }

        }

        return mediaList;
    }

}