# REST api example
---
This is a rest api example exposing database operations in JSON, using java jee.

Launch browser with : http://localhost:8080/restExamples/api/customer/getCustomers

base url : restExamples/api

/customer --> for all customer related information

@GET /getCustomers  -->  returns all customers.

@GET /getCustomers/{id} --> returns specific customer.

@GET /getCustomers/{id}/purchaces --> returns purchaces made by a specific customer.

@GET /getCustomers/AllCustomerPurchaces --> returns all the purchases of customers.

@POST /addCustomer/{id}/{name}/{zipCode}/{discountCode} --> adds a customer to the database with minimal required information.

@POST /addCustomer  --> adds a customer consuming a json object representing a customer.

@DELETE /removeCustomer/{id}  --> removes a customer from database.


---
## Example results
#### /getCustomers
[![get-Customers-results.png](https://i.postimg.cc/nr98V6dP/get-Customers-results.png)](https://postimg.cc/0KvXcH1Y)

#### /addCustomer
[![add-Customer-results.png](https://i.postimg.cc/gjBZDx8n/add-Customer-results.png)](https://postimg.cc/bSkrYN6h)

#### /removeCustomer/{id}
[![remove-Customer-ID-results.png](https://i.postimg.cc/PrWLJVFC/remove-Customer-ID-results.png)](https://postimg.cc/CzKM6700)

#### /getCustomers/{id}/purchaces
[![get-Customer-Id-Purchaces.png](https://i.postimg.cc/nrtFtpx0/get-Customer-Id-Purchaces.png)](https://postimg.cc/7Gm83vf2)

