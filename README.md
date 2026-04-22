Sprint 2: Persistencia y Gestión de Logs

Actualización del sistema de administración y usuario basada en la Unidad 8, integrando almacenamiento permanente y trazabilidad de operaciones.

Requisitos Implementados

1. Persistencia en Base de Datos

Se ha migrado el sistema de gestión de datos para abandonar el almacenamiento volátil en memoria.

    Tecnología: SQL (MySQL).

    Objetivo: Garantizar que los datos de usuarios y administradores se mantengan guardados tras cerrar la aplicación.

    Acciones: Todas las operaciones CRUD.

2. Sistema de Trazabilidad (log.txt)

Implementación de un sistema de registro de eventos automático.

    Ubicación: Raíz de la aplicación principal.

    Funcionamiento: Cada vez que se realiza una acción contra la Base de Datos, se genera una nueva línea descriptiva en el fichero.

    Formato del log: [FECHA HORAS:MIN:SEG] - ACCIÓN: [Descripción de la consulta]

3. Panel de Auditoría para Administradores

Nueva funcionalidad añadida al menú de control:

    Opción: Leer fichero log.txt.

    Descripción: Permite al administrador visualizar por consola todo el historial de movimientos registrados en el sistema de logs sin necesidad de abrir el archivo externamente.
