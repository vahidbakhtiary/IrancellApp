package ir.alroid.myirancell.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import ir.alroid.myirancell.ui.login.ActivityLogin;

public class SuccessApi extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        intent = new Intent(context, ActivityLogin.class);
        context.startActivity(intent);
    }
}