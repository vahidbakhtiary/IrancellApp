package ir.alroid.myirancell.ui.media;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import ir.alroid.myirancell.databinding.ActivityMediaBinding;
import ir.alroid.myirancell.utils.Tools;

public class ActivityMedia extends AppCompatActivity {

    ActivityMediaBinding binding;

    SimpleExoPlayer exoPlayer;
    boolean playWhenReady = true;
    int windowIndex = 0;
    long positionMs = 0;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // binding
        binding = ActivityMediaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Tools.setSystemBarColor(this, R.color.black);

        // get intent with viewModel
        Intent intent = getIntent();
        if (intent != null) {
            position = intent.getIntExtra("position", 0);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        initPlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releasePlayer();
    }

    private void initPlayer() {
        exoPlayer = ExoPlayerFactory.newSimpleInstance(this);
        binding.playerView.setPlayer(exoPlayer);

        exoPlayer.setPlayWhenReady(playWhenReady);
        exoPlayer.seekTo(windowIndex, positionMs);

        exoPlayer.prepare(buildMediaSourcePlayList(), false, false);
    }

    private void releasePlayer() {
        if (exoPlayer != null) {

            playWhenReady = exoPlayer.getPlayWhenReady();
            windowIndex = exoPlayer.getCurrentWindowIndex();
            positionMs = exoPlayer.getCurrentPosition();

            exoPlayer.release();
            exoPlayer = null;
        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory factory = new DefaultDataSourceFactory(this, "myIrancell");
        return new ProgressiveMediaSource
                .Factory(factory)
                .createMediaSource(uri);
    }

    private Uri getUri(int position) {
        //File file = ActivityCompat.getDataDir(this);
        Uri uri = null;
        switch (position) {
            case 0:
                uri = Uri.parse(getString(R.string.clip1));
                break;
            case 1:
                uri = Uri.parse(getString(R.string.clip2));
                break;
            case 2:
                uri = Uri.parse(getString(R.string.clip3));
                break;
            case 3:
                uri = Uri.parse(getString(R.string.clip4));
                break;
            case 4:
                uri = Uri.parse(getString(R.string.clip5));
                break;
            case 5:
                uri = Uri.parse(getString(R.string.clip6));
                break;
        }

        return uri;
    }

    private MediaSource buildMediaSourcePlayList() {
        MediaSource mediaSource_1 = buildMediaSource(getUri(position));
        MediaSource mediaSource_2 = buildMediaSource(getUri(1));
        MediaSource mediaSource_3 = buildMediaSource(getUri(2));
        MediaSource mediaSource_4 = buildMediaSource(getUri(3));
        MediaSource mediaSource_5 = buildMediaSource(getUri(4));
        MediaSource mediaSource_6 = buildMediaSource(getUri(5));

        return new ConcatenatingMediaSource(
                mediaSource_1,
                mediaSource_2,
                mediaSource_3,
                mediaSource_4,
                mediaSource_5,
                mediaSource_6
        );

    }

}