# Evaluación Tecno Acción.

## Requisitos:
  - Se debe contar con motor de base de datos MYSQL.
  - Tener un JDK versión 1.8.
  - Tener instalado el Git.
  - Tener mvn instalado en su ambiente.
  
## Instrucciones para levantar la API REST de manera local:
###	Configuración:
1. En un directorio clonar el proyecto: git clone  ```https://github.com/julianmisiak/evaluation_ta.git ```
	Debe crearse una base de datos en Mysql denominada springapp con el puerto 3306
	y configurar el usuario y contraseña en el archivo application.properties
    
2. Posicionarse en el directorio del proyecto y correr ```mvn clean install``` desde una terminal para descargar todas sus dependencias.
    	
###	Ejecución:
Posicionarse en el directorio del proyecto y ejecutar ```mvn spring-boot:run``` desde una terminal.
		
###	Test:
Para la ejecución de los test automáticos utilice JUnit
- Para ejecutar las pruebas se puede ir al directorio del proyecto y correr el siguiente comando:``` mvn test```
	
#### Test desde la herramienta Postman.

1. Get Usuario por id.
```http://localhost:8080/users/73```
```
{
    "id": 73,
    "email": "email_73",
    "first_name": "firstName_73",
    "last_name": "lastName_73",
    "bet": [
        {
            "id": 217,
            "mount": 20,
            "game_id": 1
        },
        {
            "id": 218,
            "mount": 20,
            "game_id": 1
        },
        {
            "id": 219,
            "mount": 20,
            "game_id": 1
        }
    ]
}
```

Delete Usuario por id. ``` http://localhost:8080/users/6 ```
```
Status 200 OK 
```

Get Bets  ``` http://localhost:8080/bets/limit/2/offset/0 ```
```
[
    {
        "id": 19,
        "mount": 20,
        "game_id": 1
    },
    {
        "id": 20,
        "mount": 20,
        "game_id": 1
    }
]
```

Get Bets por game_id ``` http://localhost:8080/bets/limit/20/offset/3/game_id/1 ```
 ```
 [
    {
        "id": 22,
        "mount": 20,
        "game_id": 1
    },
    {
        "id": 23,
        "mount": 20,
        "game_id": 1
    },
    {
        "id": 24,
        "mount": 20,
        "game_id": 1
    }
]
  ```
	
