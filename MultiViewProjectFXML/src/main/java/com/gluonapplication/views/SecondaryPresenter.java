package com.gluonapplication.views;

import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.BarcodeScanService;
import com.gluonhq.charm.down.plugins.BarcodeScanServiceFactory;
import com.gluonhq.charm.down.plugins.BatteryService;
import com.gluonhq.charm.down.plugins.BrowserService;
import com.gluonhq.charm.down.plugins.CALLService;
import com.gluonhq.charm.down.plugins.DialerService;
import com.gluonhq.charm.down.plugins.PicturesService;
import com.gluonhq.charm.down.plugins.Position;
import com.gluonhq.charm.down.plugins.PositionService;
import com.gluonhq.charm.down.plugins.SMSService;
import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.layout.layer.FloatingActionButton;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import com.gluonapplication.GluonApplication;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class SecondaryPresenter {

	@FXML
	private View secondary;
	@FXML
	private Button btn, call, BS;
	@FXML
	private TextField textField;
	@FXML
	ImageView imageView;
	@FXML
	Label LS4;

	public void initialize() throws IOException, URISyntaxException {
		secondary.setShowTransitionFactory(BounceInRightTransition::new);

		secondary.getLayers().add(
				new FloatingActionButton(MaterialDesignIcon.INFO.text, e -> System.out.println("Info")).getLayer());

		secondary.showingProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue) {
				AppBar appBar = MobileApplication.getInstance().getAppBar();
				appBar.setNavIcon(MaterialDesignIcon.MENU
						.button(e -> MobileApplication.getInstance().showLayer(GluonApplication.MENU_LAYER)));
				appBar.setTitleText("Secondary");
				appBar.getActionItems().add(MaterialDesignIcon.FAVORITE.button(e -> System.out.println("Favorite")));
				textField.setText("004917680501750");
				Services.get(SMSService.class).ifPresent(s -> btn.setOnAction(e -> s.sendSMS(textField.getText())));
			}
		});
		textField.setText("004917680501750");
		Services.get(SMSService.class).ifPresent(s -> btn.setOnAction(e -> s.sendSMS(textField.getText())));
		Services.get(CALLService.class).ifPresent(s -> call.setOnAction(e -> s.callNUMBER(textField.getText())));

	}

	public void browser() {
		Services.get(BrowserService.class).ifPresent(service -> {
			try {
				service.launchExternalBrowser("https://github.com/");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}

	public void barCode() {
		Services.get(BarcodeScanService.class).ifPresent(service -> {
			BarcodeScanService barcodeScanService = (BarcodeScanService) new BarcodeScanServiceFactory();
			Optional<String> barcode = barcodeScanService.scan();
			barcode.ifPresent(barcodeValue -> LS4.setText(barcodeValue));
			
		});
	}

	@FXML
	public void batterie() {
		Services.get(BatteryService.class).ifPresent(service -> {
			float batteryLevel = service.getBatteryLevel();
			boolean pluggedIn = service.isPluggedIn();
			LS4.setText(batteryLevel + "");
		});

	}

	public void call() {
		Services.get(DialerService.class).ifPresent(service -> {
			service.call("+32987123456");
		});
	}

	@FXML
	public void picture() {

		Services.get(PicturesService.class).ifPresent(service -> {
			service.takePhoto(true).ifPresent(image -> imageView.setImage(image));
		});
	}

	@FXML
	public void position() {
		Services.get(PositionService.class).ifPresent(service -> {
			Position position = service.getPosition();
			System.out.printf("Current position: %.5f, %.5f", position.getLatitude(), position.getLongitude());
			LS4.setText(position.getLatitude() + " /" + position.getLongitude() + "");
		});

	}
}
