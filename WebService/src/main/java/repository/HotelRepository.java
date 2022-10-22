package repository;

import java.time.LocalDate;
import java.util.ArrayList;

import model.Hotel;
import model.Reservation;
import model.Room;


public interface HotelRepository {
	
	String roomsToString();
	String toString();
	ArrayList<Room> searchRoom(float priceMin, float priceMax, int size, LocalDate in, LocalDate out);
	void addReservation(Reservation resa);
	Hotel getHotel();
}
