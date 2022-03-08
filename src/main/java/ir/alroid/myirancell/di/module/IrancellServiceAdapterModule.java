package ir.alroid.myirancell.di.module;

import android.content.Context;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import ir.alroid.myirancell.data.room.entity.IrancellService;
import ir.alroid.myirancell.ui.irancell_services.AdapterIrancellService;

@Module
public class IrancellServiceAdapterModule {

    Context context;
    List<IrancellService> list;
    int animationType;

    // Constructor
    public IrancellServiceAdapterModule(Context context, List<IrancellService> list, int animationType) {
        this.context = context;
        this.list = list;
        this.animationType = animationType;
    }

    @Provides
    public AdapterIrancellService provideAdapterIrancellService() {
        return new AdapterIrancellService(context, list, animationType);
    }
}
