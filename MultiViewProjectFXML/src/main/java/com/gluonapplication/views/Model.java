/**
 * 
 */
package com.gluonapplication.views;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author yotti
 *
 */
public class Model {

	private final StringProperty text = new SimpleStringProperty();

	public final void setText(String value) {
		text.set(value);
	}

	public final String getText() {
		return text.get();
	}

	public final StringProperty textProperty() {
		return text;
	}

}
