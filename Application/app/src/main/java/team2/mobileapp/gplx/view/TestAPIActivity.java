package team2.mobileapp.gplx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

//import team2.mobileapp.gplx.callback.VolleyResponseListener;
import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Volley.model.License;
import team2.mobileapp.gplx.Volley.service.LicenseService;

public class TestAPIActivity extends AppCompatActivity {

    private Button btn_getAll, btn_add, btn_update, btn_delete;
    private EditText et_id, et_name, et_status, et_description;
    private ListView lv_licenseShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_api);

        btn_getAll = findViewById(R.id.btn_get_all_license);
        btn_add = findViewById(R.id.btn_post_license);
        btn_update = findViewById(R.id.btn_update_license);
        btn_delete = findViewById(R.id.btn_delete_license);

        et_id = findViewById(R.id.et_id_license);
        et_name = findViewById(R.id.et_name_license);
        et_description = findViewById(R.id.et_description_license);
        et_status = findViewById(R.id.et_status_license);

        lv_licenseShow = findViewById(R.id.lv_licenses);

        final LicenseService licenseService = new LicenseService(TestAPIActivity.this);

        GetAll(licenseService);

        Add(licenseService);

        Update(licenseService);

        Delete(licenseService);
    }

    private void ResetInput() {
        et_id.setText("");
        et_name.setText("");
        et_status.setText("");
        et_description.setText("");
    }

    private void Delete(LicenseService licenseService) {
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_id.getText().toString().isEmpty()) Toast.makeText(TestAPIActivity.this, "Please enter id", Toast.LENGTH_LONG).show();
                else{
                    licenseService.Delete(et_id.getText().toString());
                }
                ResetInput();
            }
        });
    }

    private void Update(LicenseService licenseService) {
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(TestAPIActivity.this, "You clicked 'Update' button",Toast.LENGTH_LONG).show();
                if(et_id.getText().toString().isEmpty()) Toast.makeText(TestAPIActivity.this, "Please enter id", Toast.LENGTH_LONG).show();
                else if(et_name.getText().toString().isEmpty()) Toast.makeText(TestAPIActivity.this, "Please enter name", Toast.LENGTH_LONG).show();
                else if(et_status.getText().toString().isEmpty()) Toast.makeText(TestAPIActivity.this, "Please enter status", Toast.LENGTH_LONG).show();
                else if(et_description.getText().toString().isEmpty()) Toast.makeText(TestAPIActivity.this, "Please enter description", Toast.LENGTH_LONG).show();
                else{
//                    Toast.makeText(TestAPIActivity.this, "Hooray", Toast.LENGTH_LONG).show();
                    licenseService.Update(et_id.getText().toString(),et_name.getText().toString(),et_status.getText().toString(),et_description.getText().toString());
                }
                ResetInput();
            }
        });
    }

    private void Add(LicenseService licenseService) {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!et_id.getText().toString().isEmpty()) Toast.makeText(TestAPIActivity.this, "Please dont enter id input", Toast.LENGTH_LONG).show();
                else if(et_name.getText().toString().isEmpty()) Toast.makeText(TestAPIActivity.this, "Please enter name", Toast.LENGTH_LONG).show();
                else if(et_status.getText().toString().isEmpty()) Toast.makeText(TestAPIActivity.this, "Please enter status", Toast.LENGTH_LONG).show();
                else if(et_description.getText().toString().isEmpty()) Toast.makeText(TestAPIActivity.this, "Please enter description", Toast.LENGTH_LONG).show();
                else{
                    licenseService.Add(et_name.getText().toString(),et_status.getText().toString(),et_description.getText().toString());
                }
                ResetInput();
            }
        });
    }

    private void GetAll(LicenseService licenseService) {
        btn_getAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TestAPIActivity.this, "You clicked 'Get All' button", Toast.LENGTH_SHORT).show();
                licenseService.GetAll(new LicenseService.GetALLLicenseCallBack() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(TestAPIActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<License> licenses) {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(TestAPIActivity.this, android.R.layout.simple_list_item_1, licenses);
                        lv_licenseShow.setAdapter(arrayAdapter);
                    }
                });
                ResetInput();
            }
        });
    }
}