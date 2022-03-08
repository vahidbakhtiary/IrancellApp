package ir.alroid.myirancell.di.component;

import dagger.Component;
import ir.alroid.myirancell.di.module.FragmentIrancellServiceModule;
import ir.alroid.myirancell.ui.irancell_services.FragmentIrancellService;

@Component(modules = FragmentIrancellServiceModule.class)
public interface FragmentIrancellServiceComponent {

    FragmentIrancellService getFragmentIrancellService();
}
