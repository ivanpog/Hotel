package ch.bzz.roomlist.service;

import ch.bzz.roomlist.data.DataHandler;
import ch.bzz.roomlist.model.Room;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("room")
public class RoomService {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listRooms() {
        List<Room> roomList = DataHandler.getInstance().readAllRooms();
        return Response
                .status(200)
                .entity(roomList)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readBook(
            @QueryParam("number") int roomNumber
    ) {
        Room room = DataHandler.getInstance().readRoomByNumber(roomNumber);
        return Response
                .status(200)
                .entity(room)
                .build();
    }
}
