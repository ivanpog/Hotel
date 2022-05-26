package ch.bzz.roomlist.service;

import ch.bzz.roomlist.data.DataHandler;
import ch.bzz.roomlist.model.Room;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Path("room")
public class RoomService {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listRooms() {
        List<Room> roomList = DataHandler.readAllRooms();
        return Response
                .status(200)
                .entity(roomList)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readRoom(
            @QueryParam("number") int roomNumber
    ) {
        Room room = DataHandler.readRoomByNumber(roomNumber);
        return Response
                .status(200)
                .entity(room)
                .build();
    }

    /**
     * creates a room inside of roomList
     * @param roomNumber
     * @param size
     * @param priceNight
     * @param hotelName
     * @param places
     * @return new room
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertBook(
            @FormParam("roomNumber") int roomNumber,
            @FormParam("size") String size,
            @FormParam("priceNight") BigDecimal priceNight,
            @FormParam("hotelName") String hotelName,
            @FormParam("places")int places
    ){
        Room room=new Room();
        room.setRoomNumber(roomNumber);
        room.setSize(size);
        room.setPriceNight(priceNight);
        room.setHotelName(hotelName);
        room.setPlaces(places);

        DataHandler.insertRoom(room);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates a room inside of list
     * @param roomNumber
     * @param size
     * @param priceNight
     * @param hotelName
     * @param places
     * @return updated room
     */
    @POST
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateBook(
            @FormParam("roomNumber") int roomNumber,
            @FormParam("size") String size,
            @FormParam("priceNight") BigDecimal priceNight,
            @FormParam("hotelName") String hotelName,
            @FormParam("places")int places

    ){
        int httpStatus=200;
        Room room=DataHandler.readRoomByNumber(roomNumber);
        if (room!=null){
            room.setRoomNumber(roomNumber);
            room.setSize(size);
            room.setPriceNight(priceNight);
            room.setHotelName(hotelName);
            room.setPlaces(places);

            DataHandler.updateRoom();
        }else{
            httpStatus=410;
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();

    }

    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)

    public Response deleteBook(
            @QueryParam("roomNumber") int roomNumber
    ){
        int httpStatus=200;
        if(!DataHandler.deleteRoom(roomNumber)){
            httpStatus=410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();

    }
}
