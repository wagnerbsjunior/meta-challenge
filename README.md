# Challenge Meta - GIT Web Scraper

Develop an API that returns the file count and the total number of lines, grouped by file extension, of a given public Github repository. As in the example below:

### Example
```
[
    {
        "extension": "yml",
        "count": 1,
        "lines": 16
    }
]
```

## Requirements: :pencil2:

- Your API must be written using Java 8 or newer;
- Data must be retrieved from Github website by using web scraping techniques.
- Your API must support thousands of concurrent requests;
- It’s ok if the first request to a particular repository takes some time to respond (since you depend on Github website response times), but we don’t expect the next requests to take long;
- We don’t expect to get timeout errors;
- We’d like to see SOLID principles in your solution;
- You are free to choose your API contracts (parameters and response formats);

## Resources: :computer:

- **[IntelliJ IDEA Community Edition](https://www.jetbrains.com/pt-br/idea/download/#section=windows)**
    - **[Spring Framework](https://spring.io/projects/spring-framework)**
    - **[Gradle](https://gradle.org/)**
    - **[H2 Database](http://h2database.com/html/main.html)**
    - **[Lombok](https://projectlombok.org/)**
    - **[JSoup](https://jsoup.org/)**

## Sending requests: :computer:

###  GET
```
http://localhost:8080/api/scraper?repository=devdojobr/devdojo-microservices
```

## Access H2 Database: :computer:

- User name: sa
- Password: 

### testdb
```
http://localhost:8080/h2-console
```

