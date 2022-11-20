cd eureka-service/
mvn clean && mvn package -DskipTests
docker build . -t eureka-service
cd ..

cd movies-api/
mvn clean && mvn package -DskipTests
docker build . -t movie-service
cd ..

cd ms-bills/
mvn clean && mvn package -DskipTests
docker build . -t bills-service
cd ..

cd users-service/
mvn clean && mvn package -DskipTests
docker build . -t users-service
cd ..

cd gateway-api/
mvn clean && mvn package -DskipTests
docker build . -t gateway-service
cd ..

docker-compose up