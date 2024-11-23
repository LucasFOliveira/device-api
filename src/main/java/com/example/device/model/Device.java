package com.example.device.model;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Device {
	
	private @Id
	@GeneratedValue Long id;
	private String name;
	private String brand;
	private Date creation;
	
	/**
	 * @param name
	 * @param brand
	 * @param creation
	 */
	public Device(String name, String brand) {
		this.name = name;
		this.brand = brand;
		this.creation = new Date();
	}

	/**
	 * Abstract constructor
	 */
	public Device() {}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @return the creation
	 */
	public Date getCreation() {
		return creation;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @param creation the creation to set
	 */
	public void setCreation(Date creation) {
		this.creation = creation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(brand, creation, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Device)) {
			return false;
		}
		Device other = (Device) obj;
		return Objects.equals(brand, other.brand) && Objects.equals(creation, other.creation)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Device [id=" + id + ", name=" + name + ", brand=" + brand + ", creation=" + creation + "]";
	}	
}
