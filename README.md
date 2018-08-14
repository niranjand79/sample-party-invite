
* The overall project structure is based of services. database-service--> entity module --> Validation service --> web controller

1) API's exposed:

	1) POST : localhost:8080/eventservice/invitation - This will create an event and invitations along with it in the same request.
sample input json:

	{
		"name": "birthday",
		"hostFirstName": "Niranjan",
		"hostLastName": "Deshpande",
		"eventMessage":"Please attend",
		"eventDate":"Sat, 3 Feb 2018 05:30:00 PDT",
		"location":"CMR",
		"invitations":[
			{
				"recipientFirstName":"Mikcey",
				"recipientLastName":"Mouse",
				"recipientEmail":"mouse@gmail.com"
			},
			{
				"recipientFirstName":"Donald",
				"recipientLastName":"Duck",
				"recipientEmail":"donald@gmail.com"
			}
			
		]
}

2) PUT : localhost:8080/eventservice/invitation  - Update an invitation to an event (ACCEPT:1, REJECT:2, May Be:3, Not responded:0 (default while creating invitation))
sample input json:
{
	"id":1,     // we can get this by querying the database. It's generally starts at 1.
	"eventId":1, // we can get this by querying the database. It's generally starts at 1.
	"response":1, //  enum values from above
	"responseMessage":"Thank you for inviting"
}

3) PUT : localhost:8080/eventservice/event - update an event (New:0, Completed:1, Cancelled:2)

sample input json:

{
		"name": "birthday",
		"hostFirstName": "Niranjan",
		"hostLastName": "Deshpande",
		"eventMessage":"Please attend",
		"eventDate":"Sat, 3 Feb 2018 05:30:00 PDT",
		"location":"CMR",
		"id":1,
		"status":2
}

4) GET: localhost:8080/eventservice/events/1/responses - displays responses for an event
sample output:
{
    "event": {
        "id": 1,
        "name": "birthday",
        "hostFirstName": "Niranjan",
        "hostLastName": "Deshpande",
        "location": "CMR",
        "eventDate": 1517661000000,
        "eventMessage": "Please attend",
        "status": "NEW"
    },
    "totalInvitations": 2,
    "attending": 2,
    "notAttending": 0,
    "tentative": 0,
    "notResponded": 0
}

2) schema.sql will create tables in h2 database once you load the application using spring boot starter.

3) You can compile the application using parent-module pom.xml (mvn clean install) and once it successfully compiles, run the application by runnung AppMain.java from web-controllers module or alternatively executing mvn:spring-boot:run from web-controller package.
4) I wanted to further break the module to more services, add test cases and add more improvements however I couldn't get enough time for that due to my schedule. However, I'm willing to discuss more.
