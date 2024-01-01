#!/bin/bash

# Stop all containers
echo "Stopping all Docker containers..."
sudo docker-compose down

# Remove all stopped containers
echo "Removing stopped containers..."
sudo docker container prune -f

# Remove all images, not just dangling ones
echo "Removing all Docker images..."
sudo docker image prune -a -f

# Remove all unused volumes
echo "Removing unused Docker volumes..."
sudo docker volume prune -f

# Remove all unused networks
echo "Removing unused Docker networks..."
sudo docker network prune -f

echo "Docker environment cleaned up."
