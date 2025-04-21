package com.batchkatas.batchkatas.model;

public class SupplierDto {
	private String registrationNumber;
	private String lastName;
	private String firstName;

	private String position;
	private Long birthYear;
	private Long debutYear;

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Long getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(Long birthYear) {
		this.birthYear = birthYear;
	}

	public Long getDebutYear() {
		return debutYear;
	}

	public void setDebutYear(Long debutYear) {
		this.debutYear = debutYear;
	}

	@Override
	public String toString() {
		return "SupplierDto{" +
				"registrationNumber='" + registrationNumber + '\'' +
				", lastName='" + lastName + '\'' +
				", firstName='" + firstName + '\'' +
				", position='" + position + '\'' +
				", birthYear=" + birthYear +
				", debutYear=" + debutYear +
				'}';
	}
}
