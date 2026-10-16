# Sistema Bancario ABC

Sistema bancario completo con clientes, cuentas de ahorro y corriente, consignaciones, retiros y transferencias.

## Detalle

| | |
|---|---|
| Tipo | Consola (MVC) |
| Archivos Java | 15 |
| Version de Java | 21 |
| Patron | Arquitectura MVC con excepciones personalizadas y configuracion por properties |

## Funcionalidades

- Gestion de clientes
- Cuentas de ahorro y corriente
- Consignaciones, retiros y transferencias
- Excepciones propias de dominio
- Configuracion externa en archivo properties
- Persistencia de movimientos

## Estructura del proyecto

```
bancoABC/
|-- pom.xml       configuracion de Maven, importable desde cualquier IDE
|-- src/          codigo fuente
`-- README.md
```

## Como ejecutarlo

### Con Maven

```bash
mvn clean compile
mvn exec:java
```

### Con javac directamente

```bash
# Linux / macOS
javac -d bin $(find src -name "*.java")

# Windows (PowerShell)
javac -d bin (Get-ChildItem -Recurse -Filter *.java src).FullName
```

```bash
java -cp bin co.edu.unbosque.controller.AplMain
```

### Desde un IDE

Cualquier IDE con soporte de Java (Eclipse, IntelliJ IDEA, VS Code o NetBeans)
puede importar el proyecto directamente como **proyecto Maven**. Basta con abrir
o importar la carpeta: el IDE detecta el `pom.xml`, resuelve las dependencias y
compila sin configuracion adicional.

## Autor

Desarrollado por [Sergio Parra](https://github.com/P2X4A) como parte de su
formacion en Java.

## Licencia

Ver el archivo [LICENSE](LICENSE).