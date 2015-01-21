Gourmeter
=========

## Instalace PostgreSQL na WildFly
Wildfly jako takovy nema ve vychozim stavu instalovany zadny jdbc connector, proto ho musime vytvorit. Navod na instalaci PostgreSQL lze nalezt napr. na [http://www.mastertheboss.com/jboss-server/jboss-datasource/configuring-a-datasource-with-postgresql-and-jboss-wildfly](http://www.mastertheboss.com/jboss-server/jboss-datasource/configuring-a-datasource-with-postgresql-and-jboss-wildfly)

### Zjednoduseny postup:
	1. pust WildFly
	2. pust jboss-cli
	3. prikaz: connect
	4. prikaz: module add --name=org.postgres --resources=/ZADEJCESTU/tmp/postgresql-9.3-1101.jdbc41.jar --dependencies=javax.api,javax.transaction.api
	5. prikaz: /subsystem=datasources/jdbc-driver=postgres:add(driver-name="postgres",driver-module-name="org.postgres",driver-class-name=org.postgresql.Driver)
	6. prikaz (opt): data-source add --jndi-name=java:/PostGreDS --name=PostgrePool --connection-url=jdbc:postgresql://localhost/postgres --driver-name=postgres --user-name=postgres --password=postgres
	
	
## Konfigurace Arquillianu
Jako testovaci framework pouzivame Arquillian. Pro spravne spousteni testu je potreba nastavit umisteni aplikacniho serveru (WildFly 8). To se nachazi v souboru **test/resources/arquillian.xml** a v elementu **jbossHome**. Arquillian je nastaven tak, aby se chytnul beziciho Wildfly 8.2. Proto pokud buildujete projekt bez prepinace -DskipTests=true, pustetne pred buildem Wildfly

