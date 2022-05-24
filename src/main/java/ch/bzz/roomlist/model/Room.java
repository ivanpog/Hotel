package ch.bzz.roomlist.model;

import ch.bzz.roomlist.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

/**
 * a room in a hotel
 */
public class Room {
    @JsonIgnore
    private Hotel hotel;

    private int roomNumber;
    private String size;
    private BigDecimal priceNight;
    private String places;

    /**
     * gets the name of the rooms hotel
     * @return hotelName
     */
    public String getHotelName() {
        return getHotel().getHotelName();
    }

    /**
     * sets a name of rooms hotel
     * @param hotelName the value to set
     */
    public void setHotelName(String hotelName) {
        setHotel( new Hotel());
        Hotel hotel = DataHandler.getInstance().readHotelByName(hotelName);
        getHotel().setHotelName(hotelName);
        getHotel().setHotelName(hotel.getHotelName());

    }

    /**
     * gets hotel
     *
     * @return hotel
     */
    public Hotel getHotel() {
        return hotel;
    }

    /**
     * sets hotel
     *
     * @param hotel the value to set
     */
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * gets room number
     *
     * @return roomNumber
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * sets number of room
     *
     * @param roomNumber the value to set
     */
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * gets size
     *
     * @return size
     */
    public String getSize() {
        return size;
    }

    /**
     * sets size
     *
     * @param size the value to set
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * gets price pro night of room
     *
     * @return priceNight
     */
    public BigDecimal getPriceNight() {
        return priceNight;
    }

    /**
     * sets price pro night of room
     *
     * @param priceNight the value to set
     */
    public void setPriceNight(BigDecimal priceNight) {
        this.priceNight = priceNight;
    }

    /**
     * gets number of places in the room (how many people can sleep inside)
     *
     * @return places
     */
    public String getPlaces() {
        return places;
    }

    /**
     * sets number of places in room (how many people can sleep inside)
     *
     * @param places the value to set
     */
    public void setPlaces(String places) {
        this.places = places;
    }
}