
./mvnw clean install
docker-compose down --volumes && docker-compose up -d --build --force-recreate && sleep 10

sh ./executar-teste-local.sh
#ab -n 1000 -c 200 http://localhost:8080/httpbin/block/3