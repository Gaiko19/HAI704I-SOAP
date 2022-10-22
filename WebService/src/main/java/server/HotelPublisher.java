package server;

import javax.xml.ws.Endpoint;

import repository.HotelRepository;
import repository.RitzRepositoryImpl;
import service.HotelServiceImpl;

public class HotelPublisher {

	public static void main(String[] args) {
		HotelRepository ritz = new RitzRepositoryImpl();
		Endpoint.publish("http://localhost:8080/hotel", new HotelServiceImpl(ritz));
		System.err.println("Server ready!");

	}

}
