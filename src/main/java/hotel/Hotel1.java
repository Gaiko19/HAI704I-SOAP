package hotel;

import java.util.ArrayList;

import javax.xml.ws.Endpoint;

public class Hotel1 {
	
	public static void main(String[] args) {
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
		
		Position position = new Position("Montpellier", "France", "Avenue de Barcelone", 12);
		
		Hotel hotel = new Hotel("Ritz", 4.8, rooms, position);
		
		Endpoint.publish("http://localhost:8080/ritz", hotel);
		System.err.println("Server ready!");
		
	}
	
	
}
