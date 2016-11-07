package il.co.nnz.yavnepizza;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivityDetails extends AppCompatActivity implements View.OnClickListener {

    private String name, phone, street, building, apartment, city;
    private EditText editName, editPhone, editStreet, editBuilding, editApatment, editCity;
    private Button saveBtn, addTray, removeTray;
    TextView trayNumber;
    LinearLayout trayLayout;
    SharedPreferences sp;
    //NumberPicker np = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);


        sp = PreferenceManager.getDefaultSharedPreferences(this);
        editName = ((EditText) findViewById(R.id.editName));
        editName.setSelection(editName.getText().length());
        editName.requestFocus();
        editName.setText(sp.getString("name",null));
        editPhone = ((EditText) findViewById(R.id.editPhone));
        editPhone.setText(sp.getString("phone",null));
        editStreet = ((EditText) findViewById(R.id.editStreet));
        editStreet.setText(sp.getString("street",null));
        editBuilding = ((EditText) findViewById(R.id.editBuilding));
        editBuilding.setText(sp.getString("building",null));
        editApatment = ((EditText) findViewById(R.id.editApartment));
        editApatment.setText(sp.getString("apartment",null));
        editCity = ((EditText) findViewById(R.id.editCity));
        editCity.setText(sp.getString("city",null));




        saveBtn = (Button) findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent intent = getIntent();
        String smsPhone = intent.getStringExtra("sms_phone");

        name = ((TextView) findViewById(R.id.editName)).getText().toString();
        phone = ((TextView) findViewById(R.id.editPhone)).getText().toString();
        street = ((TextView) findViewById(R.id.editStreet)).getText().toString();
        building = ((TextView) findViewById(R.id.editBuilding)).getText().toString();
        apartment = ((TextView) findViewById(R.id.editApartment)).getText().toString();
        city = ((TextView) findViewById(R.id.editCity)).getText().toString();

        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name", name);
        editor.putString("phone", phone);
        editor.putString("street", street);
        editor.putString("building", building);
        editor.putString("apartment", apartment);
        editor.putString("city", city);
        editor.apply();

        String costumerDetails = name+", "+phone+", "+street+", "+building+", "+apartment+", "+city;

/*
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phone, null, name+", הזמנתך נשלחה", null, null);
                    smsManager.sendTextMessage("0532338842", null, "הזמנת פיצה מ"+costumerDetails, null, null);
                    smsManager.sendTextMessage(smsPhone, null, "הזמנת פיצה מ"+costumerDetails, null, null);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }*/
                Toast.makeText(OrderActivityDetails.this, "name: "+name+" phone: "+phone, Toast.LENGTH_SHORT).show();
                Log.d("name: ", name);
                Log.d("phone: ", phone);
                Intent goToTraysOrder = new Intent(this, OrderActivityTrays.class);
                startActivity(goToTraysOrder);
        }

    }

    /*
    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        if (newVal==3){
            Toast.makeText(OrderActivityDetails.this, "3 now", Toast.LENGTH_SHORT).show();
        }
    }*/

