
package webservice;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.0.2
 * Generated source version: 2.2
 * 
 */
@WebService(name = "HotelService", targetNamespace = "http://service/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface HotelService {


    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "roomsToString", targetNamespace = "http://service/", className = "webservice.RoomsToString")
    @ResponseWrapper(localName = "roomsToStringResponse", targetNamespace = "http://service/", className = "webservice.RoomsToStringResponse")
    @Action(input = "http://service/HotelService/roomsToStringRequest", output = "http://service/HotelService/roomsToStringResponse")
    public String roomsToString();

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg4
     * @param arg1
     * @param arg0
     * @return
     *     returns java.util.List<webservice.Room>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "searchRoom", targetNamespace = "http://service/", className = "webservice.SearchRoom")
    @ResponseWrapper(localName = "searchRoomResponse", targetNamespace = "http://service/", className = "webservice.SearchRoomResponse")
    @Action(input = "http://service/HotelService/searchRoomRequest", output = "http://service/HotelService/searchRoomResponse")
    public List<Room> searchRoom(
        @WebParam(name = "arg0", targetNamespace = "")
        float arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        float arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        String arg4);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "addReservation", targetNamespace = "http://service/", className = "webservice.AddReservation")
    @ResponseWrapper(localName = "addReservationResponse", targetNamespace = "http://service/", className = "webservice.AddReservationResponse")
    @Action(input = "http://service/HotelService/addReservationRequest", output = "http://service/HotelService/addReservationResponse")
    public void addReservation(
        @WebParam(name = "arg0", targetNamespace = "")
        Reservation arg0);

    /**
     * 
     * @return
     *     returns webservice.Hotel
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getHotel", targetNamespace = "http://service/", className = "webservice.GetHotel")
    @ResponseWrapper(localName = "getHotelResponse", targetNamespace = "http://service/", className = "webservice.GetHotelResponse")
    @Action(input = "http://service/HotelService/getHotelRequest", output = "http://service/HotelService/getHotelResponse")
    public Hotel getHotel();

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "toString", targetNamespace = "http://service/", className = "webservice.ToString")
    @ResponseWrapper(localName = "toStringResponse", targetNamespace = "http://service/", className = "webservice.ToStringResponse")
    @Action(input = "http://service/HotelService/toStringRequest", output = "http://service/HotelService/toStringResponse")
    public String toString();

}
