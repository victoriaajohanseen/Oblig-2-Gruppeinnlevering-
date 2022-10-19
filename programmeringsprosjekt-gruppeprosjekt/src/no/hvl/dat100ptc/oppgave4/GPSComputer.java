package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {
		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	// beregn total distances (i meter)
	public double totalDistance() {
		double distance = 0;
		
		for (int i = 1; i < gpspoints.length; i++) {
			distance += GPSUtils.distance(gpspoints[i], gpspoints[i - 1]);
		}
		return distance;
	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {
		double elevation = 0;
		
		for (int i = 1; i < gpspoints.length; i++) {
			double change = gpspoints[i].getElevation() - gpspoints[i - 1].getElevation();
			if (change > 0) {
				elevation += change;
			}
		}
		return elevation;
	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {
		int totaltime = 0;
		
		for (int i = 1; i < gpspoints.length; i++) {
			int change = gpspoints[i].getTime() - gpspoints[i - 1].getTime();
			totaltime += change;
		}
		return totaltime;
	}
		
	
	// beregn gjennomsnitshastighets mellom hver av gps punktene
	public double[] speeds() {
		double[] speedTab = new double[gpspoints.length - 1];
		
		for (int i = 1; i < gpspoints.length; i++) {
			speedTab[i - 1] = GPSUtils.speed(gpspoints[i], gpspoints[i - 1]);
		}
		return speedTab;
	}
	
	public double maxSpeed() {
		double maxspeed = 0;
		
		for (int i = 1; i < gpspoints.length; i++) {
			maxspeed = GPSUtils.findMax(speeds());
		}
		return maxspeed;
	}

	public double averageSpeed() {
		double average = 0;
		
		average = ((totalDistance() / totalTime() * 60 * 60) /1000);
		return average;
	}

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {
		double met = 0;		
		double speedmph = speed * MS;
		
		if (speedmph > 20) {
			met = 16.0;
		} else if (speedmph > 16) {
			met = 12.0;
		} else if (speedmph > 14) {
			met = 10.0;
		} else if (speedmph > 12) {
			met = 8.0;
		} else if (speedmph > 10) {
			met = 6.0;
		} else {
			met = 4.0;
		}
		
		double kcal = weight * (speedmph / 3600) * met;
		return kcal;
	}

	public double totalKcal(double weight) {
		double totalkcal = 0;
		
		for (int i = 1; i < gpspoints.length; i++) {
			double speed = (GPSUtils.speed(gpspoints[i - 1], gpspoints[i]));
			int time = Math.abs(gpspoints[i].getTime() - gpspoints[i - 1].getTime());
			totalkcal += kcal(weight, time, speed);
		}
		return totalkcal;
	}
	
	public static double WEIGHT = 80.0;
	
	public void displayStatistics() {
		
		System.out.println("==============================================");
        
		System.out.println(String.format("%-15s", "Total time") + ":" + String.format("%15s", GPSUtils.formatTime(totalTime())));
        System.out.println(String.format("%-15s", "Total distance") + ":" + String.format("%15s", GPSUtils.formatDouble(totalDistance()/1000)+ " km" ));
        System.out.println(String.format("%-15s", "Total elevation") + ":" + String.format("%15s", GPSUtils.formatDouble(totalElevation())+ " m"));
        System.out.println(String.format("%-15s", "Max speed") + ":" + String.format("%15s", GPSUtils.formatDouble (maxSpeed())+ " km/t"));
        System.out.println(String.format("%-15s", "Average speed") + ":" + String.format("%15s", GPSUtils.formatDouble(averageSpeed())+" km/t"));
        System.out.println(String.format("%-15s", "Energy") + ":" + String.format("%15s", GPSUtils.formatDouble(totalKcal(WEIGHT)) + " kcal"));
        
        System.out.println("==============================================");
	}
}
