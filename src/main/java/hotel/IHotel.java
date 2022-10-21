package hotel;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;

import hotelfinder.Agency;

@WebService
public interface IHotel {

	@WebMethod
	ArrayList<Agency> getPartner();

	@WebMethod
	void setPartner(ArrayList<Agency> partner);

	@WebMethod
	ArrayList<Reservation> getResa();

	@WebMethod
	void setResa(ArrayList<Reservation> resa);

	@WebMethod
	String getName();

	@WebMethod
	void setName(String name);

	@WebMethod
	double getStars();

	@WebMethod
	void setStars(double stars);

	@WebMethod
	ArrayList<Room> getRooms();

	@WebMethod
	void setRooms(ArrayList<Room> rooms);

	@WebMethod
	Position getAddress();

	@WebMethod
	void setAddress(Position address);

	@WebMethod
	String roomsToString();

	@WebMethod
	String toString();

	@WebMethod
	ArrayList<Room> searchRoom(float priceMin, float priceMax, int size, LocalDate in, LocalDate out);

	@WebMethod
	void addReservation(Reservation resa);

}