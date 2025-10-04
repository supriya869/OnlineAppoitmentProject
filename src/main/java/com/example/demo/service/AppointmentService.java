package com.example.demo.service;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment bookAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

	/*
	 * public List<Appointment> getAppointmentsByUser(User user) { return
	 * appointmentRepository.findByUser(user); }
	 */
	/*
	 * public List<Appointment> getAllAppointments() { return
	 * appointmentRepository.findAll(); }
	 */
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAppointmentsByEmail(String email) {
        return appointmentRepository.findByUserEmail(email);
    }

    public Appointment updateStatus(Long id, Appointment.Status status) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow();
        appointment.setStatus(status);
        return appointmentRepository.save(appointment);
    }
   

 // AppointmentService.java
    public String cancelByEmailAndDate(String email, LocalDateTime dateTime) {
        List<Appointment> appointments = appointmentRepository.findByUserEmailAndDateTime(email, dateTime);

        if (appointments.isEmpty()) {
            return "No appointment found";
        }

        appointments.forEach(app -> app.setCancelled(true));
        appointmentRepository.saveAll(appointments);

        return "Appointment(s) cancelled successfully";
    }

//    public List<Appointment> getAppointmentsByDoctorId(Long doctorId) {
//        return appointmentRepository.findByDoctorId(doctorId);
//    }
    public boolean isSlotBooked(Long doctorId, LocalDateTime dateTime) {
        // Using the fixed repository method
        List<Appointment> existing = appointmentRepository.findByDoctor_DoctorIdAndDateTime(doctorId, dateTime);
        return !existing.isEmpty();
    }


}
