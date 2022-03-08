package ir.alroid.myirancell.di.component;

import dagger.Component;
import ir.alroid.myirancell.di.module.FragmentHomeModule;
import ir.alroid.myirancell.ui.home.FragmentHome;

@Component(modules = FragmentHomeModule.class)
public interface FragmentHomeComponent {

    FragmentHome getFragmentHome();
}
