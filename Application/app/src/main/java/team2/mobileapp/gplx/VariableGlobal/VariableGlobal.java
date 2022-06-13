package team2.mobileapp.gplx.VariableGlobal;

import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Retrofit.dto.VerificationCode;
import team2.mobileapp.gplx.Retrofit.models.License;
import team2.mobileapp.gplx.view.EditProfileActivity;
import team2.mobileapp.gplx.view.HistoryActivity;
import team2.mobileapp.gplx.view.SelectCategoryActivity;
import team2.mobileapp.gplx.view.TutorialActivity;

public class VariableGlobal {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static String typeCode = "A1";
    public static License license = new License();
    public static String idUser = "";
    public static String PHOTO1 = "https://firebasestorage.googleapis.com/v0/b/upload-image-9b971.appspot.com/o/";
    public static String PHOTO2 = "%2F";
    public static String PHOTO3 = "?alt=media&token=";
    public static String Token = "";
    public static int IdNavigation = R.id.page_home;
    public static ArrayList<String> listMarkGlobal = new ArrayList<>();
    public static VerificationCode verificationCode = new VerificationCode();

    public static void SetNavigationBar(Activity activity) {
        View bottom_bar_container = activity.findViewById(R.id.bottom_bar);
        NavigationBarView navigationBarView = bottom_bar_container.findViewById(R.id.bottomBar);
        navigationBarView.setSelectedItemId(IdNavigation);
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                IdNavigation = item.getItemId();
                switch (item.getItemId()){
                    case R.id.page_home:{
                        Intent intent = new Intent(activity, SelectCategoryActivity.class);
                        activity.startActivity(intent);
                        return true;
                    }
                    case R.id.page_tutorial:{
                        Intent intent = new Intent(activity, TutorialActivity.class);
                        activity.startActivity(intent);
                        return true;
                    }
                    case R.id.page_history:{
                        Intent intent = new Intent(activity, HistoryActivity.class);
                        activity.startActivity(intent);
                        return true;
                    }
                    case R.id.page_profile:{
                        Intent intent = new Intent(activity, EditProfileActivity.class);
                        activity.startActivity(intent);
                        return true;
                    }
                }

                return false;
            }
        });

    }

    public static void showToast(Activity activity, String message) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) activity.findViewById(R.id.toast_root));

        TextView tvToastMessage = (TextView) layout.findViewById(R.id.toast_text);
        tvToastMessage.setText(message);
        Toast toast = new Toast(activity.getApplicationContext());
        toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

}