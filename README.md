# Simple Java REST API

REST API built in using HttpServer that accepts user inputs relating to IT:
- Technology (e.g., Cloud Computing, Cybersecurity, DevOps)
- Role (e.g., System Administrator, Software Engineer, Security Analyst)
- Environment (e.g., Enterprise Network, Cloud Infrastructure, On-Prem Data Center)

And returns a randomly generated IT scenario.


## Requirements

- **Java 21**
- **Maven 3.8+**


## Installation

Clone the repository and build with Maven:

```bash
git clone https://github.com/yourusername/simple-java-rest-api.git
cd simple-java-rest-api
mvn clean install


## Use
Make a GET request with the query parameters:

For example
http://localhost:8080/api?technology=Cloud%20Computing&role=System%20Administrator&enviroment=Enterprise%20Network
