# Virtual Threads in Spring Boot Applications 


## Testes

Usando Completable future com limitador de tempo de requisição a 4000ms:


docker stats
CONTAINER ID   NAME                            CPU %     MEM USAGE / LIMIT   MEM %     NET I/O           BLOCK I/O         PIDS
ac701581683b   hello-virtual-threads-nginx-1   5.36%     29.14MiB / 128MiB   22.77%    20.4MB / 19.2MB   12.5MB / 12.3kB   2
2083d647a3c5   hello-virtual-threads-api1-1    21.58%    547.7MiB / 550MiB   99.58%    25.8MB / 19.2MB   889MB / 908MB     1684

Resultado gatling:

<img src="https://github.com/lucasmancan/hello-virtual-threads/blob/main/spring-mvc-async.png">

Usando Spring MVC padrão

CONTAINER ID   NAME                            CPU %     MEM USAGE / LIMIT   MEM %     NET I/O           BLOCK I/O     PIDS
f40659e0f10b   hello-virtual-threads-api1-1    10.36%    420.7MiB / 550MiB   76.49%    6.5MB / 3.63MB    0B / 164kB    221
286b4e0d4627   hello-virtual-threads-nginx-1   5.80%     66.79MiB / 128MiB   52.18%    5.79MB / 5.88MB   0B / 12.3kB   2

Resultado Gatling

<img src="https://github.com/lucasmancan/hello-virtual-threads/blob/main/spring-mvc-default.png">

Referências
https://www.danvega.dev/blog/virtual-threads-spring-boot
