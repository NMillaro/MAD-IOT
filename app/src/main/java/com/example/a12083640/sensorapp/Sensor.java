package com.example.a12083640.sensorapp;

public class Sensor {
	private String sensorName;
	private String sensorValue;
	
	public Sensor(String sensorName, String sensorValue) {
		super();
		this.sensorName = sensorName;
		this.sensorValue = sensorValue;
	}
	
	public String getSensorName() {
		return sensorName;
	}
	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}
	public String getSensorValue() {
		return sensorValue;
	}
	public void setSensorValue(String sensorValue) {
		this.sensorValue = sensorValue;
	}

	@Override
	public String toString() {
		return "Sensor [sensorName=" + sensorName + ", sensorValue=" + sensorValue + "]";
	}
	
}
