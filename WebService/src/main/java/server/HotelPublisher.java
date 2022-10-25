package server;

import javax.xml.ws.Endpoint;

import repository.Formule1RepositoryImpl;
import repository.HotelRepository;
import repository.IbisRepositoryImpl;
import repository.RitzRepositoryImpl;
import service.HotelServiceImpl;

public class HotelPublisher {

	public static void main(String[] args) {
		HotelRepository ritz = new RitzRepositoryImpl();
		HotelRepository ibis = new IbisRepositoryImpl();
		HotelRepository formule1 = new Formule1RepositoryImpl();
		Endpoint.publish("http://localhost:8080/ritz", new HotelServiceImpl(ritz));
		Endpoint.publish("http://localhost:8080/ibis", new HotelServiceImpl(ibis));
		Endpoint.publish("http://localhost:8080/formule1", new HotelServiceImpl(formule1));
		System.err.println("Server ready!");

	}

}
