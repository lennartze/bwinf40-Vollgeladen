import java.util.ArrayList;


public class RoutePlanner {
	int maxTravelTimePerDay = 360;
	int maxTavelDays = 5;
	int timeTravelled = 0;
	DataInput dataInput;
	ArrayList<Hotel> listOfHotels = new ArrayList<Hotel>();
	ArrayList<Hotel> routeHotels = new ArrayList<Hotel>();
	ArrayList<Hotel> BestRouteHotels = new ArrayList<Hotel>();
	Route bestRoute;
	double time = 0.0;
	int amountOfPossibleRoutes = 0;
	
	public RoutePlanner(DataInput parDataInput) {
		this.dataInput = parDataInput;
		this.listOfHotels = parDataInput.listOfHotels;
		routeHotels.add(0,new Hotel(0, 0.0, false));
		routeHotels.add(1,new Hotel(0, 0.0, false));
		routeHotels.add(2,new Hotel(0, 0.0, false));
		routeHotels.add(3,new Hotel(0, 0.0, false));
		
		BestRouteHotels.add(0,new Hotel(0, 0.0, false));
		BestRouteHotels.add(1,new Hotel(0, 0.0, false));
		BestRouteHotels.add(2,new Hotel(0, 0.0, false));
		BestRouteHotels.add(3,new Hotel(0, 0.0, false));
		bestRoute = new Route(BestRouteHotels);
		
	}
	
	/**
	 * tryAllRoutes geht alle möglich Routen durch und überprüft ob diese besser sind als die aktuell beste Route.
	 */
	public Route tryAllRoutes () {
		Route tryRoute = new Route(routeHotels);
		final long timeStart = System.currentTimeMillis();		
		
		for (int i = 0; i < listOfHotels.size(); i++) {
			if(listOfHotels.get(i).timeToTravel <= 360) {
				tryRoute.hotelsOfRoute.set(0, listOfHotels.get(i));
				if (canReachGoal(tryRoute.hotelsOfRoute.get(0).timeToTravel)  ) {
					updateBestRoute(tryRoute);
					continue;
				}
			} else {
				tryRoute.hotelsOfRoute.set(0, new Hotel(0, 0, false));
				break;
			}
						
			for(int j = i+1; j < listOfHotels.size(); j++) {
				if (canReachNextHotel(i, j) ){
					tryRoute.hotelsOfRoute.set(1, listOfHotels.get(j));
					if (canReachGoal(tryRoute.hotelsOfRoute.get(1).timeToTravel) ) {
						updateBestRoute(tryRoute);
						continue;	
					}
				}else {
					tryRoute.hotelsOfRoute.set(1, new Hotel(0, 0, false));
					break;
				}
				
				for (int k = j + 1; k < listOfHotels.size(); k++) {
					if (canReachNextHotel(j, k) ){
						tryRoute.hotelsOfRoute.set(2, listOfHotels.get(k));
						if (canReachGoal(tryRoute.hotelsOfRoute.get(2).timeToTravel) ) {
							updateBestRoute(tryRoute);
							continue;
						}
					} else {
						tryRoute.hotelsOfRoute.set(2, new Hotel(0, 0, false));
						break;
					}
					
					for (int l = k+1; l < listOfHotels.size(); l++) {
						if (canReachNextHotel(k, l) ){
							tryRoute.hotelsOfRoute.set(3, listOfHotels.get(l));
							if (canReachGoal(tryRoute.hotelsOfRoute.get(3).timeToTravel)) {
								updateBestRoute(tryRoute);
								continue;
							}
						} else {
							tryRoute.hotelsOfRoute.set(3, new Hotel(0, 0, false));
							break;
						}
					}
				}
			}
		}
		
		final long timeEnd = System.currentTimeMillis();
		double convertToSeconds = 1000;
		time = (timeEnd - timeStart) / convertToSeconds;
		return bestRoute;
	}
	
	/**
	 * Überprüft ob an der aktuellen Stelle das Ziel der Reise erreicht werden kann
	 * @return True wenn das Ziel der Reise in einem Tag erreicht werden kann.
	 */
	public boolean canReachGoal (int hotelTimeTravelled) {
		boolean canReach = false;
		int TravelGoal = dataInput.timeOfTrafel;
		
		if (TravelGoal - hotelTimeTravelled <= 360) {
			canReach = true;
		}
		
		return canReach;
	}
	
	/**
	 * Überprüft, ob man von hotel1 hotel2 erreichen kann.
	 * @param hotelNo1
	 * @param hotelNo2
	 * @return True, wenn der Abstand von hotel1 -> hotel2 <= 360 ist.
	 */
	public boolean canReachNextHotel (int hotelNo1, int hotelNo2) {
		boolean canReach = false;
		Hotel hotel1 = listOfHotels.get(hotelNo1);
		Hotel hotel2 = listOfHotels.get(hotelNo2);
		
		if (hotel2.timeToTravel - hotel1.timeToTravel <= 360 ) {
			canReach = true;
		}
		
		return canReach;
	}
	
	/**
	 * Überprüft, ob die übergebene tryRoute eine höhere niedrigste Bewertung eines Hotels hat. Bzw. ob es eine gleich hohe niedrigste Bewertung hat, aber eine höhere Druchschnittsbewertung besitzt.
	 * In diesen beiden Fällen wird die übergebene tryRoute zur neuen bestRoute.
	 * @param tryRoute
	 */
	public void updateBestRoute (Route tryRoute) {
		amountOfPossibleRoutes++;
		tryRoute.CalcAverageRating();
		tryRoute.CalcLowestRating();
		
		if(tryRoute.lowestRating > bestRoute.lowestRating) {
			bestRoute.hotelsOfRoute = (ArrayList<Hotel>) tryRoute.hotelsOfRoute.clone();
			bestRoute.CalcAverageRating();
			bestRoute.CalcLowestRating();
		} else if (tryRoute.lowestRating == bestRoute.lowestRating && tryRoute.averageRating > bestRoute.averageRating) {
			bestRoute.hotelsOfRoute = (ArrayList<Hotel>) tryRoute.hotelsOfRoute.clone();
			bestRoute.CalcAverageRating();
			bestRoute.CalcLowestRating();
		}
		
	}
	
	
	
	
}
