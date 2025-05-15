package com.example.HAS.controller;

import com.example.HAS.entity.User;
import com.example.HAS.entity.Appointment;
import com.example.HAS.repository.UserRepository;
import com.example.HAS.repository.AppointmentRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.time.LocalDate;
import java.util.*;

@Controller
public class AppointmentController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping("/appointment")
    public String showDoctorList(Model model,HttpSession session) {
        User loggedInUser= (User) session.getAttribute("loggedInUser");

        if(loggedInUser==null){
            return "redirect:/login";
        }
        List<User> doctors = userRepository.findByRole("doctor");
        model.addAttribute("doctors", doctors);
        model.addAttribute("currentDate", LocalDate.now());
        model.addAttribute("currentUser",loggedInUser);
        return "appointment";
    }
    @GetMapping("/appointment/{doctorName}")
    public String getDoctorAppointmentPage(@PathVariable String doctorName,
                                           @RequestParam String date,
                                           Model model,HttpSession session) {
        User doctor = userRepository.findByNameAndRole(doctorName, "doctor");
        model.addAttribute("doctor", doctor);
        session.setAttribute("selectedDoctorId",doctor.getUserID());
        model.addAttribute("date", date);
        session.setAttribute("selectedDate",date);

        // Generate time slots
        List<String> timeSlots = generateTimeSlots();
        model.addAttribute("timeSlots", timeSlots);

        return "appointment-details";
    }



    @PostMapping("/appointment/book")
    public String bookAppointment(@RequestParam("timeSlot") String timeSlot,
                                  HttpSession session, Model model) {
        Long doctorId = (Long) session.getAttribute("selectedDoctorId");
        String dateStr = (String) session.getAttribute("selectedDate");
        User patient = (User) session.getAttribute("loggedInUser");

        if (doctorId == null || dateStr == null || patient == null) {
            return "redirect:/appointment";
        }

        Appointment appointment = new Appointment();
        appointment.setDoctorId(doctorId);
        appointment.setPatientId(patient.getUserID());
        appointment.setDate(LocalDate.parse(dateStr));
        appointment.setTimeSlot(timeSlot);

        appointmentRepository.save(appointment);

        model.addAttribute("message", "Appointment successfully booked!");
        return "appointment_success";
    }

    private List<String> generateTimeSlots() {
        List<String> slots = new ArrayList<>();
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(18, 0);

        while (start.isBefore(end)) {
            if (start.isBefore(LocalTime.of(13, 0)) || start.isAfter(LocalTime.of(14, 0))) {
                slots.add(start.toString());
            }
            start = start.plusMinutes(30);
        }
        return slots;
    }
}