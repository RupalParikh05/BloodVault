package in.specialsoft.bloodvault;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import in.specialsoft.bloodvault.DonorDetails.DonorDetails;
import io.paperdb.Paper;

public class MyDetailsActivity extends AppCompatActivity {

    Spinner myDetailsSpinner;
    String[] bloodGroups = {"Select","A-","A+","AB-","AB+","B-","B+","O-","O+"};

    EditText etDetailName,etDetailMobile,etDetailAddress,etDetailCity;
    CheckBox checkboxDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_details);

        Paper.init(this);
        myDetailsSpinner = findViewById(R.id.myDetailsSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MyDetailsActivity.this, android.R.layout.simple_list_item_1,bloodGroups);
        myDetailsSpinner.setAdapter(adapter);

        etDetailName = findViewById(R.id.etDetailName);
        etDetailMobile = findViewById(R.id.etDetailMobile);
        etDetailAddress = findViewById(R.id.etDetailAddress);
        etDetailCity = findViewById(R.id.etDetailCity);

        checkboxDetails = findViewById(R.id.checkboxDetails);

        init();

    }

    private void init() {
        String name,phone,address,city,bloodGroup;
        int gender,available;

        name = Paper.book().read(DonorDetails.UserNameKey);
        phone = Paper.book().read(DonorDetails.UserPhoneKey);
        address = Paper.book().read(DonorDetails.UserAddressKey);
        city = Paper.book().read(DonorDetails.UserCityKey);
        bloodGroup = Paper.book().read(DonorDetails.UserBloodGroupKey);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.search_donor:
                Intent mainIntent = new Intent(MyDetailsActivity.this,MainActivity.class);
                startActivity(mainIntent);
                finish();
                break;
            case R.id.blood_bank:
                Intent bloodBankIntent = new Intent(MyDetailsActivity.this,BloodBankActivity.class);
                startActivity(bloodBankIntent);
                finish();
                break;
            case R.id.my_details:
                break;
            case R.id.logout:
                Paper.book().destroy();
                Intent logoutIntent = new Intent(MyDetailsActivity.this,LoginActivity.class);
                startActivity(logoutIntent);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}