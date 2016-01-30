package com.ashish.rest;

import com.google.appengine.api.utils.SystemProperty;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;


//http://localhost:8080/ashish2/TrialPOST/get

@Path("/TrialPOST")
public class TrialPOST {

    final Logger log = Logger.getLogger(TrialPOST.class.getName());


    @POST
    @Path("/post")
    @Consumes(MediaType.TEXT_PLAIN)
    public javax.ws.rs.core.Response responseMsg ( String model) throws IOException, ClassNotFoundException, SQLException {

        String url ;



        ObjectMapper objectMapper = new ObjectMapper();
        Model model1 = objectMapper.readValue(model, Model.class);


        if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production)
        {
            Class.forName("com.mysql.jdbc.GoogleDriver");
            url = "jdbc:google:mysql://intensehelloworlder:intense-database2/Test";
        }
        else
        {
            Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://localhost:3306/test"; // jdbc:mysql://ip-address-of-google-cloud-sql-instance:3306/guestbook?user=root
        }


        Connection conn = DriverManager.getConnection(url, "root", "stunner28");

        String query = "INSERT INTO Expenses(amount,reason) VALUES( ? , ? )";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1,/*model1.getAmount()*/100);
        stmt.setString(2, /*model1.getReason()*/"FirstTry");
        int success =  stmt.executeUpdate();
        conn.close();

        return javax.ws.rs.core.Response.status(201).entity(success).build();
    }



    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public String get( ) throws IOException {
        ObjectMapper objectMapper= new ObjectMapper();

        String jsonFromObject = objectMapper.writeValueAsString(new Model(10, "EveSnacks"));
        return jsonFromObject;
        //return ne;
    }


}

