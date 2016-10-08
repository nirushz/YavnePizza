package il.co.nnz.yavnepizza;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {

    String name, phone;
    Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);



        findViewById(R.id.sendBtn).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        try {
            name = ((TextView) findViewById(R.id.editName)).getText().toString();
            phone = ((TextView) findViewById(R.id.editPhone)).getText().toString();
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, "הזמנת פיצה מ"+name, null, null);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        Toast.makeText(OrderActivity.this, "name: "+name+" phone: "+phone, Toast.LENGTH_SHORT).show();
        Log.d("name: ", name);
        Log.d("phone: ", phone);
    }
}
