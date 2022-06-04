package ch.bzz.roomlist.service;

import ch.bzz.roomlist.data.DataHandler;
import ch.bzz.roomlist.model.Hotel;

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
     * reads a list of all hotels
     * @return  hotels as JSON
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
     * reads a hotel identified by the name
     * @param hotelName
     * @return name
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


    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertHotel(
            @FormParam("hotelName") String hotelName,
            @FormParam("number") int number
    ){
        Hotel hotel=new Hotel();
        hotel.setHotelName(hotelName);
        hotel.setNumber(number);

        DataHandler.insertHotel(hotel);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    @POST
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateHotel(
            @FormParam("hotelName") String hotelName,
            @FormParam("number") int number

    ){
        int httpStatus=200;
        Hotel hotel=DataHandler.readHotelByName(hotelName);
        if (hotel!=null){
            hotel.setHotelName(hotelName);
            hotel.setNumber(number);

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


