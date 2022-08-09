Microservices which I have implemented are 

1.account_service(Payment_service)
2.bankAccount_service
3.creditcard_service
4.paypal_service
5.product_service
6.order_service
7.transaction_service
8.shipping_service
9.eureka_service
10.cloudconfig_service
11.authentication_service
12.gateway_service


I use eureka for the discovery,cloud config and ApI key for the secret management approach

I have worked on authentication,account tracking,order tracking,payment and transactional service,stock tracking and shipping servvices

I use feign client to communicate microservices to each other, spring boot , mysql for the database

I have defined three roles for the project 
ADMIN,SHIPPER,USER

the main function of this project are given below

#1 create account
to purchase order at first user need to create account
 he/she can create account
To  create account need to POST request in postman with api

localhost:8000/accounts
( data need to create account)

{
"firstName":"priya",
"lastName":"giri",
"emailAddress":"test@gmail.com",
"password": "12345",
"preferred":"paypal",
"paypal":{
"emailAddress":"giripriya@gmail",
"secureKey":"2399"
},
"bankAccount":{
"routingNumber":2222,
"bankAccountNumber":"12345678",
"emailAddress":"gri@gmail.com",
"type":"SAVING"
},
"creditCard":{
"cardNumber":"111111111",
"ccv":"2118",
"expiryDate":"2024-11-11"
}
}

after requesting it will go to different payment method services(PAYPAL,CREDITCARD,BANKACCOUNT) and checks whether the given information
during request is valid or not
If it is invalid then it will show error message but if it is valid then it will create account successfully


#2 purchase order
To purchase orders user need to apply post request (User role is authorized, forbidden for admin and shipper)
 before that user need to authenticate his username and password for that you can
do post request with api (localhost:8000/authenticate) with body
{
"username": "test@gmail.com",
"password": "12345"
}

then it will generate token . By taking this token as a bearer token you can apply post request  with api
localhost:8000/order/purchase?purchaseMethod=PAYPAL(optional you can use creditcard as well as bankaccount)

 when user apply post request with any purchase method then it will go to the payment service which you choose to purchase order if the information 
about that purchase method service is valid then you can successfullly purchase order if there is something wrong in your purchase method sercices informatin
 (like invalid infromation ,balance is low in purchase method account ) then it wiill give error message.

if it is successfull then the  paymment information is stored in transaction service and
with shipping baseed information in shipping service
[
{
"productCode":"A101",
"quantity":7
},
{
"productCode":"A102",
"quantity":0
},
{
"productCode":"A103",
"quantity":2
},
{
"productCode":"A104",
"quantity":1
}

]

you can test with this data in the body with bearer token and with api
localhost:8000/order/purchase?purchaseMethod=PAYPAL

#3(see status of order)
after purchasing of order  it will generate shipping code then with that shipping
code you can see the shipping status of order you have purchase
for this you can simply do GET request with api
localhost:8000/shippings/status?shippingCode=S101

#4 updating shipping status of order(only Shipper role is authorized)
to update staus you need to get token of shipper for this . 
Post request with api
localhost:8000/authenticate
with body

{
"username": "shipper@gmail.com",
"password": "shipper"
}

and it will generate token and use this token as a barrier token 
and do POST request with api
localhost:8000/shippings?shippingCode=S101&status=DELIVERED

 then status of the shippingCode S101 will changed from shipped to delivered

#5 to add new product(Only admin role is authorized)
to update staus you need to get token of admin for this .
Post request with api
localhost:8000/authenticate
with body

{
"username": "admin@gmail.com",
"password": "admin"
}
and it will generate token and use this token as a barrier token
and do POST request with api

localhost:8000/products
and with the body
{
"productName": "ABC BCD",
"price": 10.2,
"vendor": "Prakash Dahal",
"productCategory": "FOOD",
"availableUnit": 22
}
It will save new product on the stock






