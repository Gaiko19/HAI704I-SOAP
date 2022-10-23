package repository;

import java.time.LocalDate;
import java.util.ArrayList;

import model.Hotel;
import model.Position;
import model.Reservation;
import model.Room;

public class HotelRepositoryImpl implements HotelRepository {

	private Hotel hotel;
	
	public HotelRepositoryImpl() {
		hotel = new Hotel();
		Position pos = new Position("City", "Country", "street", 1);
		ArrayList<Room> rooms = new ArrayList<Room>();
		Room r1 = new Room(1, true, 10, 1);
		Room r2 = new Room(2, true, 10, 1);
		Room r3 = new Room(3, true, 10, 1);
		rooms.add(r1);
		rooms.add(r2);
		rooms.add(r3);
		this.hotel.setName("Hotel");
		this.hotel.setStars(1);
		this.hotel.setAddress(pos);
		this.hotel.setRooms(rooms);
		
		hotel.setResa(new ArrayList<Reservation>());
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
		return hotel;
	}

}
