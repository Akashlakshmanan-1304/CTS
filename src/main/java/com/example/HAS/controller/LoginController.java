package com.example.HAS.controller;

import com.example.HAS.entity.User;
import com.example.HAS.service.OtpService;
import com.example.HAS.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private OtpService otpService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam String email, Model model, HttpSession session) {
        Optional<User> userOptional = userService.findByEmail(email);
        if (userOptional.isPresent()) {
            String otp = otpService.generateOtp(email);
            // Send OTP via email logic can go here
            model.addAttribute("email", email);
            model.addAttribute("message", "OTP sent to " + email);
            session.setAttribute("email", email);
            return "enter-otp";
        } else {
            model.addAttribute("error", "Email not registered.");
            return "login";
        }
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String otp, HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        if (email == null) return "redirect:/login";

        if (otpService.validateOtp(email, otp)) {
            User user = userService.findByEmail(email).get();
            session.setAttribute("loggedInUser", user);
            otpService.clearOtp(email);

            if ("patient".equalsIgnoreCase(user.getRole())) {
                return "redirect:/appointment";
            } else if ("doctor".equalsIgnoreCase(user.getRole())) {
                return "redirect:/consultation";
            } else {
                model.addAttribute("error", "Unknown role.");
                return "login";
            }
        } else {
            model.addAttribute("error", "Invalid OTP.");
            return "enter-otp";
        }
    }
}
