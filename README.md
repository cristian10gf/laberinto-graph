# laberinto-graph
## Aventura en el Bosque Misterioso

## Descripción

Este proyecto es un juego interactivo de consola llamado "Aventura en el Bosque Misterioso". Los jugadores avanzan a través de un bosque resolviendo acertijos. Los acertijos y la estructura del bosque se representan mediante una estructura de árbol.

## Estructura del Proyecto

El proyecto tiene la siguiente estructura:
```
acertijos.txt
acertijos_2.txt
pom.xml
src/
	main/
		java/
			main/
				lab1estructura2/
					Acertijo.java
					Arbol.java
					Lab1estructura2.java
					Nodo.java
target/
	classes/
		main/
			lab1estructura2/
				Acertijo.class
				Arbol.class
				Lab1estructura2.class
				Nodo.class
	maven-status/
		maven-compiler-plugin/
			compile/
				default-compile/
					createdFiles.lst
					inputFiles.lst
	test-classes/
		.netbeans_automatic_build
```

## Cómo construir y ejecutar el proyecto

Para construir el proyecto, puedes usar Maven:

```sh
mvn clean install
```

Para ejecutar el proyecto, puedes usar el comando de Java:

```sh
java -cp target/classes main.lab1estructura2.Lab1estructura2
```

## Contribuir

Si deseas contribuir a este proyecto, por favor...
