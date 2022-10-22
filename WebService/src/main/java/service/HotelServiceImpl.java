package service;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.jws.WebService;

import model.Hotel;
import model.Reservation;
import model.Room;
import repository.HotelRepository;
import repository.HotelRepositoryImpl;
import repository.RitzRepositoryImpl;

@WebService(endpointInterface="service.HotelService")
public class HotelServiceImpl implements HotelService {
	
	private HotelRepository repo;
	
	@Override
	public Hotel getHotel() {
		return repo.getHotel();
	}

	@Override
	public String roomsToString() {
		return repo.roomsToString();
	}

	@Override
	public ArrayList<Room> searchRoom(float priceMin, float priceMax, int size, LocalDate in, LocalDate out) {
		return repo.searchRoom(priceMin, priceMax, size, in, out);
	}

	@Override
	public void addReservation(Reservation resa) {
		repo.addReservation(resa);
	}
	
	@Override
	public String toString() {
		return repo.toString();
	}
	
	public HotelServiceImpl(String type) {
		switch (type) {
		case "Ritz" :
			this.repo = new RitzRepositoryImpl();
			break;
		default :
			this.repo = new HotelRepositoryImpl();
			break;
		}
	}
	
	public HotelServiceImpl(HotelRepository repo) {
		this.repo = repo;
	}
	
	public HotelServiceImpl() {
		this.repo = new HotelRepositoryImpl();
	}

}
