package ch.bzz.roomlist.service;

import ch.bzz.roomlist.data.DataHandler;
import ch.bzz.roomlist.model.Hotel;
import ch.bzz.roomlist.model.Room;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Path ("hotel")
public class HotelService {


    /**
     * get list of hotels
     * @return response
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listHotels(
            @CookieParam("userRole") String userRole
    ) {
        List<Hotel> hotelList=null;
        int httpStatus;
        if (userRole.equals("guest")){
            httpStatus=403;
        }else{
            hotelList = DataHandler.readAllHotels();
            httpStatus=200;
        }

        return Response
                .status(httpStatus)
                .entity(hotelList)
                .build();
    }

    /**
     * read hotel in list
     * @param hotelName to set
     * @return response
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readPublisher(
            //here
            @NotEmpty
            @QueryParam("hotelName") String hotelName,
            @CookieParam("userRole") String userRole


    ) {
        Hotel hotel=null;
        int httpStatus = 200;
        if (userRole.equals("guest")) {
            httpStatus = 403;
        } else {

                hotel = DataHandler.readHotelByName(hotelName);
                if (hotel == null) {
                    httpStatus = 410;
                }

            }
            return Response
                    .status(httpStatus)
                    .entity(hotel)
                    .build();
        }



    /**
     * create hotel
     * @param hotel to set
     * @return response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertHotel(
            @Valid @BeanParam Hotel hotel,
            @CookieParam("userRole") String userRole
    ){
        int httpStatus=0;
        if (userRole.equals("guest")||userRole.equals("user")) {
            httpStatus = 403;
        } else {
            httpStatus = 200;
            DataHandler.insertHotel(hotel);
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * update hotel in list
     * @param hotel to set
     * @return response
     */
    @POST
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateHotel(
            @Valid @BeanParam Hotel hotel,
            @CookieParam("userRole") String userRole
    ){
        int httpStatus=200;
        if (userRole.equals("guest")||userRole.equals("user")) {
            httpStatus = 403;
        } else {
            Hotel oldHotel = DataHandler.readHotelByName(hotel.getHotelName());
            if (oldHotel != null) {
                oldHotel.setHotelName(hotel.getHotelName());
                oldHotel.setNumber(hotel.getNumber());

                DataHandler.updateHotel();
            } else {
                httpStatus = 410;
            }
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();


    }


    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)

    public Response deleteHotel(
            //here
            @NotEmpty
            @QueryParam("hotelName") String hotelName,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        if (userRole.equals("guest") || userRole.equals("user")) {
            httpStatus = 403;
        } else {
            if (!DataHandler.deleteHotel(hotelName)) {
                httpStatus = 410;
            }

        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();

    }
}