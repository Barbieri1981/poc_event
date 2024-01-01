#!/bin/bash

# Función para iniciar docker-compose
start_docker_compose() {
    echo "Starting docker-compose..."
    docker-compose up --build -d
    echo "docker-compose started."
}

# Función para detener docker-compose
stop_docker_compose() {
    echo "Stopping docker-compose..."
    docker-compose down
    echo "docker-compose stopped."
}

# Función para ver los logs de docker-compose
view_docker_compose_logs() {
    echo "Viewing docker-compose logs..."
    docker-compose logs -f
}

# Control de flujo del script
case "$1" in
    start)
        start_docker_compose
        ;;
    stop)
        stop_docker_compose
        ;;
    logs)
        view_docker_compose_logs
        ;;
    *)
        echo "Usage: $0 {start|stop|logs}"
        exit 1
esac
