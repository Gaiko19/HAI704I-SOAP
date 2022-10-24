package repository;

import java.time.LocalDate;
import java.util.ArrayList;

import model.Hotel;
import model.Position;
import model.Reservation;
import model.Room;

public class IbisRepositoryImpl implements HotelRepository {

	private Hotel hotel = new Hotel(
			"Ibis Budget",
			3.6,
			new ArrayList<>(),
			new Position("Toulouse", "France", "matabiau", 1));
	
	public IbisRepositoryImpl() {
		ArrayList<Room> rooms = new ArrayList<>();
		Room room1 = new Room(1, true, 60.5, 1);
		Room room2 = new Room(2, true, 65.10, 2);
		Room room3 = new Room(3, true, 62.10, 3);
		Room room4 = new Room(4, true, 60.5, 3);
		Room room5 = new Room(5, true, 62.10, 2);

		
		rooms.add(room1);
		rooms.add(room2);
		rooms.add(room3);
		rooms.add(room4);
		rooms.add(room5);

		this.hotel = new Hotel(
				"Ibis Budget",
				3.6,
				rooms,
				new Position("Toulouse", "France", "matabiau", 1));
		
		hotel.setResa(new ArrayList<>());
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
	public ArrayList<Room> searchRoom(float priceMin, float priceMax, int size, String inS, String outS) {
		LocalDate in = LocalDate.parse(inS);
		LocalDate out = LocalDate.parse(outS);
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
