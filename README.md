I have used Java 11 and Spring Boot v2.7.17 Also, I have used in-memory h2 database.

Insomnia collection with all APIs has been added in the 'insomnia-collection' directory.

<h4>There are four API's: </h4>

1. POST - http://localhost:1992/fridge/api/v1/createUser
2. POST - http://localhost:1992/fridge/api/v1/createAndUpdateProduct
3. GET - http://localhost:1992/fridge/api/v1/expiring-soon/userId?days=1
4. http://localhost:1992/fridge/api/v1/timeStored/userId

<h4> One simple API, just to see data via simple HTML: </h4>
http://localhost:1992/fridge/view/timeStored

<h4>Scheduler Job for storing expired products </h4>
NotificationService.java