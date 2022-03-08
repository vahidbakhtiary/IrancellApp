package ir.alroid.myirancell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import ir.alroid.myirancell.databinding.ActivityMainBinding;
import ir.alroid.myirancell.di.component.DaggerFragmentBillComponent;
import ir.alroid.myirancell.di.component.DaggerFragmentHomeComponent;
import ir.alroid.myirancell.di.component.DaggerFragmentIrancellServiceComponent;
import ir.alroid.myirancell.di.component.DaggerFragmentMediaComponent;
import ir.alroid.myirancell.di.component.DaggerFragmentProductComponent;
import ir.alroid.myirancell.ui.bill.FragmentBill;
import ir.alroid.myirancell.ui.home.FragmentHome;
import ir.alroid.myirancell.ui.irancell_services.FragmentIrancellService;
import ir.alroid.myirancell.ui.media.FragmentMedia;
import ir.alroid.myirancell.ui.product.FragmentProduct;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // set home fragment by default:
        binding.bottomNavigation.setSelectedItemId(R.id.home);

        Fragment selectedFragment = DaggerFragmentHomeComponent
                .create()
                .getFragmentHome();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

        binding.bottomNavigation.setItemIconTintList(null);

        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.bill:

                        selectedFragment = DaggerFragmentBillComponent
                                .create()
                                .getFragmentBill();
                        break;
                    case R.id.media:
                        selectedFragment = DaggerFragmentMediaComponent
                                .create()
                                .getFragmentMedia();
                        break;
                    case R.id.services:
                        selectedFragment = DaggerFragmentIrancellServiceComponent
                                .create()
                                .getFragmentIrancellService();
                        break;
                    case R.id.home:
                        selectedFragment = DaggerFragmentHomeComponent
                                .create()
                                .getFragmentHome();
                        break;
                    case R.id.product:
                        selectedFragment = DaggerFragmentProductComponent
                                .create()
                                .getFragmentProduct();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                return true;
            }
        });
    }

    private BottomNavigationView.OnNavigationItemReselectedListener navListener = new BottomNavigationView.OnNavigationItemReselectedListener() {
        @Override
        public void onNavigationItemReselected(@NonNull MenuItem item) {

        }
    };

}