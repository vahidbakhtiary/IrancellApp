package ir.alroid.myirancell.di.module;

import dagger.Module;
import dagger.Provides;
import ir.alroid.myirancell.ui.home.FragmentHome;

@Module
public class FragmentHomeModule {

    @Provides
    public FragmentHome provideFragmentHome() {
        return new FragmentHome();
    }
}
