/**
 * 
 */
package com.gluonapplication.views;

import java.util.List;

import javafx.beans.property.ObjectProperty;

/**
 * @author yotti
 *
 */
public interface PlatformProvider {

	void sendSMS(String number, String message);

	List<SMSMessage> readSMSs();

	void listenToIncomingSMS();

	ObjectProperty<SMSMessage> messageProperty();

}