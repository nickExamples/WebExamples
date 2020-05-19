# Rest api example

This is a rest api example exposing database operations in JSON, using java jee.

Launch browser with : http://localhost:8080/restExamples/api/customer/getCustomers

base url : restExamples/api

/customer --> for all customer related information

@GET /getCustomers  -->  returns all customers.

@GET /getCustomers/{id} --> returns specific customer.

@GET /getCustomers/{id}/purchaces --> returns purchaces made by a specific customer.

@GET /getCustomers/AllCustomerPurchaces --> returns all the purchases of customers.

@POST /addCustomer/{id}/{name}/{zipCode}/{discountCode}

@DELETE /removeCustomer/{id}

