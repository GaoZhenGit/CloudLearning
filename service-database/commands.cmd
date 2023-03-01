docker build -t learning-database:1.0.0 .
docker stop learning-database-1.0.0
docker rm learning-database-1.0.0
docker run -d --name learning-database-1.0.0 -p 20201:20201 --network spring --network-alias database learning-database:1.0.0