# transaction-redis-cache
This project is a training on how to implement redis as a database and as a cache

# Docker commands to start up a container
1. Get redis image with:
- docker pull redis
2. Start a redis container with:
- docker run -d --name redis -p 6379:6379 redis
3. Access the docker instance with:
- docker exec -it redis redis-cli
