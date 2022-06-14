package ch.bzz.roomlist.service;

import ch.bzz.roomlist.data.DataHandler;
import ch.bzz.roomlist.model.Hotel;

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
    public Response listHotels() {
        List<Hotel> hotelList = DataHandler.readAllHotels();
        return Response
                .status(200)
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
            @QueryParam("hotelName") String hotelName
    ) {
        int httpStatus = 200;
        Hotel hotel = DataHandler.readHotelByName(hotelName);
        if (hotel == null) {
            httpStatus = 410;
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
            @Valid @BeanParam Hotel hotel
    ){

        DataHandler.insertHotel(hotel);
        return Response
                .status(200)
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
            @Valid @BeanParam Hotel hotel
    ){
        int httpStatus=200;
        Hotel oldHotel=DataHandler.readHotelByName(hotel.getHotelName());
        if (oldHotel!=null){
            oldHotel.setHotelName(hotel.getHotelName());
            oldHotel.setNumber(hotel.getNumber());

            DataHandler.updateHotel();
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

    public Response deleteHotel(
            //here
            @NotEmpty
            @QueryParam("hotelName") String hotelName
    ){
        int httpStatus=200;
        if(!DataHandler.deleteHotel(hotelName)){
            httpStatus=410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();

    }
}