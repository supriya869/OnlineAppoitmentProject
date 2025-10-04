package com.example.demo.service;

import java.util.List;
import java.util.Random;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JavaMailSender mailSender;

    public UserService(UserRepository userRepository, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }
    
    
    
    

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

	/*
	 * public User registerUser(User user) { User existingUser =
	 * userRepository.findByEmail(user.getEmail());
	 * 
	 * if (existingUser != null) { return existingUser; // just return existing user
	 * }
	 * 
	 * String code = String.valueOf(new Random().nextInt(900000) + 100000);
	 * user.setVerificationCode(code); user.setVerified(false); User savedUser =
	 * userRepository.save(user);
	 * 
	 * sendVerificationEmail(savedUser.getEmail(), code); return savedUser; }
	 */
    public String registerUser(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser != null) {
        	if (existingUser.isVerified()) {
        	    return "User already registered and verified! You can login.";
        	}
 else {
                // Resend verification code
                String code = String.valueOf(new Random().nextInt(900000) + 100000);
                existingUser.setVerificationCode(code);
                userRepository.save(existingUser);

                sendVerificationEmail(existingUser.getEmail(), code);
                return "Verification code resent. Please check your email.";
            }
        }

        // New user
        String code = String.valueOf(new Random().nextInt(900000) + 100000);
        user.setVerificationCode(code);
        user.setVerified(false);
        userRepository.save(user);

        sendVerificationEmail(user.getEmail(), code);
        return "Registration successful! Verification code sent to your email.";
    }


    private void sendVerificationEmail(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Verify your email");
        message.setText("Your verification code is: " + code);
        mailSender.send(message);
    }

    public boolean verifyUser(String email, String code) {
        User user = userRepository.findByEmail(email);
        if (user != null && code.equals(user.getVerificationCode())) {
            user.setVerified(true);
            user.setVerificationCode(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
