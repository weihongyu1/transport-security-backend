mvn clean package
docker build -t transport-security:v2.0 .
docker run -p 8888:8888 --name transport-security-container --link mysql:mysql-container --link redis:redis-container -d transport-security:v2.0
