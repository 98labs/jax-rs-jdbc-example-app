package com.example.app.web;

import com.example.app.model.Customer;
import com.example.app.service.CustomerService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerController {

    private final CustomerService customerService = new CustomerService();

    @GET
    public Response findAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return Response.ok(customers).build();
    }

    @GET
    @Path("/{id}")
    public Response findCustomerById(@PathParam("id") BigInteger id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        if (customer.isPresent()) {
            return Response.ok(customer.get()).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

//    @GET
//    @Path("/search")
//    public List<Customer> findByNameWithPaging(
//            @QueryParam("name") String name,
//            @QueryParam("page") @DefaultValue("1") int page,
//            @QueryParam("size") @DefaultValue("10") int size) {
//        return customerService.findByNameWithPaging(name, page, size);
//    }
//
//    @GET
//    @Path("/search/by-name")
//    public List<Customer> findByNameLike(@QueryParam("name") String name) {
//        return customerService.findByNameLike(name);
//    }
//
//    @GET
//    @Path("/search/by-email")
//    public Response findByEmail(@QueryParam("email") String email) {
//        Optional<Customer> customer = customerService.findByEmail(email);
//        if (customer.isPresent()) {
//            return Response.ok(customer.get()).build();
//        } else {
//            return Response.status(Status.NOT_FOUND).build();
//        }
//    }
//
//    @POST
//    public Response createCustomer(Customer customer) {
//        Customer createdCustomer = customerService.createCustomer(customer);
//        return Response.status(Status.CREATED).entity(createdCustomer).build();
//    }
//
//    @PUT
//    @Path("/{id}")
//    public Response updateCustomer(@PathParam("id") BigInteger id, Customer customer) {
//        Optional<Customer> updatedCustomer = customerService.updateCustomer(id, customer);
//        if (updatedCustomer.isPresent()) {
//            return Response.ok(updatedCustomer.get()).build();
//        } else {
//            return Response.status(Status.NOT_FOUND).build();
//        }
//    }
//
//    @DELETE
//    @Path("/{id}")
//    public Response deleteCustomer(@PathParam("id") BigInteger id) {
//        boolean deleted = customerService.deleteCustomer(id);
//        if (deleted) {
//            return Response.noContent().build();
//        } else {
//            return Response.status(Status.NOT_FOUND).build();
//        }
//    }
}
