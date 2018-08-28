# Descrição

É um projeto Spring boot para um Crud de Clientes obtendo a sua localização a partir do ip e obtendo temperatura a partir da localização.

Autor: Everton Mendes - rosario.everton@gmail.com


# Executar o Projeto

	Instalar maven:	
		Seguir roteiro em: https://maven.apache.org/install.html

	Se desejar executar projeto para produção: 

		#Executar projeto:
		1. Ir a pasta raiz
		2. mvn -e clean package
		3. java -jar target/crud-0.0.1-SNAPSHOT.jar


	Se desejar executar projeto para desenvolvimento:
		1. Ir a pasta raiz
		2. mvn spring-boot:run

#Serviços disponibilizados

POST http://{host}:8080/api/v1/cliente
Accept: application/json
Content-Type: application/json

{
   "name": "meu nome",
   "age":"12"

}

###

GET http://{host}:8080/api/v1/cliente/{id}
Accept: application/json
Content-Type: application/json

###

GET http://{host}:8080/api/v1/cliente
Accept: application/json
Content-Type: application/json

###

PUT http://{host}:8080/api/v1/cliente
Accept: application/json
Content-Type: application/json

{
  "identificador": 1,
  "name": "meu nome",
  "age": 22
}

###

DELETE http://{host}:8080/api/v1/cliente/1
Accept: application/json
Content-Type: application/json

###
