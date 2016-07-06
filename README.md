before running you need to install/start the mysql server: mysql.server start

build: 
    $ mvn clean install
db migrations: 
    $ java -jar target/am-portfolio-1.0.0-SNAPSHOT.jar db migrate portfolio.yml
run: 
    $ java -jar target/am-portfolio-1.0.0-SNAPSHOT.jar server portfolio.yml 
hit localhost:8080
