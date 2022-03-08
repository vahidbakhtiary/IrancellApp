package ir.alroid.myirancell.di.component;

import dagger.Component;
import ir.alroid.myirancell.di.module.FragmentBillModule;
import ir.alroid.myirancell.ui.bill.FragmentBill;

@Component(modules = FragmentBillModule.class)
public interface FragmentBillComponent {

    FragmentBill getFragmentBill();
}
