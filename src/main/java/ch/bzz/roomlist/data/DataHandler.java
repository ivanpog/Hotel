package ch.bzz.roomlist.data;

import ch.bzz.roomlist.model.Room;
import ch.bzz.roomlist.model.Hotel;
import ch.bzz.roomlist.service.Config;

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
     * reads all rooms
     * @return list of rooms
     */
    public List<Room> readAllRooms() {
        return getRoomList();
    }

    /**
     * reads a room by its number
     * @param roomNumber
     * @return room
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
     * reads all Hotels
     * @return list of hotels
     */
    public List<Hotel> readAllHotels() {

        return getHotelList();
    }

    /**
     * reads a hotel by its name
     * @param hotelName
     * @return hotel
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
     * reads rooms from the JSON-file
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
     * reads hotels from the JSON-file
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
     * gets roomList
     *
     * @return value of roomList
     */
    private List<Room> getRoomList() {
        return roomList;
    }

    /**
     * sets roomList
     *
     * @param roomList the value to set
     */
    private void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    /**
     * gets hotelList
     *
     * @return value of hotelList
     */
    private List<Hotel> getHotelList() {
        return hotelList;
    }

    /**
     * sets hotelList
     *
     * @param hotelList the value to set
     */
    private void setHotelList(List<Hotel> hotelList) {
        this.hotelList = hotelList;
    }


}