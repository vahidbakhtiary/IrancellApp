package ir.alroid.myirancell.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;

import ir.alroid.myirancell.R;
import ir.alroid.myirancell.databinding.LayoutBottomSheetPhoneNumberBinding;
import ir.alroid.myirancell.databinding.LayoutBottomSheetWalletBinding;

public class ActionBottomDialogWallet extends BottomSheetDialogFragment implements View.OnClickListener {

    public static final String TAG = ActionBottomDialogWallet.class.getSimpleName();

    LayoutBottomSheetWalletBinding binding;

    public static ActionBottomDialogWallet newInstance() {
        return new ActionBottomDialogWallet();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = LayoutBottomSheetWalletBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showSnackBar("در دست ساخت", R.color.snackBar_orange);
                dismiss();
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

    public void showSnackBar(String message, int colorId) {
        View parent_view = getActivity().findViewById(android.R.id.content);

        final Snackbar snackbar = Snackbar.make(parent_view, "", Snackbar.LENGTH_SHORT);

        // inflate View
        View custom_view = getLayoutInflater().inflate(R.layout.snackbar_icon_text, null);

        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackBarView = (Snackbar.SnackbarLayout) snackbar.getView();
        snackBarView.setPadding(0, 0, 0, 0);

        // Customize
        ((TextView) custom_view.findViewById(R.id.message)).setText(message);
        //((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_done);
        //(custom_view.findViewById(R.id.parent_view)).setBackgroundColor(getResources().getColor(R.color.green_500));
        (custom_view.findViewById(R.id.parent_view)).setBackgroundColor(getResources().getColor(colorId));

        snackBarView.addView(custom_view, 0);
        snackbar.show();
    }

}
