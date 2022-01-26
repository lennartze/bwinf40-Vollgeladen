import java.util.ArrayList;

public class Route {
	int amountOfStops = 0;
	ArrayList<Hotel> hotelsOfRoute; 
	double averageRating = 0;
	double lowestRating = 0;
	
	public Route(ArrayList<Hotel> parListOfHotels ) {
		this.hotelsOfRoute = parListOfHotels;
		CalcAmountOfStops();
		CalcAverageRating();
	}
	
	/**
	 * Berechnet die Anzahl der tatsächlichen Hotels in der Route.
	 */
	public void CalcAmountOfStops () {
		amountOfStops = 0;
		for (int i = 0; i < hotelsOfRoute.size(); i++) {
			if(hotelsOfRoute.get(i).isFilled == true) {
				amountOfStops++;
			}
		}
	}
	
	/**
	 * Berechnet die Durchschnittsbewertung einer Route.
	 */
	public void CalcAverageRating () {
		double allRating = 0;
		for (int i = 0; i < hotelsOfRoute.size(); i++) {
			if (hotelsOfRoute.get(i).isFilled == true) {			
				allRating += hotelsOfRoute.get(i).rating;			
			}
		}
		CalcAmountOfStops();
		if (amountOfStops != 0) {
			averageRating = allRating / amountOfStops;
		} else {
			averageRating = 0;
		}
	}
	
	/**
	 * Ermittelt die niedrigste Hotel-Bewertung der Route.
	 */
	public void CalcLowestRating () {
		lowestRating = hotelsOfRoute.get(0).rating;
		for(int i = 1; i < hotelsOfRoute.size(); i++) {
			if(hotelsOfRoute.get(i).isFilled == true) {
				if(hotelsOfRoute.get(i).rating < lowestRating) {
					lowestRating = hotelsOfRoute.get(i).rating;
				}
			}
		}
	}
}
