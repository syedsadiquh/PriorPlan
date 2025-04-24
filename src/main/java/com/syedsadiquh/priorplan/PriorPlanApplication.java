package com.syedsadiquh.priorplan;

import com.syedsadiquh.priorplan.models.User;
import com.syedsadiquh.priorplan.repository.UserRepository;
import com.syedsadiquh.priorplan.ui.LoginSignupGUI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.swing.*;

@SpringBootApplication
public class PriorPlanApplication {

	private static ApplicationContext ctx;

	public static void main(String[] args) {

		System.setProperty("java.awt.headless", "false");

		ctx = SpringApplication.run(PriorPlanApplication.class, args);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new LoginSignupGUI().setVisible(true);
			}
		});
	}

	public static ApplicationContext getApplicationContext() {
		return  ctx;
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(UserRepository userRepository) {
//		return args -> {
//			var user = User.builder().name("Syed Sadiqu").username("syed").password("root").build();
//			userRepository.save(user);
//		};
//	}

}
