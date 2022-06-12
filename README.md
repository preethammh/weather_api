# weather_api
Spring boot application to find the weather of any location
*******************************************************
Building the application:- Perform maven build
      mvn clean validate install
*********************************************************
Target folder will be generated in the below path, which includes the resources folder
     {source_code_path}\target\weather_app
********************************************************
Start the application by executing the below command
    
C:\Projects\Weather_App\weather_app\target\weather_app>java -jar weather_app-0.0.1-SNAPSHOT.jar

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.7.0)

2022-06-12 22:36:19.516  INFO 8320 --- [           main] com.weather.app.WeatherAppApplication    : Starting WeatherAppApplication v0.0.1-SNAPSHOT using Java 11.0.11 on LAPTOP-O7ET145J with PID 8320 (C:\Projects\Weather_App\weather_app\target\weather_app\weather_app-0.0.1-SNAPSHOT.jar started by preet in C:\Projects\Weather_App\weather_app\target\weather_app)
2022-06-12 22:36:19.520  INFO 8320 --- [           main] com.weather.app.WeatherAppApplication    : No active profile set, falling back to 1 default profile: "default"
2022-06-12 22:36:20.662  INFO 8320 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2022-06-12 22:36:20.722  INFO 8320 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 42 ms. Found 1 JPA repository interfaces.
2022-06-12 22:36:21.661  INFO 8320 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 4000 (http)
2022-06-12 22:36:21.671  INFO 8320 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2022-06-12 22:36:21.671  INFO 8320 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.63]
2022-06-12 22:36:21.759  INFO 8320 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2022-06-12 22:36:21.759  INFO 8320 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 2172 ms
2022-06-12 22:36:21.792  INFO 8320 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2022-06-12 22:36:22.145  INFO 8320 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2022-06-12 22:36:22.173  INFO 8320 --- [           main] o.s.b.a.h2.H2ConsoleAutoConfiguration    : H2 console available at '/h2-ui'. Database available at 'jdbc:h2:file:./weather_db'
2022-06-12 22:36:22.343  WARN 8320 --- [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2022-06-12 22:36:22.411  INFO 8320 --- [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2022-06-12 22:36:22.488  INFO 8320 --- [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 5.6.9.Final
2022-06-12 22:36:22.689  INFO 8320 --- [           main] o.hibernate.annotations.common.Version   : HCANN000001: Hibernate Commons Annotations {5.1.2.Final}
2022-06-12 22:36:22.848  INFO 8320 --- [           main] org.hibernate.dialect.Dialect            : HHH000400: Using dialect: org.hibernate.dialect.H2Dialect
2022-06-12 22:36:23.539  INFO 8320 --- [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
2022-06-12 22:36:23.540  INFO 8320 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2022-06-12 22:36:25.283  INFO 8320 --- [           main] o.s.s.web.DefaultSecurityFilterChain     : Will secure any request with [org.springframework.security.web.session.DisableEncodeUrlFilter@5f14761c, org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter@33feb805, org.springframework.security.web.context.SecurityContextPersistenceFilter@4f0cab0a, org.springframework.security.web.header.HeaderWriterFilter@78e17a99, org.springframework.web.filter.CorsFilter@37854b34, org.springframework.security.web.authentication.logout.LogoutFilter@39bbd9e0, com.weather.app.security.jwt.AuthTokenFilter@588f63c, org.springframework.security.web.savedrequest.RequestCacheAwareFilter@7ab4ae59, org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter@6f76c2cc, org.springframework.security.web.authentication.AnonymousAuthenticationFilter@48eb001a, org.springframework.security.web.session.SessionManagementFilter@60d40ff4, org.springframework.security.web.access.ExceptionTranslationFilter@7847ef2c, org.springframework.security.web.access.intercept.FilterSecurityInterceptor@341b13a8]
2022-06-12 22:36:25.614  INFO 8320 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 4000 (http) with context path ''
2022-06-12 22:36:25.628  INFO 8320 --- [           main] com.weather.app.WeatherAppApplication    : Started WeatherAppApplication in 6.661 seconds (JVM running for 7.122)
******************************************************
Application is configured to be execute in the port 4000
Swagger UI path

http://localhost:4000/swagger-ui.html
