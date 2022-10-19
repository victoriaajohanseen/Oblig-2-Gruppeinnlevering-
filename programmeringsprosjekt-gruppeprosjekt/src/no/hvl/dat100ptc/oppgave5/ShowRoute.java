package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {
	
	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {
		
		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {
		
		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);
		
		showRouteMap(MARGIN + MAPYSIZE);
		
		showStatistics();
	}
	
	// antall x-pixels per lengdegrad
    public double xstep() {

        double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
        double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

        return MAPXSIZE / Math.abs(maxlon - minlon);
    }
	
    // antall y-pixels per breddegrad
    public double ystep() {
    
        double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
        double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
        
        return MAPYSIZE / Math.abs(maxlat - minlat);
        
    }
	
    public void showRouteMap(int ybase) {
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		//initialiserer start verdier som senere blir brukt som base for tegne linje fra
		int preX = (int)(xstep() * (gpspoints[0].getLongitude() - minlon));
		int preY = (int)(ystep() * (gpspoints[0].getLatitude() - minlat));
		int preEle = (int)gpspoints[0].getElevation();
		
		
		for (int i = 1; i < gpspoints.length; i++) {
			int thisX = (int)(xstep() * (gpspoints[i].getLongitude() - minlon));
			int thisY = (int)(ystep() * (gpspoints[i].getLatitude() - minlat));
			int thisEle = (int)gpspoints[i].getElevation();
			
			//drawns log point on route.
			setColor(0, 0, 0);
			fillCircle(MARGIN + thisX, ybase - thisY, 2);
			
			//bytter farge avhengig av om stigning synger eller stiger.
			if (thisEle < preEle) {
				setColor(255, 0, 0);
			} else {
				setColor(0, 255, 0);
			}
			
			drawLine(MARGIN + preX, ybase - preY, MARGIN + thisX, ybase - thisY);
			
			preX = thisX;
			preY = thisY;
		}
	}

    public void showStatistics() {

		int TEXTDISTANCE = 20;
		
		String[] data = { 
				String.format("%-15s", "Total time") + ":" + String.format("%15s", GPSUtils.formatTime(gpscomputer.totalTime())), 
				String.format("%-15s", "Total distance") + ":" + String.format("%15s", GPSUtils.formatDouble(gpscomputer.totalDistance()/1000)+ " km" ), 
				String.format("%-15s", "Total elevation") + ":" + String.format("%15s", GPSUtils.formatDouble(gpscomputer.totalElevation())+ " m"), 
				String.format("%-15s", "Max speed") + ":" + String.format("%15s", GPSUtils.formatDouble (gpscomputer.maxSpeed())+ " km/t"), 
				String.format("%-15s", "Average speed") + ":" + String.format("%15s", GPSUtils.formatDouble(gpscomputer.averageSpeed())+" km/t"), 
				String.format("%-15s", "Energy") + ":" + String.format("%15s", GPSUtils.formatDouble(gpscomputer.totalKcal(GPSComputer.WEIGHT)) + " kcal") };
		
		setColor(0, 0, 0);
		setFont("Courier", 12);
		int prePosY = TEXTDISTANCE;

		for (String d : data) {
			drawString(d, 0, prePosY);
			prePosY += TEXTDISTANCE;
		}
	}
}
