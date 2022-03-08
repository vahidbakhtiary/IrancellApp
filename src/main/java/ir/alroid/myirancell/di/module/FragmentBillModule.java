package ir.alroid.myirancell.di.module;

import dagger.Module;
import dagger.Provides;
import ir.alroid.myirancell.ui.bill.FragmentBill;

@Module
public class FragmentBillModule {

    @Provides
    public FragmentBill provideFragmentBill() {
        return new FragmentBill();
    }
}
