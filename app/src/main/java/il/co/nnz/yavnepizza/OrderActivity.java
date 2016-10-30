package il.co.nnz.yavnepizza;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener, NumberPicker.OnValueChangeListener {

    String name, phone;
    Button sendBtn;
    NumberPicker np = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        np = (NumberPicker) findViewById(R.id.numberPicker);
        np.setValue(1);
        np.setMinValue(1);
        np.setMaxValue(20);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);

        findViewById(R.id.sendBtn).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        try {
            name = ((TextView) findViewById(R.id.editName)).getText().toString();
            phone = ((TextView) findViewById(R.id.editPhone)).getText().toString();
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, name+", הזמנתך נשלחה", null, null);
            smsManager.sendTextMessage("0532338842", null, "הזמנת פיצה מ"+name, null, null);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        Toast.makeText(OrderActivity.this, "name: "+name+" phone: "+phone, Toast.LENGTH_SHORT).show();
        Log.d("name: ", name);
        Log.d("phone: ", phone);
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        if (newVal==3){
            Toast.makeText(OrderActivity.this, "3 now", Toast.LENGTH_SHORT).show();
        }
    }
}
