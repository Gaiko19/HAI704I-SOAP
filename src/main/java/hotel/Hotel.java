package hotel;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.jws.WebService;

import hotelfinder.Agency;

@WebService(endpointInterface="finder.IHotel")
public class Hotel implements IHotel {
	private String name;
	private double stars;
	private ArrayList<Room> rooms;
	private Position address;
	private ArrayList<Reservation> resa;
	private ArrayList<Agency> partner;
	
	
	@Override
	public ArrayList<Agency> getPartner() {
		return partner;
	}
	@Override
	public void setPartner(ArrayList<Agency> partner) {
		this.partner = partner;
	}
	@Override
	public ArrayList<Reservation> getResa() {
		return resa;
	}
	@Override
	public void setResa(ArrayList<Reservation> resa) {
		this.resa = resa;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public double getStars() {
		return stars;
	}
	@Override
	public void setStars(double stars) {
		this.stars = stars;
	}
	@Override
	public ArrayList<Room> getRooms() {
		return rooms;
	}
	@Override
	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}
	@Override
	public Position getAddress() {
		return address;
	}
	@Override
	public void setAddress(Position address) {
		this.address = address;
	}
	public Hotel(String name, double stars, ArrayList<Room> rooms, Position address) {
		this.name = name;
		this.stars = stars;
		this.rooms = rooms;
		this.address = address;
		this.resa = new ArrayList<Reservation>();
	}
	public Hotel() {
		this.name = "Undefined";
		this.stars = 0;
		this.rooms = new ArrayList<>();
		this.address = new Position();
		this.resa = new ArrayList<Reservation>();
	}
	
	@Override
	public String roomsToString() {
		String res = "";
		for (int index = 0; index < rooms.size(); index++) {
			res += rooms.get(index).toString();
		}
		return res;
	}
	
	@Override
	public String toString() {
		return "Hotel "+ this.name +"\n Rating : " + this.stars + "\n" + this.address + "\n" + roomsToString();
	}
	
	@Override
	public ArrayList<Room> searchRoom(float priceMin, float priceMax, int size, LocalDate in, LocalDate out) {
		ArrayList<Room> results = new ArrayList<>();
		for (int index = 0; index < this.rooms.size(); index++) {
			Room room = this.rooms.get(index);
			double roomPrice = room.getPrice();
			int roomSize = room.getSize();
			if(roomPrice >= priceMin && roomPrice <= priceMax && roomSize >= size) {
				boolean isOkay = true;
				for(Reservation res : this.resa) {
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
		this.resa.add(resa);
	}
	
	
}
