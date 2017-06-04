package android.ebozkurt.com.cs308ticket.network;

import android.ebozkurt.com.cs308ticket.domain.Event;
import android.ebozkurt.com.cs308ticket.domain.Ticket;
import android.ebozkurt.com.cs308ticket.domain.User;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by erdem on 3.06.2017.
 */

public interface TicketApiInterface {

    @POST("user/register")
    Call<Void> registerUser(@Body User user);

    @POST("user/login")
    Call<ResponseBody> loginUser(@Body User user);

    @GET("user/secure/getallusers")
    Call<ArrayList<User>> getAllUsers(@Header("Authorization") String authorization);

    @GET("user/secure/getmyinfo")
    Call<User> getMyInfo(@Header("Authorization") String authorization);

    @POST("user/secure/addadminbyemail")
    Call<ArrayList<User>> addAdminByEmail(@Header("Authorization") String authorization, @Body User user);

    @POST("user/secure/removeadminbyemail")
    Call<ArrayList<User>> removeAdminByEmail(@Header("Authorization") String authorization, @Body User user);

    @POST("event/secure/add")
    Call<Void> addEvent(@Header("Authorization") String authorization, @Body Event event);

    @POST("event/secure/remove")
    Call<Void> removeEvent(@Header("Authorization") String authorization, @Body Event event);

    @GET("event/getallevents")
    Call<ArrayList<Event>> getAllEvents();

    @GET("event/geteventbyid")
    Call<Event> getEventById(@Body Event event);

    @POST("ticket/secure/getAllTicketsForEvent")
    Call<ArrayList<Ticket>> getAllTicketsForEvent(@Header("Authorization") String authorization, @Body Event event);

    @POST("ticket/secure/createTicket")
    Call<Void> createTicket(@Header("Authorization") String authorization, @Body Ticket ticket);

    @POST("ticket/secure/getAllTicketsForUser")
    Call<ArrayList<Ticket>> getAllTicketsForUser(@Header("Authorization") String authorization, @Body User user);

    @POST("ticket/getEvent")
    Call<Event> getEvent(@Body Ticket ticket);

    @POST("ticket/secure/getAllTickets")
    Call<ArrayList<Ticket>> getAllTickets(@Header("Authorization") String authorization);
}
