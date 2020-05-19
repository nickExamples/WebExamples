/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wb.restexamples;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import Entitys.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.DELETE;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import repositorys.CustomerEJB;

/**
 * REST Web Service
 *
 * @author Nick
 */
@Path("customer")
//@RequestScoped
@Stateless
public class GenericResource {

       
    @Context
    private UriInfo context;

    @Context
    private UriInfo uriInfo;
       
     
    @EJB
    CustomerEJB cejb;
    
     
    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }
    
    
    @GET
    @Path("getCustomers")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Customer> getCustomers(){
        
        
        return cejb.findAll_customer();
    }
    
    @POST
    @Path("addCustomer/{id}/{name}/{zipCode}/{disCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCustomer(@PathParam("id") int id,
                                @PathParam("name") String name,
                                @PathParam("zipCode") String zipCode,
                                @PathParam("disCode") String disCode){
    
    Customer cs = new Customer();
    Customer cs1;
    cs.setCustomerId(id);
    cs.setName(name);
    DiscountCode ds = new DiscountCode();
    MicroMarket mk = new MicroMarket();
    mk.setZipCode(zipCode);
    ds.setDiscountCode(disCode);
            
    cs.setDiscountCode(ds);
    cs.setZip(mk);
    
    cs1 = cejb.addCustomer(cs);
    URI customeruri = uriInfo.getBaseUriBuilder().path("customer/getCustomers/").path(cs1.getCustomerId().toString()).build();
    return Response.created(customeruri).build();
    }
    
    @DELETE
    @Path("removeCustomer/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeCustomer(@PathParam("id") int id){
        Customer cus;
        cus = cejb.removeCustomer(id);
    
        return Response.ok().build();
    }
    
    
    @GET
    @Path("getCustomers/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomersId(@PathParam("id") int id){
        
        
        Customer cs = cejb.findCustomerId(id);
                
        if(cs == null){
            throw new NotFoundException();
        }
               
        return Response.ok(cs).build();
        //return test;
    }
    
    @GET
    @Path("getCustomers/{id}/purchaces")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<PurchaseOrder> getCustomerPurchaseCollection(@PathParam("id") int id){
    
        return cejb.getCustomerIdPO(id);
    }
    
    
    @GET
    @Path("getCustomers/AllCustomerPurchaces")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<PurchaseOrder> getAllCustomerPurchaces(){
    
        return cejb.getAllCustomerPurchases();
    }
    
    

    /**
     * Retrieves representation of an instance of com.wb.restexamples.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("sayHello")
    @Produces(MediaType.APPLICATION_JSON)
    public String sayhello() {
       
        return "test";
        //throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
