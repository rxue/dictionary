# How to generate the project with `mvn` command
https://quarkus.io/guides/getting-started

# Quarkus dependency search engine
https://code.quarkus.io/

# Practical lessons learnt
## From https://github.com/rxue/dictionary/issues/172
`@Embedded` class is not supported to be in an abstract `@Entity` class. This kinda just obey the "composition over inheritance rule"

## From https://github.com/rxue/dictionary/issues/174 on 20250529
When using `quarkus-resteasy-jsonb` to make REST API endpoint, the attribute annotated with `@Embedded` inside a *JPA entity* will not be visible in the implemented REST API endpoint response JSON. This is a bit different from that when using `resteasy` of *WildFly* to implement the REST API. In order to make the `@Embedded` attribute visible in the response JSON, add *getter* and *setter* to that `@Embedded` field. Moreover, the `@Embedded` annotation is not compulsory, i.e. it can be removed

# IntelliJ learning tutorial with Linux OS
## Keyboard shortcut
### "Alt + Insert" to *create a New* class/package etc.


