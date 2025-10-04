package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "DOCTOR")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;
    public String getAvailableSlots() {
		return availableSlots;
	}

	public void setAvailableSlots(String availableSlots) {
		this.availableSlots = availableSlots;
	}

	private String availableSlots;

    

    public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public Set<Disease> getDiseases() {
		return diseases;
	}

	public void setDiseases(Set<Disease> diseases) {
		this.diseases = diseases;
	}

	private String name;
    private String email;
    private String mobileNo;
    private String specialization;

    @ManyToMany
    @JoinTable(
        name = "DOCTOR_DISEASE",
        joinColumns = @JoinColumn(name = "DOCTOR_ID"),
        inverseJoinColumns = @JoinColumn(name = "DISEASE_ID")
    )
    private Set<Disease> diseases;

    // getters and setters
}
