/**
 * 
 */

/**
 * @author yotti
 *
 */

package com.gluonhq.charm.down.plugins.android;


import com.gluonhq.charm.down.plugins.SMSService;

import android.content.Intent;
import android.net.Uri;
import javafxports.android.FXActivity;

public class AndroidSMSService implements SMSService {

    public void sendSMS(String number) {
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);         
        smsIntent.setData(Uri.parse("sms:"));
        smsIntent.putExtra("address", number);
        FXActivity.getInstance().startActivity(smsIntent);
    }

}