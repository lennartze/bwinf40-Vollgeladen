import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.InputStreamReader;

public class main {

	public static void main(String[] args) throws FileNotFoundException {
		//DataInput dataInput = new DataInput("C:\\Users\\lennart.zeppenfeld\\Documents\\RBBK\\2. Jahr\\STD\\BWINF 40\\Aufgabe 2\\hotels1.txt");
		//Beispieldatei auswählen
		System.out.println("Bitt geben Sie eine Zahl von 1-5 ein!");
		System.out.println("1: hotels1.text");
		System.out.println("2: hotels2.text");
		System.out.println("3: hotels3.text");
		System.out.println("4: hotels4.text");
		System.out.println("5: hotels5.text");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int intInput = 0;
		
		try {
			intInput = Integer.parseInt(br.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(intInput == 1 || intInput == 2 || intInput == 3 || intInput == 4 || intInput == 5)
		{
			DataInput dataInput = new DataInput(".\\Beispieldateien\\hotels" + Integer.toString(intInput) + ".txt");
			
			
			for (int i = 0; i < dataInput.listOfHotels.size(); i++) {
				System.out.println(dataInput.listOfHotels.get(i).timeToTravel + ": " +  + dataInput.listOfHotels.get(i).rating );
			}
			System.out.println("Anzahl an Hotels: " + dataInput.amountOfHotels);
			System.out.println("Reiseziel: " + dataInput.timeOfTrafel);
			
			//RoutePlanner starten
			RoutePlanner routePlanner = new RoutePlanner(dataInput);
			Route bestRoute = routePlanner.tryAllRoutes();
			System.out.println();
			System.out.println("-----------Beste Route für hotels" + Integer.toString(intInput) + "-----------");
			System.out.println("Anzahl an Stopps: " + bestRoute.amountOfStops);
			System.out.println("Durchschnittliche Bewertung: " + bestRoute.averageRating);
			System.out.println("Niedrigste Bewertung eines Hotels: " + bestRoute.lowestRating);
			for (int i = 0; i < bestRoute.hotelsOfRoute.size(); i++) {
				System.out.println(bestRoute.hotelsOfRoute.get(i).timeToTravel + " | Bewertung: " + bestRoute.hotelsOfRoute.get(i).rating);
			}
			System.out.println("Zeit zum Berechnen der besten Route: " + routePlanner.time + " Sekunden");	
			System.out.println("Anzahl an möglichen Routen: " + routePlanner.amountOfPossibleRoutes);
		} else {
			System.out.println("Diese Eingabe konnte nicht verarbeitet werden.");
		}
	}
}
