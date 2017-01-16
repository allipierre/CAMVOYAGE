/**
 * 
 */
package com.gluonapplication;

import com.gluonhq.charm.down.DefaultServiceFactory;

/**
 * @author yotti
 *
 */
public class SMSServiceFactory extends DefaultServiceFactory<SMSService> {

    public SMSServiceFactory() {
    	
        super(SMSService.class);
    }

}