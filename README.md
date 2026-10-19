# ðŸ¦ Sistema Bancario ABC

Sistema bancario completo con clientes, cuentas de ahorro y corriente, consignaciones, retiros y transferencias. Desarrollado en Java con arquitectura **MVC** para consola.

## ðŸ“‹ CaracterÃ­sticas

- GestiÃ³n de clientes y cuentas bancarias
- Cuentas de ahorro y corriente con reglas distintas
- Operaciones: consignaciones, retiros y transferencias
- Manejo de excepciones bancarias (saldo insuficiente, sobregiro no permitido, cuenta inactiva)
- Gestor centralizado de banco (`GestorBanco`)
- Registro de movimientos y transacciones
- Interfaz por consola

## ðŸ› ï¸ TecnologÃ­as

- Java
- Maven
- PatrÃ³n MVC
- ProgramaciÃ³n orientada a objetos

## ðŸš€ CÃ³mo ejecutar

```bash
git clone https://github.com/P2X4A/bancoABC.git
cd bancoABC
mvn clean compile
mvn exec:java -Dexec.mainClass="AplMain"
```

## ðŸ“ Estructura

```
bancoABC/
â”œâ”€â”€ pom.xml
â”œâ”€â”€ README.md
â”œâ”€â”€ LICENSE
â”œâ”€â”€ config.properties
â”œâ”€â”€ src/
â”‚   â”œâ”€â”€ AplMain.java
â”‚   â”œâ”€â”€ Controlador.java
â”‚   â”œâ”€â”€ Consola.java
â”‚   â”œâ”€â”€ Cliente.java
â”‚   â”œâ”€â”€ Cuenta.java, CuentaAhorro.java, CuentaCorriente.java
â”‚   â”œâ”€â”€ GestorBanco.java
â”‚   â”œâ”€â”€ Transaccion.java, MovimientoConsignacion.java
â”‚   â””â”€â”€ *.Excepcion.java (excepciones bancarias)
â””â”€â”€ ...
```

## ðŸ‘¨â€ðŸ’» Autor

Sergio Parra

---

*Proyecto acadÃ©mico para portafolio.*
