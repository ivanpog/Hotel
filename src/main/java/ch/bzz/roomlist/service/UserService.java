package ch.bzz.roomlist.service;

import ch.bzz.roomlist.data.UserData;
import ch.bzz.roomlist.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

/**
 * services for authentication and authorization of users
 */

@Path("user")
public class UserService {

    @POST
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(
            @FormParam("username") String username,
            @FormParam("password") String password
    )
    {
        int httpStatus;

        User user= UserData.findUser(username, password);

        if (user.getRole().equals("guest")){
            httpStatus=404;
        }else{
            httpStatus=200;
        }
        NewCookie cookie=new NewCookie(
                "userRole",
                user.getRole(),
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );
        Response response=Response
                .status(httpStatus)
                .entity("")
                .cookie(cookie)
                .build();
        return response;
    }

    @DELETE
    @Path("logout")
    @Produces(MediaType.TEXT_PLAIN)
    public Response logout(){
        NewCookie cookie=new NewCookie(
                "userRole",
                "guest",
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );
        Response response=Response
                .status(200)
                .cookie(cookie)
                .entity("")
                .build();
        return response;
    }


}