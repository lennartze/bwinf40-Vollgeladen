import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class DataInput {
	File txtFile;
	BufferedReader br;
	ArrayList<String> lineList = new ArrayList<String>();
	int amountOfHotels;
	int timeOfTrafel;
	ArrayList<Hotel> listOfHotels = new ArrayList<Hotel>();
	
	public DataInput(String fileName) throws FileNotFoundException{
		this.txtFile = new File(fileName);
		this.br = new BufferedReader(new FileReader(txtFile));
		classifyDataInLines();
		classifyAmountOfHotels();
		classifyTimeOfTravel();
		classifyListOfHotels();
	}
	
	/**
	 * Füllt alle Zeilen der Text-Datei der lineList hinzu
	 */
	public void classifyDataInLines() {
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			lineList.add(line);
			
			while(line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
				if(line != null)
					lineList.add(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
	}
	
	/**
	 * Holt aus der ersten Zeile der Text-Datei die Anzahl der Hotels.
	 */
	public void classifyAmountOfHotels() {
		String firstLine = lineList.get(0);
		amountOfHotels = Integer.parseInt(firstLine);
	}
	
	/**
	 * Setzt die Zeit in Minuten zum Reisen aus der zweiten Zeile der Text-Datei.
	 */
	public void classifyTimeOfTravel() {
		String secondLine = lineList.get(1);
		timeOfTrafel = Integer.parseInt(secondLine);
	}
	
	/**
	 * Sucht in den Zeilen (ab der 3. Zeilen: Hotel-Zeilen) nach einem Leerzeichen, um
	 * die Zeit bis zum Hotel und die Bewertung des Hotels von einander trennen zu können.
	 * @param hotelLine
	 * @return Stelle des Leerzeichens
	 */
	public int findEmptyChar(String hotelLine) {
		int emptyCharPlace = 0;
		for (int i = 0; i < hotelLine.length(); i++) {
			if(hotelLine.charAt(i)==' ') {
				emptyCharPlace = i;	
			}
		}
		return emptyCharPlace;
	}
	
	/**
	 * Erstellt ein neues Hotel.
	 * @param hotelLine
	 * @param emptyCharPlace
	 * @return Ein Objekt der Klasse Hotel
	 */
	public Hotel classifyHotel(String hotelLine, int emptyCharPlace) {
		int hotelTravelTime;
		double hotelRating;
		Hotel hotel;
		
		hotelTravelTime = Integer.parseInt(hotelLine.substring(0, emptyCharPlace));
		hotelRating = Double.parseDouble(hotelLine.substring(emptyCharPlace+1));
		hotel = new Hotel(hotelTravelTime, hotelRating, true);
		
		return hotel;
	}
	
	/**
	 * Erstellt eine Liste von Hotels.
	 */
	public void classifyListOfHotels() {
		for (int i = 2; i < lineList.size(); i++) {
			String hotelLine = lineList.get(i);
			listOfHotels.add(classifyHotel(hotelLine, findEmptyChar(hotelLine)));
		}
	}
	
	/**
	 * Druckt alle Zeilen der Text-Datei
	 */
	public void printData() {
		for (int i = 0; i < lineList.size(); i++) {
			System.out.println(lineList.get(i));
		}
	}
}
