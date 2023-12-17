mvn clean package
docker build -t transport-security-backend:v2.0 .
docker run -p 8888:8888 --name transport-security-backend-container --link mysql:mysql-container --link redis:redis-container -d transport-security-backend:v2.0
