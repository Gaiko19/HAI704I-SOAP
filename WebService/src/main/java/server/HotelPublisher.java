package server;

import javax.xml.ws.Endpoint;

import repository.HotelRepository;
import repository.IbisRepositoryImpl;
import repository.RitzRepositoryImpl;
import service.HotelServiceImpl;

public class HotelPublisher {

	public static void main(String[] args) {
		HotelRepository ritz = new RitzRepositoryImpl();
		HotelRepository ibis = new IbisRepositoryImpl();
		Endpoint.publish("http://localhost:8080/ritz", new HotelServiceImpl(ritz));
		Endpoint.publish("http://localhost:8080/ibis", new HotelServiceImpl(ibis));
		System.err.println("Server ready!");
	}
}
