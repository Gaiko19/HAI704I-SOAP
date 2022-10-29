package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import model.Hotel;
import model.Position;
import model.Reservation;
import model.Room;

public class HotelRepositoryImpl implements HotelRepository {

	private Hotel hotel;
	
	public HotelRepositoryImpl(int ID) {
			
	String name="";
	float rating = 0;
	int pos = 0;
	int id = 0;
	Position adress = new Position();
	ArrayList<Room> rooms = new ArrayList<>();
	ArrayList<Integer> roomIds = new ArrayList<>();
	try{  
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(
		"jdbc:mysql://dakota.o2switch.net:3306/sc1samo7154_hotelfinderdb","sc1samo7154_hotelfinder","hotelfinderdb");
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from Hotel where id="+ID);
		if(rs.next()) {
			name = rs.getString("Name");
			rating = rs.getFloat("Rating"); 
			pos= rs.getInt("Adress");
			id = rs.getInt("ID");
		}
		rs = stmt.executeQuery("select * from Position where id="+ pos);
		if(rs.next()) {
			adress.setCity(rs.getString("City"));
			adress.setCountry(rs.getString("Country"));
			adress.setStreet(rs.getString("Street"));
			adress.setNumber(rs.getInt("Number"));
		}
		rs = stmt.executeQuery("select Room from ListeRooms where Hotel="+ id);
		while(rs.next()) {
			roomIds.add(rs.getInt(1));
		}
		for(int i = 0; i < roomIds.size(); i++) {
			int roomID = roomIds.get(i);
			rs = stmt.executeQuery("select * from Room where ID="+ roomID);
			if(rs.next()) {
				Room room = new Room();
				room.setRoomNumber(rs.getInt("Number"));
				room.setPrice(rs.getFloat("Price"));
				room.setSize(rs.getInt("Size"));
				rooms.add(room);
			}
		}
		
		con.close();
	}
	catch(Exception e){
		System.out.println(e);
	}
	
	this.hotel = new Hotel(
			name,
			rating,
			rooms,
			adress);
	
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
