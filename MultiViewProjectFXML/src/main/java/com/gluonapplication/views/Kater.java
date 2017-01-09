/**
 * 
 */
package com.gluonapplication.views;

import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * @author yotti
 *
 */
public class Kater {

	private IntegerProperty alter = new SimpleIntegerProperty();

	public final int getAlter() {
		return alter.get();
	}

	public final void setAlter(int value) {
		alter.set(value);
	}

	public IntegerProperty alterProperty() {
		return alter;
	}

}
