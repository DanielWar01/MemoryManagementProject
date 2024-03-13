# Proyecto de Simulación de Gestión de Memoria

Este proyecto, desarrollado en Java utilizando el paradigma de programación orientada a objetos con la versión 17 del JDK y la biblioteca Java Swing para la interfaz gráfica, tiene como objetivo simular la localización, ubicación y liberación de n procesos en la memoria del PC utilizando particiones fijas y variables.

## Integrantes del Equipo
- **Daniel Alejandro Guerra**
- **Laura Jimena Jaime**
- **Sergio Gómez**

## Descripción del Problema
Se busca construir una aplicación que simule la gestión de memoria en un sistema informático. Específicamente, la aplicación debe mostrar en tiempo real el diagrama de particiones, incluyendo la localización, ubicación y liberación de procesos en la memoria, tanto para particiones fijas como variables.

### Dinámica de la Aplicación
La simulación debe ofrecer una visualización dinámica del diagrama de particiones, actualizándose en tiempo real al ingresar o liberar un proceso en la memoria.

## Objetivo
El objetivo principal de esta aplicación es permitir a los usuarios familiarizarse con los algoritmos lógicos utilizados por el gestor de memoria para ubicar procesos en la memoria del sistema.

## Alcances
- La aplicación permite ingresar o liberar procesos de forma individual, no en conjunto.
- Muestra el espacio de memoria usado, el espacio de memoria libre y el espacio de memoria desperdiciado, ya que estos algoritmos generan segmentación externa.
- Ofrece una representación gráfica del diagrama de particiones actualizado después de cada operación.

## Métodos de Gestión de Memoria
El programa implementa dos métodos de gestión de memoria:
1. **Gestion de Memoria por Particiones Fijas**
2. **Gestion de Memoria por Particiones Variables** con tres algoritmos diferentes:
   - **Primer Ajuste**
   - **Mejor Ajuste**
   - **Peor Ajuste**

Para más detalles sobre la implementación y funcionamiento del programa, consulte la documentación y el código fuente.
