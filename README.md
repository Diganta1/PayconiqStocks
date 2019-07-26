# PayconiqStocks
Stock Services
Service

Its's a Stock solution MVP where we have defined 4 end points for doing CRUD operations on stock and price table.
For databse table we have choosen H2 in memory database which is quite handy and familier as well.

## Database components 

(http://localhost:8080/h2-console/):
Parameters:
Saved settings: Generic H2(Embeded)
Driver Class: org.h2.Driver
JDBC Url: jdbc:h2:mem:testdb
User name: sa

(Insert attribute to table static): 

insert into stock values(10001,'sogeti');
insert into stock values(10002,'ABN AMRO');

insert into price values(11001,200.05,TO_DATE(sysdate, 'yyyy-mm-dd hh24:mi:ss'),10001);
insert into price values(11002,200.07,TO_DATE(sysdate, 'yyyy-mm-dd hh24:mi:ss'),10001);
insert into price values(11003,200.09,TO_DATE(sysdate, 'yyyy-mm-dd hh24:mi:ss'),10001);
insert into price values(11004,200.09,TO_DATE(sysdate, 'yyyy-mm-dd hh24:mi:ss'),10002);


INSERT INTO tbl_user (id, username, password, salary, age) VALUES (1, 'digi',   '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu', 3456, 33);

# (Try everything with postman tool)

# Crud Service operations

Applied OAuth2 Security, hence just Pass the authentication user and validate it against database , get a dynamic access token and pass it on header of the other controller services.

   # End point for security  :
   
   Settings : Under "Authorization" tab of postman pass the (Type : Basic Auth)
   Username: stock
   password: stock
   
   "Headers" 
   Accept : Application/json
   
   "Body"      form-data
   
      Key                value
   
     Username           digi
     password           password
     grant_type         password
   
  # POST: localhost:8080/oauth/token
   
   Response: 
   
    {
    "access_token": "bbc50a3c-25fa-4141-9b62-cab9fa8dfa96",
    "token_type": "bearer",
    "refresh_token": "168eb83a-992a-4b4a-9fbc-e892f2c0217d",
    "expires_in": 3599,
    "scope": "read write trust"
    }

#<After generating the access_token pass it in header of other services to validate>

## This GET method will show all 
GET: localhost:8080/api/stocks?access_token=<bbc50a3c-25fa-4141-9b62-cab9fa8dfa96>
  
  Response:      Always display latest timestamp price.   [200:OK]
  
    {
    "listOfStock": [
        {
            "id": 3,
            "name": "12345",
            "latestPrice": {
                "prices": 87689.0,
                "timestamp": "2019-07-25T21:32:24.006+0000"
            }
        },
        {
            "id": 10001,
            "name": "sogeti",
            "latestPrice": {
                "prices": 200.05,
                "timestamp": "2019-07-25T21:28:34.000+0000"
            }
        }
              ]
    }
  
## This GET method will return response to the perticular ID:
GET: localhost:8080/api/stocks/{10001}?access_token=<11879d38-c264-4bf9-b037-e38f48762b4b>

[If ID is not present] .    [404:Not Found]
  
  Response:
  
       
    {
    "timestamp": "2019-07-26T09:02:25.877+0000",
    "message": "Stock 1000 does not exists"
    }

[If ID is present] .       [200:OK]

 Response:
 
       
    {
    "id": 10001,
    "name": "sogeti",
    "latestPrice": {
        "prices": 200.05,
        "timestamp": "2019-07-26T08:02:44.000+0000"
                    }
     }

  
## This POST method will accept new stock name and associate price and response back with success or fail with ID and timestamp
POST: localhost:8080/api/stocks/?access_token=eb07fe2b-65a5-4bf3-850a-0ee1b6a6a1be

  Request: 
  
  {
       
       "name": PayConiq,
       "prices":[
       {
            
            "prices": 200.08
            
        }
                ]  
       
    }
    
   Response:           [200:OK]
    
    {
    "id": 1,
    "name": "PayConiq",
    "prices": [
        {
            "prices": 200.08,
            "timestamp": "2019-07-26T08:54:46.993+0000"
        }
    ]
}

[If you will re insert the same content in request ]:

  Response:         [400:Bad Request]
  
    {
    "timestamp": "2019-07-26T08:55:51.108+0000",
    "message": "Cannot insert the same record details"
    }

  ## This PUT method will accept price for available stock and response with success or failed message
  
  PUT: localhost:8080/api/stocks/{4}?access_token=<eb07fe2b-65a5-4bf3-850a-0ee1b6a6a1be>
    
    
   Request:  
    
         {
	           "price":"87689"
         }
        
    
   Response:
    
   [If ID is not present] .      [404: Not Found]
    
    {
    "timestamp": "2019-07-26T09:00:28.457+0000",
    "message": "Stock 4 does not exists"
     }

[If stock id is available] .      [200 OK]

"Stock 1 is updated successfully"




    
         
    
     
    

    
    

   
