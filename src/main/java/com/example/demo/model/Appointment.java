package com.example.demo.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="appointments")
public class Appointment {
	
	@ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;   // <--- this is important
  
	/*
	 * public String getUserName() { return userName; }
	 * 
	 * public void setUserName(String userName) { this.userName = userName; }
	 * 
	 * public String getUserEmail() { return userEmail; }
	 * 
	 * public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
	 */

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	/*
	 * private String userName; private String userEmail;
	 */
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	private String patientEmail;  // <-- must exist

    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    public enum Status { PENDING, APPROVED, REJECTED }
    public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	private boolean cancelled = false; // ✅ new field


    // Getters & Setters
}
