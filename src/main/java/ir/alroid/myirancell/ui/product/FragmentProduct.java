package ir.alroid.myirancell.ui.product;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ir.alroid.myirancell.R;
import ir.alroid.myirancell.data.room.entity.Product;
import ir.alroid.myirancell.databinding.FragmentProductBinding;
import ir.alroid.myirancell.di.component.DaggerProductAdapterComponent;
import ir.alroid.myirancell.di.module.ProductAdapterModule;
import ir.alroid.myirancell.utils.Tools;

public class FragmentProduct extends Fragment {

    FragmentProductBinding binding;
    ViewModelProduct viewModel;

    @Inject
    AdapterProduct mAdapter;

    private int id_day = 0;
    private int id_gigabyte = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProductBinding.inflate(inflater);

        Tools.setSystemBarColor(getActivity(), R.color.banner_internet);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // init View Model
        viewModel = ViewModelProviders.of(getActivity()).get(ViewModelProduct.class);

        // set RecyclerView
        // init Adapter with Dagger
        DaggerProductAdapterComponent.builder().productAdapterModule(new ProductAdapterModule(viewModel))
                .build()
                .inject(this);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(mAdapter);

        // select
        viewModel.select().observe(getActivity(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                mAdapter.setList(products);
            }
        });

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
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

                // insert into database with RxJava
                viewModel.insert(new Product(day, gigabyte, String.valueOf(price)))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe();
            }
        });

    }

}