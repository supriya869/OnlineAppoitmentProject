package com.example.demo.config;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import com.example.demo.model.User;
import com.example.demo.model.Doctor;
import com.example.demo.model.Appointment;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.AppointmentRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepo;
    private final DoctorRepository doctorRepo;
    private final AppointmentRepository appointmentRepo;

    public DataInitializer(UserRepository userRepo, DoctorRepository doctorRepo, AppointmentRepository appointmentRepo) {
        this.userRepo = userRepo;
        this.doctorRepo = doctorRepo;
        this.appointmentRepo = appointmentRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Initializing default data...");

        User user = new User();
        user.setName("Supriya");
        user.setEmail("supriya@example.com");
        userRepo.save(user);

        Doctor doctor = new Doctor();
        doctor.setName("Dr. Rao");
       // doctor.setDiseases("Cardiology");
        doctorRepo.save(doctor);

        Appointment appointment = new Appointment();
        appointment.setUser(user);
        appointment.setDoctor(doctor);
        appointment.setDateTime(LocalDateTime.parse("2025-09-20T10:00:00"));
        appointment.setStatus(Appointment.Status.APPROVED);
        appointment.setCancelled(false);
        appointmentRepo.save(appointment);

        System.out.println("Default data initialized successfully!");
    }
}

