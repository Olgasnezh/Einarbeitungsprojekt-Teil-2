package com.programmerscuriosity;

import com.daojpa.model.Customer;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.QueryParam;
import javax.persistence.RollbackException;
import javax.persistence.EntityManager;



/**
 * Created on 28/05/18.
 */

@Path("mce_project_2")

public class MCE {

    private EntityManagerFactory currentSession;
    private static EntityManager em;


    private EntityManager createEntityManagerPrivate(){
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("Customer");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        return em;
    }


    @GET
    @Produces(MediaType.TEXT_HTML)
    public String helloWorld() {
        String ret = "<html>\n" +
                "<head>\n" +
                "<title>Einarbeitungsprojekt MCE Teil 2</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<p><a href=\"mce_project_2/createcustomer?id=678323&firstName=Olga&secondName=Perevalova&email=olga...@gmail.com&phone=+49...\">Example look-up for createCustomer: person id = 678323, first name = Olga, " +
                "last name = Perevalova, email = olga...@gmail.com, phone =  +49...</a>\n" +
                "<p><a href=\"mce_project_2/createcustomer?id=678324&firstName=Olga&secondName=Perevalova\">Example look-up for createCustomer: person id = 678324, first name = Olga, " +
                "last name = Perevalova</a>\n" +
                "<p><a href=\"mce_project_2/deletecustomer?id=678324\">Example look-up for deleteCustomer: person id = 678324</a>\n"+
                "<p><a href=\"mce_project_2/updatecustomer?id=678323&firstName=Elena\">Example look-up for updateCustomer: person id = 678323, first name = Elena</a>\n"+
                "<p><a href=\"mce_project_2/searchcustomer?id=678323\">Example look-up for searchCustomer: person id = 678323</a>\n"+
                "<body>\n" +
                "</html>";

        return ret;
    }

   @GET
   @Produces(MediaType.TEXT_HTML)
   @Path("/createcustomer")

   public String createCustomer(@QueryParam("id") int id, @QueryParam("firstName") String firstName, @QueryParam("secondName") String secondName, @QueryParam("email") String email, @QueryParam("phone") String phone) {

       String ret = "Success";
       try {
    em = createEntityManagerPrivate();
    Customer emp = new Customer(id, firstName, secondName, email, phone);
    em.persist(emp);
    em.getTransaction().commit();

    }

       catch (RollbackException e) {
    ret = e.getMessage();
    }
       return ret;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/deletecustomer")

    public String deleteCustomer(@QueryParam("id") int id){
        String ret = "Success";
        try {
            em = createEntityManagerPrivate();
            Customer emp = em.find(Customer.class, id);
            em.remove(emp);
            em.getTransaction().commit();
        }

        catch (IllegalArgumentException e) {
            ret = e.getMessage();
        }
       return ret;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/searchcustomer")

    public String searchCustomer(@QueryParam("id") int id){
        String ret;
        try {
            em = createEntityManagerPrivate();

            Customer emp = em.find(Customer.class, id);

            ret =" Person Id: "+emp.getId()+ " First name: " + emp.getFirstName()+ " Second Name: " + emp.getSecondName()+ " Email: " + emp.getEmail()+ " Phone: " + emp.getPhone();
            em.getTransaction().commit();
        }

        catch (IllegalArgumentException e)
        {
            ret = e.getMessage();
        }
        return ret;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/updatecustomer")

    public String updateCustomer(@QueryParam("id") int id, @QueryParam("firstName") String firstName, @QueryParam("secondName") String secondName, @QueryParam("email") String email, @QueryParam("phone") String phone)
    {
        String ret = "Success";
        try {
            em = createEntityManagerPrivate();

            Customer emp = em.find(Customer.class, id);

            if (firstName!=null){
            emp.setFirstName(firstName);
            }
            if(secondName!=null) {
                emp.setSecondName(secondName);
            }
            if(email!=null) {
                emp.setEmail(email);
            }
            if(phone!=null){
                emp.setPhone(phone);
            }

            em.getTransaction().commit();
        }

        catch (IllegalArgumentException e) {
            ret = e.getMessage();
        }
        return ret;

    }

}

