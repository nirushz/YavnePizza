package il.co.nnz.yavnepizza;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class OrderActivityTrays extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener {

    private Button sendBtn, addTray, removeTray;
    TextView trayNumber, sendMassege;
    LinearLayout trayLayout;
    ListView traysListView;
    ArrayAdapter <String> adapter;
    ArrayList<String> traysArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_trays);

        sendMassege = (TextView) findViewById(R.id.sendMassege);

        sendBtn = (Button) findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(this);


        //trayNumber = (TextView) findViewById(R.id.traysNumber);
        trayLayout = (LinearLayout) findViewById(R.id.traysLayout);

        traysArrayList = new ArrayList<>();
        traysListView = (ListView) findViewById(R.id.traysListView);
        adapter = new ArrayAdapter<String>(this, R.layout.trays_item, R.id.trayAddedItem);
        traysListView.setAdapter(adapter);
        traysListView.setOnItemLongClickListener(this);

        final Spinner spinner = (Spinner) findViewById(R.id.traysSpinner);
        //MySpinner spinner = findViewById(R.id.traysSpinner);

        // Initializing a String Array
        String[] pizzaTypes = new String[]{
                "בחר סוג מגש להזמנה...",
                "רגיל",
                "זיתים",
                "פטריות",
                "בולגרית",
                "בצל",
                "עגבניות"
        };

        final ArrayList<String> pizzaTypesList = new ArrayList<>(Arrays.asList(pizzaTypes));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.spinner_item, pizzaTypesList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }


            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                }
                return view;
            }
        };

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){
                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "מגש " + selectedItemText +" נוסף בהצלחה", Toast.LENGTH_SHORT)
                            .show();
                    traysArrayList.add(selectedItemText);
                    adapter.clear();
                    adapter.addAll(traysArrayList);
                    //traysListView.setAdapter(adapter);
                    Log.d("position", String.valueOf(position));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        //String currentTrays;
        //int newTraysNumber;

        String costumerTrays="";

        for (int i=0;i<adapter.getCount();i++){
            costumerTrays =  adapter.getItem(i).toString()+", "+costumerTrays;
                    }

        if (adapter.getCount()>0) {

            try {

                SmsManager smsManager = SmsManager.getDefault();
                //smsManager.sendTextMessage(phone, null, name+", הזמנתך נשלחה", null, null);
                smsManager.sendTextMessage("0532338842", null, "המגשים שהוזמנו: " + costumerTrays, null, null);
                sendMassege.setText(R.string.send_msg_success);

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        } else {

            sendMassege.setText(R.string.send_msg_error);

        }


        /*
            case R.id.addTray:
                currentTrays = trayNumber.getText().toString();
                newTraysNumber = Integer.parseInt(currentTrays) + 1;
                trayNumber.setText(newTraysNumber + "");

                trayLayout.addView(editText);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    editText.setBackground(getResources().getDrawable(R.drawable.rounded_edit_text));
                    editText.setHint("פטריות/זיתים/רגיל");
                    editText.setTextSize(2);
                    editText.setPadding(10, 10, 10, 10);
                }
                break;

            case R.id.removeTray:
                currentTrays = trayNumber.getText().toString();
                if (Integer.parseInt(currentTrays) > 0) {
                    newTraysNumber = Integer.parseInt(currentTrays) - 1;
                    trayNumber.setText(newTraysNumber + "");

                    trayLayout.removeView(editText);
                    break;
                    */
                }



    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        final int pos=position;

        AlertDialog deleteDialog = new AlertDialog.Builder(this).create();
        deleteDialog.setMessage("Delete Tray?");
        deleteDialog.setButton(deleteDialog.BUTTON_POSITIVE, "DELETE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                traysArrayList.remove(adapter.getItem(pos));
                adapter.remove(adapter.getItem(pos));

            }
        });
        deleteDialog.setButton(deleteDialog.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        deleteDialog.show();

        return false;
    }
}

class MySpinner extends Spinner{

    OnItemSelectedListener listener;

    public MySpinner(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public void setSelection(int position)
    {
        super.setSelection(position);

        if (position == getSelectedItemPosition())
        {
            listener.onItemSelected(null, null, position, 0);
        }
    }

    public void setOnItemSelectedListener(OnItemSelectedListener listener)
    {
        this.listener = listener;
    }
}

