package io.arha.ticketsvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import io.arha.ticketsvc.entity.RegisterUser;
import io.arha.ticketsvc.props.EmailProps;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private EmailProps emailProps;

	private JavaMailSenderImpl prepareJavaMailSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost(emailProps.getHost());
		javaMailSender.setPort(emailProps.getPort());
		javaMailSender.setUsername(emailProps.getUsername());
		javaMailSender.setPassword(emailProps.getPassword());
		return javaMailSender;
	}

	@Override
	public void sendRegisterUserEmail(RegisterUser registerUser) {

		JavaMailSenderImpl javaMailSender = prepareJavaMailSender();

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(registerUser.getUsername());
		message.setFrom(emailProps.getFrom());
		message.setSubject("Ticket Service | Verification email ");
		String emailText = String.format(
				"Hi %s,\nGreeting from Ticket Service IT TEAM.\nPlease click on link to complete your varification.\n%s",
				registerUser.getName(), "http://localhost:8081/register/verify/" + registerUser.getRegisterLinkId());

		message.setText(emailText);

		javaMailSender.send(message);

	}

}
