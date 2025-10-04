package com.example.demo.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.model.Appointment;
import com.example.demo.model.Doctor;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.DoctorRepository;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;

    public DoctorService(DoctorRepository doctorRepository, AppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getDoctorsByDisease(String diseaseName) {
        return doctorRepository.findByDiseases_Name(diseaseName);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId).orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    // Get available slots for a doctor
//    public List<String> getAvailableSlots(Long doctorId) {
//        Doctor doctor = getDoctorById(doctorId);
//        Optional<Appointment> booked = appointmentRepository.findById(doctorId);
//
//        Set<String> bookedTimes = booked.stream()
//            .map(app -> app.getDateTime().toLocalTime().toString())
//            .collect(Collectors.toSet());
//
//        return Arrays.stream(doctor.getAvailableSlots().split(","))
//            .filter(slot -> !bookedTimes.contains(slot))
//            .collect(Collectors.toList());
//    }
    public List<String> getAvailableSlots(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        String slots = doctor.getAvailableSlots();
        if (slots == null || slots.isEmpty()) {
            return new ArrayList<>(); // return empty list instead of error
        }

        return Arrays.asList(slots.split(","));
    }

}
