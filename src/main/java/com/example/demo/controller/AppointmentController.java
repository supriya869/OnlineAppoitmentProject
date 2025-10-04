package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Appointment;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final UserService userService;

    public AppointmentController(AppointmentService appointmentService, UserService userService) {
        this.appointmentService = appointmentService;
        this.userService = userService;
    }

//    @PostMapping
//    public Appointment bookAppointment(@RequestBody Appointment appointment) {
//        return appointmentService.bookAppointment(appointment);
//    }
   
    @PostMapping
    public Object bookAppointment(@RequestBody Appointment appointment) {
        try {
            return appointmentService.bookAppointment(appointment);
        } catch (RuntimeException e) {
            return Map.of("error", e.getMessage());
        }
    }

    
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

	
//    public List<Appointment> getAllAppointments() {
//        return appointmentRepository.findAll();
//    }

	 
    @GetMapping("/user/{email}")
    public List<Appointment> getAppointmentsByEmail(@PathVariable String email) {
        return appointmentService.getAppointmentsByEmail(email);
    }


    @PutMapping("/{id}/status")
    public Appointment updateStatus(@PathVariable Long id, @RequestParam Appointment.Status status) {
        return appointmentService.updateStatus(id, status);
    }
    @PostMapping("/cancel")
    public String cancelByEmailAndDate(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String dateTimeStr = body.get("dateTime");

        // ISO-8601 format: "2025-09-03T16:13:00"
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr);

        return appointmentService.cancelByEmailAndDate(email, dateTime);
    }

    @PostMapping("/check-slot")
    public Object checkSlotAvailability(@RequestBody Map<String, String> body) {
        try {
            Long doctorId = Long.parseLong(body.get("doctorId"));
            LocalDateTime dateTime = LocalDateTime.parse(body.get("dateTime"));

            boolean isBooked = appointmentService.isSlotBooked(doctorId, dateTime);

            if (isBooked) {
                return Map.of("status", "Already booked");
            } else {
                return Map.of("status", "Available");
            }
        } catch (Exception e) {
            return Map.of("status", "Error", "message", e.getMessage());
        }
    }


}

