##!/bin/bash

# Rutas a los archivos que deseas monitorear
archivo_lexico="Lexico"
archivo_sintactico="Sintactico"

# Comando a ejecutar cuando se detecte un cambio en los archivos
comando_a_ejecutar="./compilar.sh"

# Función para ejecutar el comando y limpiar la pantalla
ejecutar_comando() {
    clear   # Limpia la pantalla
    echo "Cambio detectado en $1"
    eval "$comando_a_ejecutar"
}

# Inicia el monitoreo de los archivos
while true; do
    evento=$(inotifywait -e modify,move,create,delete "$archivo_lexico" "$archivo_sintactico" 2>/dev/null)
    if [ $? -eq 0 ]; then
        ejecutar_comando "$archivo_lexico" || true
        ejecutar_comando "$archivo_sintactico" || true
    fi
done
