package ir.alroid.myirancell.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import ir.alroid.myirancell.R;
import ir.alroid.myirancell.data.room.entity.DeviceInfo;
import ir.alroid.myirancell.databinding.LayoutBottomSheetMeBinding;
import ir.alroid.myirancell.databinding.LayoutBottomSheetPhoneNumberBinding;
import ir.alroid.myirancell.utils.Tools;

public class ActionBottomDialogMe extends BottomSheetDialogFragment implements View.OnClickListener {

    public static final String TAG = ActionBottomDialogMe.class.getSimpleName();

    LayoutBottomSheetMeBinding binding;

    public static ActionBottomDialogMe newInstance() {
        return new ActionBottomDialogMe();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = LayoutBottomSheetMeBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.tvWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://alialahverdi.ir/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View view) {
        // switch
        // Also we can edit product in database here ...
    }

    public interface ItemClickListener {
        void onItemClick(String item);
    }

    @Override
    public int getTheme() {
        return R.style.AppBottomSheetDialogTheme;
    }

}
