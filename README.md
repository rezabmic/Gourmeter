Gourmeter
=========

## Spusteni testu pri buildu
Arquillian je nastaven tak, aby se chytnul beziciho Wildfly 8.2. Proto pokud buildujete projekt bez prepinace -DskipTests=true, pustetne pred buildem Wildfly.

## Instalace PostgreSQL
Wildfly jako takovy nema ve vychozim stavu instalovany zadny jdbc connector, proto ho musime vytvorit. Navod na instalaci PostgreSQL lze nalezt napr. na [http://www.mastertheboss.com/jboss-server/jboss-datasource/configuring-a-datasource-with-postgresql-and-jboss-wildfly](http://www.mastertheboss.com/jboss-server/jboss-datasource/configuring-a-datasource-with-postgresql-and-jboss-wildfly)
