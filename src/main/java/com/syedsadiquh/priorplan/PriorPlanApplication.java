package com.syedsadiquh.priorplan;

import com.syedsadiquh.priorplan.ui.LoginSignupGUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class PriorPlanApplication {

	public static void main(String[] args) {

		System.setProperty("java.awt.headless", "false");

		SpringApplication.run(PriorPlanApplication.class, args);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new LoginSignupGUI().setVisible(true);
			}
		});
	}

}
