package ir.alroid.myirancell.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import ir.alroid.myirancell.R;
import ir.alroid.myirancell.data.room.entity.DeviceInfo;
import ir.alroid.myirancell.databinding.LayoutBottomSheetInternetBinding;
import ir.alroid.myirancell.databinding.LayoutBottomSheetPhoneNumberBinding;
import ir.alroid.myirancell.utils.Tools;

public class ActionBottomDialogPhoneNumber extends BottomSheetDialogFragment implements View.OnClickListener {

    public static final String TAG = ActionBottomDialogPhoneNumber.class.getSimpleName();

    LayoutBottomSheetPhoneNumberBinding binding;

    public static ActionBottomDialogPhoneNumber newInstance() {
        return new ActionBottomDialogPhoneNumber();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = LayoutBottomSheetPhoneNumberBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DeviceInfo deviceInfo = Tools.getDeviceInfo(getActivity());

        binding.tvDeviceName.setText(deviceInfo.device);
        binding.tvOsVersion.setText(deviceInfo.os_version);

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
