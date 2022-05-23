package ch.bzz.booklist.model;

import ch.bzz.booklist.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

/**
 * a book in the bookshelf
 */
public class Room {
    @JsonIgnore
    private Hotel hotel;

    private int roomNumber;
    private String size;
    private BigDecimal priceNight;
    private String places;

    /**
     * gets the publisherUUID from the Publisher-object
     * @return
     */
    public String getHotelName() {
        return getHotel().getHotelName();
    }

    /**
     * creates a Publisher-object without the booklist
     * @param hotelName
     */
    public void setHotelName(String hotelName) {
        setHotel( new Hotel());
        Hotel hotel = DataHandler.getInstance().readHotelByName(hotelName);
        getHotel().setHotelName(hotelName);
        getHotel().setHotelName(hotel.getHotelName());

    }

    /**
     * gets publisher
     *
     * @return value of publisher
     */
    public Hotel getHotel() {
        return hotel;
    }

    /**
     * sets publisher
     *
     * @param hotel the value to set
     */
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * gets bookUUID
     *
     * @return value of bookUUID
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * sets bookUUID
     *
     * @param roomNumber the value to set
     */
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * gets title
     *
     * @return value of title
     */
    public String getSize() {
        return size;
    }

    /**
     * sets title
     *
     * @param size the value to set
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * gets author
     *
     * @return value of author
     */
    public BigDecimal getPriceNight() {
        return priceNight;
    }

    /**
     * sets author
     *
     * @param priceNight the value to set
     */
    public void setPriceNight(BigDecimal priceNight) {
        this.priceNight = priceNight;
    }


    /**
     * gets price
     *
     * @return value of price
     */


    /**
     * sets price
     *
     * @param price the value to set
     */


    /**
     * gets isbn
     *
     * @return value of isbn
     */
    public String getPlaces() {
        return places;
    }

    /**
     * sets isbn
     *
     * @param places the value to set
     */
    public void setPlaces(String places) {
        this.places = places;
    }
}