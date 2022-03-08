package ir.alroid.myirancell.ui.irancell_services;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ir.alroid.myirancell.R;
import ir.alroid.myirancell.data.room.entity.IrancellService;
import ir.alroid.myirancell.databinding.FragmentIrancellServiceBinding;
import ir.alroid.myirancell.di.component.DaggerIrancellServiceAdapterComponent;
import ir.alroid.myirancell.di.module.IrancellServiceAdapterModule;
import ir.alroid.myirancell.utils.ItemAnimation;
import ir.alroid.myirancell.utils.Tools;

public class FragmentIrancellService extends Fragment {

    FragmentIrancellServiceBinding binding;

    private View mParentView;
    private List<IrancellService> services = new ArrayList<>();
    private int animationType = ItemAnimation.FADE_IN;

    @Inject
    AdapterIrancellService mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentIrancellServiceBinding.inflate(inflater);

        Tools.setSystemBarColor(getActivity(), R.color.banner_services);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // init recycler
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3, RecyclerView.VERTICAL, false));
        binding.recyclerView.setHasFixedSize(true);

        // get sample data
        services = getServices(getActivity());

        // init adapter with Dagger
        DaggerIrancellServiceAdapterComponent.builder()
                .irancellServiceAdapterModule(new IrancellServiceAdapterModule(getActivity(), services, animationType))
                .build()
                .inject(this);

        binding.recyclerView.setAdapter(mAdapter);

        // on item list clicked
        mAdapter.onItemClickListener(new AdapterIrancellService.OnItemClickListener() {
            @Override
            public void onItemClick(View view, IrancellService irancellService, int position) {
                showSnackBar("در دست ساخت", R.color.snackBar_blue);
            }
        });

    }

    private List<IrancellService> getServices(Context context) {

        Resources resources = context.getResources();
        String[] servicesTittle = resources.getStringArray(R.array.services_tittle);
        List<IrancellService> services = new ArrayList<>();

        for (int i = 0; i < servicesTittle.length; i++) {
            IrancellService service = new IrancellService();
            service.setName(servicesTittle[i]);
            switch (i) {
                case 0:
                    service.setImgRes(R.drawable.ic_service_01);
                    break;
                case 1:
                    service.setImgRes(R.drawable.ic_service_02);
                    break;
                case 2:
                    service.setImgRes(R.drawable.ic_service_03);
                    break;
                case 3:
                    service.setImgRes(R.drawable.ic_service_04);
                    break;
                case 4:
                    service.setImgRes(R.drawable.ic_service_05);
                    break;
                case 5:
                    service.setImgRes(R.drawable.ic_service_06);
                    break;
                case 6:
                    service.setImgRes(R.drawable.ic_service_07);
                    break;
                case 7:
                    service.setImgRes(R.drawable.ic_service_08);
                    break;
                case 8:
                    service.setImgRes(R.drawable.ic_service_09);
                    break;
                case 9:
                    service.setImgRes(R.drawable.ic_service_10);
                    break;
                case 10:
                    service.setImgRes(R.drawable.ic_service_11);
                    break;
                case 11:
                    service.setImgRes(R.drawable.ic_service_12);
                    break;
                case 12:
                    service.setImgRes(R.drawable.ic_service_13);
                    break;
                case 13:
                    service.setImgRes(R.drawable.ic_service_14);
                    break;
                case 14:
                    service.setImgRes(R.drawable.ic_service_15);
                    break;
                case 15:
                    service.setImgRes(R.drawable.ic_service_16);
                    break;
                case 16:
                    service.setImgRes(R.drawable.ic_service_17);
                    break;
                case 17:
                    service.setImgRes(R.drawable.ic_service_18);
                    break;
                case 18:
                    service.setImgRes(R.drawable.ic_service_19);
                    break;
                case 19:
                    service.setImgRes(R.drawable.ic_service_20);
                    break;
                case 20:
                    service.setImgRes(R.drawable.ic_service_21);
                    break;
            }

            services.add(service);
        }

        return services;
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