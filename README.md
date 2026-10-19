# Banking System ABC - bancoABC

## Description

Complete banking system: manage clients, savings and checking accounts, and run consignments, withdrawals and transfers from a console interface. Built with an **MVC** architecture and custom exceptions for banking rules (insufficient balance, blocked overdraft, inactive accounts), with configuration read from `config.properties`.

Academic project developed for the **Java programming** courses - Systems Engineering, Universidad El Bosque.

## Author

- Sergio Alejandro Parra Rivera

## Technologies

- Java 21 (compiled with `maven-compiler-plugin`)
- Maven 3.8+ with `exec-maven-plugin` to run the application
- **MVC** architecture (`controller`, `model`, `persistence`, `view`)
- Inheritance for account types (`CuentaAhorro`, `CuentaCorriente` extend `Cuenta`)
- Custom exceptions for banking rules
- Plain text file persistence with `FileHandler`

## Requirements

| Requirement | Version | How to check |
| --- | --- | --- |
| JDK | 21 or higher | `javac -version` |
| Maven | 3.8 or higher | `mvn -v` |
| Git | Any recent version | `git --version` |

## Installation

Follow these steps exactly so the application runs on the first try.

### 1. Clone the repository

```bash
git clone https://github.com/P2X4A/bancoABC.git
```

### 2. Enter the project folder

```bash
cd bancoABC
```

> On Windows, if your path contains spaces, always quote it, for example: `cd "C:\Users\Example\Documents\bancoABC"`.

### 3. Verify your environment

```bash
javac -version
mvn -v
```

Expected: JDK 21 (or higher) and Maven 3.8 (or higher). If any command fails, install or update that tool before continuing.

### 4. Compile the project

```bash
mvn clean compile
```

What success looks like:

- The console ends with `BUILD SUCCESS`.
- A `target/classes/` folder is created with the compiled classes.

## Running the application

```bash
mvn exec:java
```

The menu is displayed in the terminal: register clients and accounts, then consign, withdraw or transfer between accounts.

Troubleshooting:

- `ClassNotFoundException` or missing main class: compile first with `mvn clean compile`.
- `UnsupportedClassVersionError`: your JDK is older than 21; install JDK 21 or higher and check it with `javac -version`.

## Main features

- **Clients and accounts:** register clients and open savings or checking accounts.
- **Operations:** consignments, withdrawals and transfers between accounts, handled by `GestorBanco`.
- **Business rules with exceptions:** every operation validates the account state and balance, raising `SaldoInsuficienteExcepcion`, `SobregiroNoPermitidoExcepcion` or `CuentaInactivaExcepcion` when a rule is broken.
- **Configuration:** operational parameters are read from `src/config.properties`; a missing parameter raises `ParametroNoConfiguradoException`.
- **Transaction records:** every consignment and movement is recorded as a `Transaccion` / `MovimientoConsignacion`.

## Project structure

```text
bancoABC/
├── pom.xml
├── README.md
├── LICENSE
└── src/
    ├── config.properties                  # Operational parameters
    └── co/edu/unbosque/
        ├── controller/
        │   ├── AplMain.java               # Entry point (main class)
        │   └── Controlador.java           # Application controller (MVC)
        ├── model/
        │   ├── Cliente.java               # Client entity
        │   ├── Cuenta.java                # Base account
        │   ├── CuentaAhorro.java          # Savings account
        │   ├── CuentaCorriente.java       # Checking account
        │   ├── GestorBanco.java           # Bank operations manager
        │   ├── Transaccion.java           # Transaction record
        │   └── MovimientoConsignacion.java # Consignment movement
        ├── exceptions/                    # Custom banking exceptions
        │   ├── SaldoInsuficienteExcepcion.java
        │   ├── SobregiroNoPermitidoExcepcion.java
        │   ├── CuentaInactivaExcepcion.java
        │   └── ParametroNoConfiguradoException.java
        ├── persistence/
        │   └── FileHandler.java           # File reading and writing
        └── view/
            └── Consola.java               # Console interface
```
