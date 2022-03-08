package ir.alroid.myirancell.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ir.alroid.myirancell.R;
import ir.alroid.myirancell.databinding.LayoutBottomSheetEditBinding;
import ir.alroid.myirancell.databinding.LayoutBottomSheetInternetBinding;
import ir.alroid.myirancell.ui.product.ActionBottomDialogFragment;
import ir.alroid.myirancell.ui.product.ViewModelProduct;

public class ActionBottomDialogInternet extends BottomSheetDialogFragment implements View.OnClickListener {

    public static final String TAG = ActionBottomDialogInternet.class.getSimpleName();

    LayoutBottomSheetInternetBinding binding;

    public static ActionBottomDialogInternet newInstance() {
        return new ActionBottomDialogInternet();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = LayoutBottomSheetInternetBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
