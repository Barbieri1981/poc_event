#!/bin/bash

# Stop all containers
echo "Stopping all Docker containers..."
docker-compose down

# Remove all stopped containers
echo "Removing stopped containers..."
docker container prune -f

# Remove all dangling images
echo "Removing dangling images..."
docker image prune -f

# Remove all unused volumes
echo "Removing unused Docker volumes..."
docker volume prune -f

# Remove all unused networks
echo "Removing unused Docker networks..."
docker network prune -f

# Start docker-compose
echo "Starting docker-compose..."
docker-compose up --build
