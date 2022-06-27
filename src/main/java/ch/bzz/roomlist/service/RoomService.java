package ch.bzz.roomlist.service;

import ch.bzz.roomlist.data.DataHandler;
import ch.bzz.roomlist.model.Room;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Path("room")
public class RoomService {

    /**
     * list of rooms
     * @return response
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listRooms(
            @CookieParam("userRole") String userRole
    ) {
        List<Room> roomList=null;
        int httpStatus;
        if (userRole.equals("guest")){
              httpStatus=403;
        }else{
             roomList = DataHandler.readAllRooms();
             httpStatus=200;
        }

        return Response
                .status(httpStatus)
                .entity(roomList)
                .build();
    }

    /**
     * read room from list
     * @param roomNumber to set
     * @return response
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readRoom(
            @NotNull
            @QueryParam("number") Integer roomNumber,
            @CookieParam("userRole") String userRole


    ) {
        Room room=null;
        int httpStatus = 200;
        if (userRole.equals("guest")) {
            httpStatus = 403;
        } else {
             room = DataHandler.readRoomByNumber(roomNumber);
            if (room == null) {
                httpStatus = 410;
            }
        }
        return Response
                .status(httpStatus)
                .entity(room)
                .build();
    }

    /**
     * create room
     * @param room to set
     * @param hotelName to set
     * @return response
     */

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertRoom(
            @Valid @BeanParam Room room,
            @FormParam("hotelName") String hotelName,
            @CookieParam("userRole") String userRole
    ){
        int httpStatus=0;
        if (userRole.equals("guest")||userRole.equals("user")) {
            httpStatus = 403;
        } else {
            httpStatus=200;
            room.setHotelName(hotelName);
        }

        DataHandler.insertRoom(room);
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * update room
     * @param room to set
     * @param hotelName to set
     * @return response
     */

    @POST
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateRoom(
            @Valid @BeanParam Room room,
            @FormParam("hotelName") String hotelName,
            @CookieParam("userRole") String userRole

    ){
        int httpStatus=200;
        if (userRole.equals("guest")||userRole.equals("user")) {
            httpStatus = 403;
        } else {

            Room oldRoom = DataHandler.readRoomByNumber(room.getRoomNumber());
            if (oldRoom != null) {
                oldRoom.setSize(room.getSize());
                oldRoom.setRoomNumber(room.getRoomNumber());
                oldRoom.setPriceNight(room.getPriceNight());
                oldRoom.setHotelName(hotelName);
                oldRoom.setPlaces(room.getPlaces());

                DataHandler.updateRoom();
            } else {
                httpStatus = 410;
            }
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();

    }

    /**
     * delete room
     * @param roomNumber to set
     * @return response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)

    public Response deleteBook(
            //here
            @NotNull
            @QueryParam("roomNumber") int roomNumber,
            @CookieParam("userRole") String userRole

    ) {
        int httpStatus = 200;
        if (userRole.equals("guest")||userRole.equals("user")) {
            httpStatus = 403;
        } else {
            if (!DataHandler.deleteRoom(roomNumber)) {
                httpStatus = 410;
            }

        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}