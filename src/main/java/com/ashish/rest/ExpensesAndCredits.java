package com.ashish.rest;

import com.google.appengine.api.utils.SystemProperty;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


//http://localhost:8080/ashish2/ExpensesAndCredits/get

@Path("/ExpensesAndCredits")
public class ExpensesAndCredits {

    final Logger log = Logger.getLogger(ExpensesAndCredits.class.getName());


    @POST
    @Path("/postExpense")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response postToExpensesTable ( String expense) throws IOException, ClassNotFoundException, SQLException {

        String url ;


        log.setLevel(Level.INFO);

        ObjectMapper objectMapper = new ObjectMapper();
        Expenses expenses1 = objectMapper.readValue(expense, Expenses.class);


        //--------------------------------------------------------------------------
                    log.info("at STAGE 1");
        //--------------------------------------------------------------------------




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

        //--------------------------------------------------------------------------
        log.info("at STAGE 2");
        //--------------------------------------------------------------------------

        long dateTime = new java.util.Date().getTime();
        Date date = new Date(dateTime);

        String query = "INSERT INTO Expenses(amount,reason,date) VALUES( ? , ? , ?)";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, expenses1.getAmount());
        stmt.setString(2, expenses1.getReason());
        stmt.setDate(3, date);

        int success =  stmt.executeUpdate();


        //--------------------------------------------------------------------------
        log.info("at STAGE 3");
        //--------------------------------------------------------------------------

        conn.close();

        //-------------------------------------------------------------------------
        log.info("at STAGE 4");
        //--------------------------------------------------------------------------

        Response.ResponseBuilder response = javax.ws.rs.core.Response.status(201);
        //-------------------------------------------------------------------------
        log.info("at STAGE 5");
        //--------------------------------------------------------------------------
        response.entity(success);
        //-------------------------------------------------------------------------
        log.info("at STAGE 6");
        //-------------------------------------------------------------------------
        Response response1 = response.build();
        //-------------------------------------------------------------------------
        log.info("at STAGE 7");
        //--------------------------------------------------------------------------

        return response1;
    }



    @GET
    @Path("/getExpense")
    @Produces(MediaType.TEXT_PLAIN)
    public String selectDataFromExpenses ( ) throws IOException, ClassNotFoundException, SQLException {

        String url ;
        log.setLevel(Level.INFO);

        //--------------------------------------------------------------------------
        log.info("at STAGE 1");
        //--------------------------------------------------------------------------




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

        //--------------------------------------------------------------------------
        log.info("at STAGE 2");
        //--------------------------------------------------------------------------

        String query = "SELECT * FROM Expenses";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        Map<Integer, Expenses> map = new HashMap<>();
        int index=1;


        while (rs.next())
        {
            int amount = rs.getInt(1);
            log.info(""+amount);
            String reason =  rs.getString(2);
            log.info(""+reason);
            java.sql.Date date = rs.getDate(3);
            log.info(""+date);

            map.put(index++, new Expenses(amount, reason, date));
        }

        json= objectMapper.writeValueAsString( map )  ;



        //--------------------------------------------------------------------------
        log.info("at STAGE 3");
        //--------------------------------------------------------------------------

        conn.close();

        //-------------------------------------------------------------------------
        log.info("at STAGE 4");
        //--------------------------------------------------------------------------

        return json;


    }



    @POST
    @Path("/postCredit")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response postToCreditsTable( String creditJSON) throws IOException, ClassNotFoundException, SQLException {

        String url ;
        log.setLevel(Level.INFO);
        ObjectMapper objectMapper = new ObjectMapper();
        Credit credit= objectMapper.readValue(creditJSON, Credit.class);

        //--------------------------------------------------------------------------
        log.info("at STAGE 1");
        //--------------------------------------------------------------------------

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
        //--------------------------------------------------------------------------
        log.info("at STAGE 2");
        //--------------------------------------------------------------------------

        long dateTime = new java.util.Date().getTime();
        Date date = new Date(dateTime);

        String query = "INSERT INTO Credits(amount,person,date) VALUES( ? , ? , ?)";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, credit.getAmount());
        stmt.setString(2, credit.getPerson());
        stmt.setDate(3, credit.getDate() );

        int success =  stmt.executeUpdate();

        //--------------------------------------------------------------------------
        log.info("at STAGE 3");
        //--------------------------------------------------------------------------

        conn.close();

        //-------------------------------------------------------------------------
        log.info("at STAGE 4");
        //--------------------------------------------------------------------------

        Response.ResponseBuilder response = javax.ws.rs.core.Response.status(201);
        response.entity(success);
        return response.build();

    }



    @GET
    @Path("/getCredit")
    @Produces(MediaType.TEXT_PLAIN)
    public String selectDataFromCredits() throws IOException, ClassNotFoundException, SQLException {

        String url,json ;
        log.setLevel(Level.INFO);

        //--------------------------------------------------------------------------
        log.info("at STAGE 1");
        //--------------------------------------------------------------------------




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

        //--------------------------------------------------------------------------
        log.info("at STAGE 2");
        //--------------------------------------------------------------------------

        String query = "SELECT * FROM Credits";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<Integer, Credit> map = new HashMap<>();
        int index=1;


        while (rs.next())
        {
            int amount = rs.getInt(1);
            log.info(""+amount);

            String person =  rs.getString(2);
            log.info(""+person);

            java.sql.Date date = rs.getDate(3);
            log.info(""+date);

            map.put(index++, new Credit( amount, person, date));
        }

        json= objectMapper.writeValueAsString( map )  ;



        //--------------------------------------------------------------------------
        log.info("at STAGE 3");
        //--------------------------------------------------------------------------

        conn.close();

        //-------------------------------------------------------------------------
        log.info("at STAGE 4");
        //--------------------------------------------------------------------------

        return json;


    }



    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public String get( ) throws IOException {
        ObjectMapper objectMapper= new ObjectMapper();
        log.setLevel(Level.INFO);


        //-------------------------------------------------------------------------
        log.info("at STAGE 1 in GET");
        //--------------------------------------------------------------------------
        java.util.Date date =new java.util.Date();

        return objectMapper.writeValueAsString(new Expenses(10, "EveSnacks", new Date( new java.util.Date().getTime())));
        //return ne;
    }


}

