package client;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import webservice.Hotel;
import webservice.HotelService;
import webservice.HotelServiceImplService;
import webservice.Room;



public class Agency {
	private String agencyName;
	private HashMap<String, String> clientInfos;
	private HashMap<HotelService, Double> offers;
	
	public Agency() {
		this.agencyName = "Hotel name";
		this.clientInfos = new HashMap<String, String>();
		clientInfos.put("admin", "root");
		this.offers = new HashMap<HotelService, Double>();
	}

	public Agency(String name, HashMap<String, String> clientInfos, HashMap<HotelService, Double> offers) {
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

	public HashMap<HotelService, Double> getOffers() {
		return offers;
	}

	public void setOffers(HashMap<HotelService, Double> offers) {
		this.offers = offers;
	}

	public void quitAgency() {
		System.out.println("Thanks for using TripFinder\n Bye bye...");
		System.exit(0);
	}
	
	public Hotel searchHotel(LocalDate in, LocalDate out, int size, float priceMin, float priceMax, String location) {
		Hotel hotel = new Hotel();
		HotelService proxy= null;
		try {
			proxy = new HotelServiceImplService(new URL("http://localhost:8080/hotel?wsdl")).getHotelServiceImplPort();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		List<Room> rooms = proxy.searchRoom(priceMin, priceMax, size, in, out);
		hotel = proxy.getHotel();
		hotel.getRooms().addAll(rooms);

		return hotel;
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
