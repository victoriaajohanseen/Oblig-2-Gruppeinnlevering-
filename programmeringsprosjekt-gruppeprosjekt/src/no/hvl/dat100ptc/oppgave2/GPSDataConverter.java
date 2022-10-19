package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {
	
	private static int TIME_STARTINDEX = 11; // posisjon for start av tidspunkt i timestr

	public static int toSeconds(String timestr) {
		int secs;
		int hr, min, sec;
		
		hr = Integer.parseInt(timestr.substring(11, 13));
		min = Integer.parseInt(timestr.substring(14, 16));
		sec = Integer.parseInt(timestr.substring(17, 19));
		
		secs = (hr * 60 * 60) + (min * 60) + sec;
		
		return secs;		
		
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		GPSPoint gpspoint;
		
		String time = timeStr;
		int t = toSeconds(time);
		
		String latitude = latitudeStr;
		double lat = Double.parseDouble(latitude);
		
		String longitude = longitudeStr;
		double lo = Double.parseDouble(longitude);
		
		String elevation = elevationStr;
		double el = Double.parseDouble(elevation);
		
		gpspoint = new GPSPoint(t, lat, lo, el);
		return gpspoint;
	    
	}
	
}
