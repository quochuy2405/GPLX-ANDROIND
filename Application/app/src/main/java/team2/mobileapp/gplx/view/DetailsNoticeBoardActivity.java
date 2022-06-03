package team2.mobileapp.gplx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import  team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Retrofit.callbacks.TrafficSignCallBackListener;
import team2.mobileapp.gplx.Retrofit.controllers.TrafficSignController;
import team2.mobileapp.gplx.Volley.model.TrafficSign;

public class DetailsNoticeBoardActivity extends AppCompatActivity implements TrafficSignCallBackListener {
    private TrafficSignController trafficSignController;
    private TextView tvTitle,tvDescription;
    private ImageView ivPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_notice_board);

        tvTitle = (TextView) findViewById(R.id.tv_title_board_details);
        tvDescription = (TextView) findViewById(R.id.tv_description_board_details);
        ivPhoto = (ImageView) findViewById(R.id.iv_image_board_details);
        trafficSignController = new TrafficSignController(this);
        String Id = getIntent().getStringExtra("ID");
        trafficSignController.startFetching(Id);

    }
    @Override
    public void onFetchProgress(TrafficSign trafficSign) {
        tvTitle.setText(trafficSign.getName());
        tvDescription.setText(trafficSign.getDescription());
        String uri =trafficSign.getPhoto().substring(0, trafficSign.getPhoto().length() - 4);
        int imageResource = getResources().getIdentifier(uri, "drawable", getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        ivPhoto.setImageDrawable(res);

    }

    @Override
    public void onFetchComplete(String message) {

    }
}