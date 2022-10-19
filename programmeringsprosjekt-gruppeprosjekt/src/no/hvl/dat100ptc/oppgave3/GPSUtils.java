package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {
		double max; 
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		return max;
	}

	public static double findMin(double[] da) {
		double min;
		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		return min;
	}
	
	public static double[] getLatitudes(GPSPoint[] gpspoints) {
		double[] latitudes = new double[gpspoints.length];
		
		for (int i = 0; i < gpspoints.length; i++) {
			latitudes[i] = gpspoints[i].getLatitude();
		}
		return latitudes;
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {
		double[] longitudes = new double[gpspoints.length];
		
		for (int i = 0; i < gpspoints.length; i++) {
			longitudes[i] = gpspoints[i].getLongitude();
		}
		return longitudes;
	}
	
	// ekstrametode
	public static double[] getElevations(GPSPoint[] gpspoints) {         
		double[] elevation = new double[gpspoints.length];                  
		
		for (int i = 0; i < gpspoints.length - 1; i++) {             
			double change = gpspoints[i+1].getElevation() - gpspoints[i].getElevation();             
			elevation[i] = change > 0 ? change : 0;         
		}                  
		return elevation;     
	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		double d, latitude1, longitude1, latitude2, longitude2;

		latitude1 = (gpspoint1.getLatitude());
		longitude1 = (gpspoint1.getLongitude());
		
		latitude2 = (gpspoint2.getLatitude());
		longitude2 = (gpspoint2.getLongitude());
		
		double lat = Math.toRadians(latitude2 - latitude1);
		double lo = Math.toRadians(longitude2 - longitude1);
		
		double a = (Math.pow(Math.sin(lat/2), 2)
				+ (Math.cos(Math.toRadians(latitude1)))
				* (Math.cos(Math.toRadians(latitude2)))
				* (Math.pow(Math.sin(lo/2), 2)));
		
		double c = (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)));
		
		d = (R * c);
		// System.out.println(d);
		return d;
		
	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		int secs = Math.abs(gpspoint2.getTime() - gpspoint1.getTime());
		double speed = ((distance(gpspoint1, gpspoint2)) / secs) * 3.6;
		return speed;
	}

	public static String formatTime(int secs) {
		String TIMESEP = ":";
		
		int hr = (secs / (60 * 60));
		String hrstr = Integer.toString(hr);
		if (hr < 10) {
			hrstr = "0" + hrstr;
		}
		
		
		int min = ((secs / 60) %60);
		String minstr = Integer.toString(min);
		if (min < 10) {
			minstr = "0" + minstr;
		}
		
		
		int sec = (secs %60);
		String secstr = Integer.toString(sec);
		if (sec < 10) {
			secstr = "0" + secstr;
		}
		
		String timestr = String.format("%10s", hrstr + TIMESEP + minstr + TIMESEP + secstr);
		return timestr;
	}

	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {
		String str = String.format("%.2f", d);
		String output =  String.format("%10s", str);
		
		return output;
		
	}
}
