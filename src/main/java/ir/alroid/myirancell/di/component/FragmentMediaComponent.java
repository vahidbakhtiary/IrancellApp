package ir.alroid.myirancell.di.component;

import dagger.Component;
import ir.alroid.myirancell.di.module.FragmentMediaModule;
import ir.alroid.myirancell.ui.media.FragmentMedia;

@Component(modules = FragmentMediaModule.class)
public interface FragmentMediaComponent {

    FragmentMedia getFragmentMedia();
}
