package cz.cvut.fel.jee.gourmeter.ejb;

public class CoordinateSearchWrapper {

	private final double longitudeMin;
	private final double longitudeMax;
	private final double latitudeMin;
	private final double latitudeMax;

	CoordinateSearchWrapper(double longitudeMin, double longitudeMax,
									double latitudeMin, double latitudeMax) {
		this.longitudeMin = longitudeMin;
		this.longitudeMax = longitudeMax;
		this.latitudeMin = latitudeMin;
		this.latitudeMax = latitudeMax;
	}

	double getLongitudeMin() {
		return longitudeMin;
	}

	double getLongitudeMax() {
		return longitudeMax;
	}

	double getLatitudeMin() {
		return latitudeMin;
	}

	double getLatitudeMax() {
		return latitudeMax;
	}

}
