package server;

import javax.xml.ws.Endpoint;

import repository.CrowneRepositoryImpl;
import repository.F1MtpSudRepositoryImpl;
import repository.F1TlsUnRepositoryImpl;
import repository.HotelRepository;
import repository.KyriadRepositoryImpl;
import repository.RitzRepositoryImpl;
import service.HotelServiceImpl;

public class HotelPublisher {

	public static void main(String[] args) {
		HotelRepository ritz = new RitzRepositoryImpl();
		HotelRepository kyriad = new KyriadRepositoryImpl();
		HotelRepository f1mtpsud = new F1MtpSudRepositoryImpl();
		HotelRepository f1tlsram = new F1TlsUnRepositoryImpl();
		HotelRepository f1tlsun = new F1TlsUnRepositoryImpl();
		HotelRepository crowne = new CrowneRepositoryImpl();
		Endpoint.publish("http://localhost:8080/ritz", new HotelServiceImpl(ritz));
		Endpoint.publish("http://localhost:8080/kyriad", new HotelServiceImpl(kyriad));
		Endpoint.publish("http://localhost:8080/f1mtpsud", new HotelServiceImpl(f1mtpsud));
		Endpoint.publish("http://localhost:8080/f1tlsram", new HotelServiceImpl(f1tlsram));
		Endpoint.publish("http://localhost:8080/f1tlsun", new HotelServiceImpl(f1tlsun));
		Endpoint.publish("http://localhost:8080/crowne", new HotelServiceImpl(crowne));
		System.err.println("Server ready!");

	}

}
