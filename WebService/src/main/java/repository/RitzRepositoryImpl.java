package repository;

import java.time.LocalDate;
import java.util.ArrayList;

import model.Hotel;
import model.Position;
import model.Reservation;
import model.Room;

public class RitzRepositoryImpl implements HotelRepository {

	private Hotel hotel = new Hotel(
			"Ritz",
			4.8,
			new ArrayList<Room>(),
			new Position("Montpellier", "France", "avenue de Barcelone", 12));
	
	public RitzRepositoryImpl() {
		ArrayList<Room> rooms = new ArrayList<Room>();
		Room room1 = new Room(1, true, 95.90, 3);
		Room room2 = new Room(2, true, 80, 3);
		Room room3 = new Room(3, true, 99, 3);
		Room room4 = new Room(4, true, 90.50, 3);
		Room room5 = new Room(5, true, 120.50, 4);
		Room room6 = new Room(6, true, 110, 4);
		Room room7 = new Room(7, true, 95.90, 2);
		Room room8 = new Room(8, true, 98.20, 3);
		Room room9 = new Room(9, true, 102.50, 4);
		Room room10 = new Room(10, true, 101.30, 3);
		Room room11 = new Room(11, true, 78, 2);
		
		rooms.add(room1);
		rooms.add(room2);
		rooms.add(room3);
		rooms.add(room4);
		rooms.add(room5);
		rooms.add(room6);
		rooms.add(room7);
		rooms.add(room8);
		rooms.add(room9);
		rooms.add(room10);
		rooms.add(room11);

		this.hotel = new Hotel(
				"Ritz",
				4.8,
				rooms,
				new Position("Montpellier", "France", "avenue de Barcelone", 12));
	}
	
	@Override
	public String roomsToString() {
		String res = "";
		for (int index = 0; index < this.hotel.getRooms().size(); index++) {
			res += this.hotel.getRooms().get(index).toString();
		}
		return res;
	}
	
	@Override
	public String toString() {
		return "Hotel "+ this.hotel.getName() +"\n Rating : " + this.hotel.getStars() + "\n" + this.hotel.getAddress() + "\n" + roomsToString();
	}
	
	@Override
	public ArrayList<Room> searchRoom(float priceMin, float priceMax, int size, LocalDate in, LocalDate out) {
		ArrayList<Room> results = new ArrayList<>();
		for (int index = 0; index < this.hotel.getRooms().size(); index++) {
			Room room = this.hotel.getRooms().get(index);
			double roomPrice = room.getPrice();
			int roomSize = room.getSize();
			if(roomPrice >= priceMin && roomPrice <= priceMax && roomSize >= size) {
				boolean isOkay = true;
				for(Reservation res : this.hotel.getResa()) {
					if(res.getRoom().getRoomNumber() == room.getRoomNumber()) {
						if((in.isAfter(res.getIn()) && in.isBefore(res.getOut()))
							|| (out.isAfter(res.getIn()) && out.isBefore(res.getOut()))
							|| ((in.isBefore(res.getIn()) && out.isAfter(res.getOut())))
							|| (in.isAfter(res.getIn()) && out.isBefore(res.getOut()))) {
							isOkay = false;
							continue;
						}
					}
				}
				if(isOkay) {
					results.add(room);
				}
			}
		}
		return results;
	}
	
	@Override
	public void addReservation(Reservation resa) {
		this.hotel.getResa().add(resa);
	}

	@Override
	public Hotel getHotel() {
		return this.hotel;
	}

}
