package ir.alroid.myirancell.ui.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ir.alroid.myirancell.R;
import ir.alroid.myirancell.data.room.AppDatabase;
import ir.alroid.myirancell.data.room.entity.Product;
import ir.alroid.myirancell.databinding.LayoutBottomSheetEditBinding;

public class ActionBottomDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    public static final String TAG = ActionBottomDialogFragment.class.getSimpleName();

    LayoutBottomSheetEditBinding binding;
    ViewModelProduct viewModel;

    private int id_day = 0;
    private int id_gigabyte = 0;

    public static ActionBottomDialogFragment newInstance() {
        return new ActionBottomDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = LayoutBottomSheetEditBinding.inflate(inflater);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // init View Model
        viewModel = ViewModelProviders.of(getActivity()).get(ViewModelProduct.class);

        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get radio button info
                // get Day
                id_day = binding.radioGroupDay.getCheckedRadioButtonId();
                RadioButton radioButtonDay = view.findViewById(id_day);
                String day = radioButtonDay.getText().toString();

                // get Gigabyte
                id_gigabyte = binding.radioGroupGigabyte.getCheckedRadioButtonId();
                RadioButton radioButtonGigabyte = view.findViewById(id_gigabyte);
                String gigabyte = radioButtonGigabyte.getText().toString();

                int price = Integer.parseInt(day) * 2300;

                // get product that user clicked
                Bundle mArgs = getArguments();

                assert mArgs != null;
                int id = mArgs.getInt("id");

                // update database with RxJava
                viewModel.updateProduct(id, day, gigabyte, String.valueOf(price))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe();

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

}
