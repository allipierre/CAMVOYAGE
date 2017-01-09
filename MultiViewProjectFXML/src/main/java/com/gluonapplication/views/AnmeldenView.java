/**
 * 
 */
package com.gluonapplication.views;
import java.io.IOException;

import com.gluonhq.charm.glisten.mvc.View;

import javafx.fxml.FXMLLoader;

/**
 * @author yotti
 *
 */
public class AnmeldenView {
	private final String name;

	public AnmeldenView(String name) {
		this.name = name;
	}

	public View getView() {
		try {
			View view = FXMLLoader.load(PrimaryView.class.getResource("Anmelden.fxml"));
			view.setName(name);
			return view;
		} catch (IOException e) {
			System.out.println("IOException: " + e);
			return new View(name);
		}
	}

}
