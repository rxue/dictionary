# RESTful API Design
## Critial Questions
### Should database id be exposed to the endpoint URL path, e.g. sth like `/lexical_item/1`

# Quarkus
## [Dev services for database](https://quarkus.io/guides/databases-dev-services#:~:text=Dev%20Services%20for%20databases%20automatically,The%20application%20is%20configured%20automatically.&text=Dev%20Services%20for%20databases%20relies,which%20is%20run%20in%20process)
> When testing or running in dev mode Quarkus can provide you with a zero-config database out of the box

## Critical questions
### Does Quarkus framework have embedded server like the embedded Tomcat inside Spring Boot (20260421)
Yes. [Vert.x](https://quarkus.io/guides/vertx-reference)

# 20260420
## Problem encountered
### end-to-end test with `jest` failed



