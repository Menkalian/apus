FROM mariadb:10.5

# Set defaults for Environment
ENV MYSQL_ROOT_PASSWORD="changeitnow"
ENV MYSQL_DATABASE="apus"
ENV MYSQL_USER="spring"
ENV MYSQL_PASSWORD="keintest"

COPY init.sql /docker-entrypoint-initdb.d/init.sql
HEALTHCHECK --start-period=30s CMD mysqlcheck ultimap -p$MYSQL_ROOT_PASSWORD || exit 1
