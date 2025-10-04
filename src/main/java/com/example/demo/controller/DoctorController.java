package com.example.demo.controller;


import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Appointment;
import com.example.demo.model.Doctor;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.DoctorService;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "http://localhost:3000")
public class DoctorController {
    private final DoctorService doctorService;


    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        return doctorService.addDoctor(doctor);
    }

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/disease/{name}")
    public List<Doctor> getDoctorsByDisease(@PathVariable String name) {
        return doctorService.getDoctorsByDisease(name);
    }
   
    @GetMapping("/{doctorId}/available-slots")
    public List<String> getAvailableSlots(@PathVariable Long doctorId) {
        return doctorService.getAvailableSlots(doctorId);
    }
    
}

