package ch.bzz.roomlist.data;

import ch.bzz.roomlist.model.Room;
import ch.bzz.roomlist.model.Hotel;
import ch.bzz.roomlist.model.User;
import ch.bzz.roomlist.service.Config;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public class DataHandler {
    private static DataHandler instance;
    private static List<Room> roomList;
    private static List<Hotel> hotelList;
    private static List<User> userList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {

    }

    /**
     * reads all rooms
     * @return list of rooms
     */
    public static List<Room> readAllRooms() {
        return getRoomList();
    }

    /**
     * reads a room by its number
     * @param roomNumber
     * @return room
     */
    public static Room readRoomByNumber(Integer roomNumber) {
        Room room = null;
        for (Room entry : getRoomList()) {
            if (entry.getRoomNumber().equals(roomNumber)) {
                room = entry;
            }
        }
        return room;
    }

    /**
     * inserts a new room to the list
     * @param room the room to be saved
     */
    public static void insertRoom(Room room){
        getRoomList().add(room);
        writeRoomJSON();
    }

    /**
     * updates the list
     */
    public static void updateRoom(){
        writeRoomJSON();
    }

    /**
     * deletes a room identified by the number
     * @param roomNumber is the key
     * @return true or false
     */
    public static boolean deleteRoom(Integer roomNumber){
        Room room=readRoomByNumber(roomNumber);
        if(room!=null){
            getRoomList().remove(room);
            writeRoomJSON();
            return true;
        }else{
            return false;
        }
    }

    /**
     * reads all Hotels
     * @return list of hotels
     */
    public static List<Hotel> readAllHotels() {

        return getHotelList();
    }

    /**
     * reads a hotel by its name
     * @param hotelName
     * @return hotel
     */
    public static Hotel readHotelByName(String hotelName) {
        Hotel hotel = null;
        for (Hotel entry : getHotelList()) {
            if (entry.getHotelName().equals(hotelName)) {
                hotel = entry;
            }
        }
        return hotel;
    }

    /**
     * inserts a new hotel into the list
     *
     * @param hotel the hotel to be saved
     */
    public static void insertHotel(Hotel hotel) {
        getHotelList().add(hotel);
        writeHotelJSON();
    }

    /**
     * updates the list of hotels
     */
    public static void updateHotel() {
        writeHotelJSON();
    }

    /**
     * deletes a hotel identified by the name of hotel
     * @param hotelName  the key
     * @return  true or false
     */
    public static boolean deleteHotel(String hotelName) {
        Hotel hotel = readHotelByName(hotelName);
        if (hotel != null) {
            getHotelList().remove(hotel);
            writeHotelJSON();
            return true;
        } else {
            return false;
        }
    }


    /**
     * reads rooms from the JSON-file
     */
    private static void readRoomJSON() {
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
     * writes the list of rooms to the JSON-file
     */
    private static void writeRoomJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("roomJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getRoomList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * reads hotels from the JSON-file
     */
    private static void readHotelJSON() {
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
     * writes the list of hotels to the JSON-file
     */
    private static void writeHotelJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String bookPath = Config.getProperty("hotelJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getHotelList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the users from the JSON-file
     */
    private static void readUserJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("userJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            User[] users = objectMapper.readValue(jsonData, User[].class);
            for (User user : users) {
                getUserList().add(user);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String readUserRole(String username, String password) {
        for (User user : getUserList()) {
            if (user.getUsername().equals(username) &&
                    user.getPassword().equals(password)) {
                return user.getUsername();
            }
        }
        return "guest";
    }


    /**
     * gets roomList
     *
     * @return value of roomList
     */
    private static List<Room> getRoomList() {
        if (roomList == null) {
            setRoomList(new ArrayList<>());
            readRoomJSON();
        }
        return roomList;
    }

    /**
     * sets roomList
     *
     * @param roomList the value to set
     */
    private static void setRoomList(List<Room> roomList) {
        DataHandler.roomList = roomList;
    }

    /**
     * gets hotelList
     *
     * @return value of hotelList
     */
    private static List<Hotel> getHotelList() {
        if (hotelList == null) {
            setHotelList(new ArrayList<>());
            readHotelJSON();
        }
        return hotelList;
    }

    /**
     * sets hotelList
     *
     * @param hotelList the value to set
     */
    private static void setHotelList(List<Hotel> hotelList) {
        DataHandler.hotelList = hotelList;
    }

    /**
     * gets userList
     *
     * @return value of userList
     */

    public static List<User> getUserList() {
        if (DataHandler.userList == null) {
            DataHandler.setUserList(new ArrayList<>());
            readUserJSON();
        }
        return userList;
    }

    /**
     * sets userList
     *
     * @param userList the value to set
     */

    public static void setUserList(List<User> userList) {
        DataHandler.userList = userList;
    }
}