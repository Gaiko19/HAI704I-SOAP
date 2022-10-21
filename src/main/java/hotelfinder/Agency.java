package hotelfinder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import hotel.Hotel;
import hotel.Room;

public class Agency {
	private String agencyName;
	private HashMap<String, String> clientInfos;
	private HashMap<Hotel, Float> offers;
	
	public Agency() {
		this.agencyName = "Hotel name";
		this.clientInfos = new HashMap<String, String>();
		clientInfos.put("admin", "root");
		this.offers = new HashMap<Hotel, Float>();
	}

	public Agency(String name, HashMap<String, String> clientInfos, HashMap<Hotel, Float> offers) {
		this.agencyName = name;
		this.clientInfos = clientInfos;
		this.offers = offers;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public HashMap<String, String> getClientInfos() {
		return clientInfos;
	}

	public void setClientInfos(HashMap<String, String> clientInfos) {
		this.clientInfos = clientInfos;
	}

	public HashMap<Hotel, Float> getOffers() {
		return offers;
	}

	public void setOffers(HashMap<Hotel, Float> offers) {
		this.offers = offers;
	}

	public void quitAgency() {
		System.out.println("Thanks for using TripFinder\n Bye bye...");
		System.exit(0);
	}
	
	public ArrayList<Hotel> searchHotel(LocalDate in, LocalDate out, int size, float priceMin, float priceMax, String location) {
		ArrayList<Hotel> results = new ArrayList<Hotel>();
		for (Hotel hotel : offers.keySet()) {
			if(hotel.getAddress().getCountry().equals(location) || hotel.getAddress().getCity().equals(location)) {
				ArrayList<Room> rooms = hotel.searchRoom(priceMin, priceMax, size, in, out);
				if(rooms != null) {
					hotel.setRooms(rooms);
					results.add(hotel);
				}				
			}
		}
		return results;
	}
	
	public boolean connectClient(String username, String pwd) {
		boolean access = false;
		for (String i : this.clientInfos.keySet()) {
			  if(username.equals(i) && this.clientInfos.get(i).equals(pwd)) {
				  access = true;
				  break;
			  }
		}
		return access;
	}
	

}
