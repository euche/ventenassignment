package venten.ventenass;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class customSMSreader extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {



        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){

            Bundle b = intent.getExtras();

            SmsMessage message = null;

//            String phone_num
            String msg_fromm;



            if(b!= null){


                try{

                    Object[] pdus = (Object[]) b.get("pdus");

                    for(int i = 0; i < pdus.length;i++){


                        message = SmsMessage.createFromPdu((byte[]) pdus[i]);

                        String phone_number = message.getDisplayOriginatingAddress();

                        String message_1 = message.getDisplayMessageBody();

                        msg_fromm = message_1.replaceAll("\\s","");

                        Intent next = new Intent(context, MainActivity.class);
                        next.putExtra("message", msg_fromm );           //message.getMessageBody()

//                        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        next.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        context.startActivity(next);



//                        MainActivity inst = MainActivity.instance();
//                        inst.updateInbox(smsMessageStr);







                       }












                }catch(Exception e){


                    Log.e("Results",e.getMessage());

                }


            }




        }






    }















}
