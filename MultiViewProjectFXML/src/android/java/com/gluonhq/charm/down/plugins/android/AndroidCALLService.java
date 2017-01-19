/**
 * 
 */

/**
 * @author yotti
 *
 */

package com.gluonhq.charm.down.plugins.android;



import com.gluonhq.charm.down.plugins.CALLService;
import com.gluonhq.charm.down.plugins.SMSService;

import android.content.Intent;
import android.net.Uri;
import javafxports.android.FXActivity;

public class AndroidCALLService implements CALLService {

    public void callNUMBER(String number) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);         
        callIntent.setData(Uri.parse("tel:999999999"));
        FXActivity.getInstance().startActivity(callIntent);
    }

	

}