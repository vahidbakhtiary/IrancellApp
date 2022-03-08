package ir.alroid.myirancell.di.component;

import dagger.Component;
import ir.alroid.myirancell.di.module.IrancellServiceAdapterModule;
import ir.alroid.myirancell.ui.irancell_services.FragmentIrancellService;

@Component(modules = IrancellServiceAdapterModule.class)
public interface IrancellServiceAdapterComponent {

    void inject(FragmentIrancellService fragmentIrancellService);
}
