package ir.alroid.myirancell.di.module;

import dagger.Module;
import dagger.Provides;
import ir.alroid.myirancell.ui.media.FragmentMedia;

@Module
public class FragmentMediaModule {

    @Provides
    public FragmentMedia provideFragmentMedia() {
        return new FragmentMedia();
    }
}
