package ch.bzz.booklist.data;

import ch.bzz.booklist.model.Room;
import ch.bzz.booklist.model.Hotel;
import ch.bzz.booklist.service.Config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public class DataHandler {
    private static DataHandler instance = null;
    private List<Room> roomList;
    private List<Hotel> hotelList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setHotelList(new ArrayList<>());
        readHotelJSON();
        setRoomList(new ArrayList<>());
        readRoomJSON();
    }

    /**
     * gets the only instance of this class
     * @return
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    /**
     * reads all books
     * @return list of books
     */
    public List<Room> readAllRooms() {
        return getRoomList();
    }

    /**
     * reads a book by its uuid
     * @param roomNumber
     * @return the Book (null=not found)
     */
    public Room readRoomByNumber(int roomNumber) {
        Room room = null;
        for (Room entry : getRoomList()) {
            if (entry.getRoomNumber()==roomNumber) {
                room = entry;
            }
        }
        return room;
    }

    /**
     * reads all Publishers
     * @return list of publishers
     */
    public List<Hotel> readAllHotels() {

        return getHotelList();
    }

    /**
     * reads a publisher by its uuid
     * @param hotelName
     * @return the Publisher (null=not found)
     */
    public Hotel readHotelByName(String hotelName) {
        Hotel hotel = null;
        for (Hotel entry : getHotelList()) {
            if (entry.getHotelName().equals(hotelName)) {
                hotel = entry;
            }
        }
        return hotel;
    }

    /**
     * reads the books from the JSON-file
     */
    private void readRoomJSON() {
        try {
            String path = Config.getProperty("roomJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Room[] rooms = objectMapper.readValue(jsonData, Room[].class);
            for (Room room : rooms) {
                getRoomList().add(room);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the publishers from the JSON-file
     */
    private void readHotelJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("hotelJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Hotel[] hotels = objectMapper.readValue(jsonData, Hotel[].class);
            for (Hotel hotel : hotels) {
                getHotelList().add(hotel);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * gets bookList
     *
     * @return value of bookList
     */
    private List<Room> getRoomList() {
        return roomList;
    }

    /**
     * sets bookList
     *
     * @param roomList the value to set
     */
    private void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    /**
     * gets publisherList
     *
     * @return value of publisherList
     */
    private List<Hotel> getHotelList() {
        return hotelList;
    }

    /**
     * sets publisherList
     *
     * @param hotelList the value to set
     */
    private void setHotelList(List<Hotel> hotelList) {
        this.hotelList = hotelList;
    }


}