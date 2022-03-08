package ir.alroid.myirancell.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import ir.alroid.myirancell.MainActivity;
import ir.alroid.myirancell.utils.AppConstance;

import static android.content.Context.MODE_PRIVATE;

public class ViewModelLogin extends AndroidViewModel {

    private static final String TAG = ViewModelLogin.class.getSimpleName();

    // Constructor
    public ViewModelLogin(@NonNull Application application) {
        super(application);
    }

    public void goToMainActivity(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    public boolean isValidEmail(String email, EditText etEmail) {
        if (email.lastIndexOf('@') <= 0
                || !email.contains(".")
                || email.lastIndexOf('.') < email.lastIndexOf('@')
                || email.split("@").length > 2) {

            etEmail.setError("لطفا ایمیل صحیح را وارد کنید. مانند:\n\n al.allahverdi@gmail.com");
            etEmail.requestFocus();
            return false;
        }

        return true;
    }

    public boolean isValidEmail(String email) {
        if (email == null
                || email.lastIndexOf('@') <= 0
                || !email.contains(".")
                || email.lastIndexOf('.') < email.lastIndexOf('@')
                || email.split("@").length > 2
        ) {

            return false;
        }

        return true;
    }

    public boolean isValidPassword(String password, EditText etPassword) {
        if (password.length() < 8) {
            etPassword.setError("کلمه عبور  باید حداقل بیشتر از هشت کاراکتر باشد.");
            etPassword.requestFocus();
            return false;
        }

        return true;
    }

    public boolean isValidPassword(String password) {
        if (password == null
                || password.isEmpty()
                || password.length() < 8) {

            return false;
        }

        return true;
    }

    public void onLogin(Context context, EditText email, EditText password) {

        if (email.getText() == null
                || password.getText() == null
                || !isValidEmail(email.getText().toString(), email)
                || !isValidPassword(password.getText().toString(), password)) {

            Log.e(TAG, "onRegister USER: >>> INVALID");

        } else {

            // set data in shared preference:
            SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstance.SHARED_PREF_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(AppConstance.SHARED_PREF_KEY, email + " " + password);
            editor.apply();

            goToMainActivity(context);

        }

    }

    public void checkRegister(Context context) {
        String result = getSharedPreference(context, AppConstance.SHARED_PREF_KEY);
        if (result != null) {
            goToMainActivity(context);
        }
    }

    public String getSharedPreference(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstance.SHARED_PREF_NAME, MODE_PRIVATE);
        if (!sharedPreferences.contains(key)) {
            return null;
        }
        return sharedPreferences.getString(key, null);
    }

}
