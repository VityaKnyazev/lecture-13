lecture-13

Home task lecture 13:
0. On git repo
1. Base on hometask 12.
2. Remove all xml configuration
3. All beans to annotation
4. At least 2 beans of the same type (interface)
5. Inject by Autowired
6. Inject by Qualified.

Additional for 10
7. Spring bean for DataSource (jdbc pool per student)
8. jdbc property by Value annotation
9. docker-compose file with your DB
10. schema to flyway/liquibase per student

Home task done on 0-6 points.
Additional: added beans for HicariConfig, HikariDataSource to DataSource, database properties added by @Value. Added docker-compose file: src/main/resources/docker/docker-compose.yml Added schema to liquibase  src/main/resources/liquibase

Before startind App use:
$ cd lecture-13/src/main/resources/docker/
$ docker-compose up -d
$ cd ../../../..
$ mvn liquibase:update
