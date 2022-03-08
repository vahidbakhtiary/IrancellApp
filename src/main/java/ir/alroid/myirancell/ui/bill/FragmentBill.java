package ir.alroid.myirancell.ui.bill;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import ir.alroid.myirancell.R;
import ir.alroid.myirancell.databinding.FragmentBillBinding;
import ir.alroid.myirancell.utils.Tools;

public class FragmentBill extends Fragment {

    FragmentBillBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // binding
        binding = FragmentBillBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Tools.setSystemBarColor(getActivity(), R.color.banner_bill);

        binding.btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackBar("در دست ساخت", R.color.snackBar_orange);
            }
        });

        binding.btnDerails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackBar("در دست ساخت", R.color.snackBar_green);
            }
        });
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