package com.example.demo.repository;



import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Appointment;
import com.example.demo.model.User;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	List<Appointment> findByUserEmailAndDateTime(String email, LocalDateTime dateTime);
	
	/*
	 * List<Appointment> findByUser(User user); List<Appointment>
	 * findByPatientEmail(String email);
	 */
	List<Appointment> findByUserEmail(String email);
    List<Appointment> findByPatientEmailAndDateTime(String email, LocalDateTime dateTime);

	List<Appointment> findByDoctor_DoctorIdAndDateTime(Long doctorId, LocalDateTime dateTime);




}


