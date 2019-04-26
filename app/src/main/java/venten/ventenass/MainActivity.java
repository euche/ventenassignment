package venten.ventenass;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {


    TextView dt,msg;


    String message;



    private static MainActivity inst;


    private static final int READ_SMS_PERMISSIONS_REQUEST = 1;


    private long stime;



    public static MainActivity instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        dt = findViewById(R.id.dateandtime);
        msg = findViewById(R.id.txtmessage);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            getPermissionToReadSMS();
        }






        try{


            Bundle b = getIntent().getExtras();

            message = b.getString("message");


            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm", getResources().getConfiguration().locale);
            df.setTimeZone(TimeZone.getDefault());

            String time = " DT "+ " "+ df.format(new Date(stime));


            dt.setText(time);

            msg.setText(message);





        }catch(NullPointerException e){





        }







    }






//    public String getNdatetimeFormat(Context c){
//
//        //SimpleDateFormat class is imported, used to format date and time //SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
//        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm", c.getResources().getConfiguration().locale);
//        df.setTimeZone(TimeZone.getDefault());
//        return df.format(new Date(stime));
//
//
//
//    }







    @TargetApi(23)
    public void getPermissionToReadSMS() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_SMS)) {
                Toast.makeText(this, "Please allow permission!", Toast.LENGTH_SHORT).show();
            }
            requestPermissions(new String[]{Manifest.permission.READ_SMS},
                    READ_SMS_PERMISSIONS_REQUEST);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        // Make sure it's our original READ_CONTACTS request
        if (requestCode == READ_SMS_PERMISSIONS_REQUEST) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Read SMS permission granted", Toast.LENGTH_SHORT).show();
//                refreshSmsInbox();
            } else {
                Toast.makeText(this, "Read SMS permission denied", Toast.LENGTH_SHORT).show();
            }

        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }





}
