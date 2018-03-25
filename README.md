# Placements.io Coding Project By Jong young Chung

## Project Platform
I chosed HTML(client), Spring Boot(WAS) and H2(database), because those are platform independent, easy to deploy and easy to set up.

## Selected Requirements
These features are simple, but it could show the entire client server flow clearly.

### Bucket 1:
- The user should be able browse through the line-item data as either a list or table (pagination).
- The user should be able to see sub-totals grouped by campaign (line-items grouped by their parent campaign).
- The user should be able to output the invoice to *.CSV, *.XLS, etc.

### Bucket 2:
- The user should be able to filter the data (campaign name).
- The user should be able to share and reuse filters between users.
- The user should be able to add comments on an individual line-item.

## Data(json) Import
When the server up, the placements_teaser_data.json is automatically imported.

## Design
| View|Controller|Model|
|-|-|-|
|Item List|ItemController|ItemRepository, Item|
|(HTML/Ajax)|(@RestController)|(Jpa/H2, Class)|

Simple MVC pattern was applied and common web technology was used to fetch the list and apply the filter for the View.

## Trade-offs
In this project, h2 was selected as the database for convenience of depoyment. It may be possible to use RDBMS with better performance or NOSQL considering distributed environment.
Time space trade-offs.

## Dependencies
|Server|Client|
|-|-|
|Java 8|HTML5|
|All items(include database) are bound to a jar, so you do not need to install them separately.|No need to be installed separately.|

## How to Setup/Run
0. Install JRE 8
http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html

1. Run Server
$ java -jar ./placements.io-0.0.1-SNAPSHOT.jar

2. Run Client
http://localhost:8080/list.html

## Test
Described some unit test code to test ItemRepository in /placements.io/src/test/java/io/placements/challenge/jong/ItemRepositoryTest.java
