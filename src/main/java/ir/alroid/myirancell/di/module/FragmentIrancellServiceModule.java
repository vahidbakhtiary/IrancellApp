package ir.alroid.myirancell.di.module;

import dagger.Module;
import dagger.Provides;
import ir.alroid.myirancell.ui.irancell_services.FragmentIrancellService;

@Module
public class FragmentIrancellServiceModule {

    @Provides
    public FragmentIrancellService provideFragmentIrancellService() {
        return new FragmentIrancellService();
    }
}
