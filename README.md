<div align="center">
  <h1>Banking Demo Java</h1>
  <p><strong>Sistema bancario multi-módulo con Spring Boot, Docker y Swagger</strong></p>

  <p>
    <img src="https://img.shields.io/badge/Java-17-ED8B00?style=flat-square&logo=java" alt="Java 17"/>
    <img src="https://img.shields.io/badge/Maven-C71A36?style=flat-square&logo=apache-maven" alt="Maven"/>
    <img src="https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=docker" alt="Docker"/>
    <img src="https://img.shields.io/badge/MySQL-005C84?style=flat-square&logo=mysql" alt="MySQL"/>
    <img src="https://img.shields.io/badge/Swagger-85EA2D?style=flat-square&logo=swagger" alt="Swagger"/>
  </p>
</div>

---

## Descripción

Aplicación bancaria modular que expone servicios REST para gestión de cuentas y movimientos. Arquitectura multi-módulo con contenedores Docker, documentación Swagger y base de datos MySQL.

## Arquitectura

```
bankingDemoJava/
├── account.client/        # Módulo de clientes (API REST)
├── account.mov/           # Módulo de movimientos (API REST)
├── docker-compose.yml     # Orquestación de contenedores
├── BaseDatos.sql          # Esquema de base de datos
└── BankingDemoJava.postman_collection.json  # Colección Postman
```

**Flujo:**

```
Client (REST)  -->  account.client (8082)  -->  MySQL
Client (REST)  -->  account.mov (8083)     -->  MySQL
                    Swagger UI              -->  account.client:5000
                    Swagger UI              -->  account.mov:5001
```

## Stack

| Tecnología | Versión |
|------------|---------|
| Java | 17 |
| Spring Boot | 3.x |
| Maven | 3.x |
| Docker | Latest |
| MySQL | 8.x |
| Swagger / OpenAPI | 3.x |

## Requisitos

- Java 17+
- Docker & Docker Compose
- Maven 3.x
- IntelliJ IDEA (recomendado) o tu IDE favorito

## Instalación y ejecución

### 1. Clonar e importar

```bash
git clone https://github.com/sevillacesar/bankingDemoJava.git
cd bankingDemoJava
```

Importa el proyecto en IntelliJ como proyecto Maven existente.

### 2. Crear directorio para datos de MySQL

```bash
mkdir -p shared/mysql_data
```

### 3. Compilar módulos

```bash
mvn clean install -f account.client/pom.xml
mvn clean install -f account.mov/pom.xml
```

### 4. Buildear imágenes Docker

```bash
docker build -q --rm -t client account.client/.
docker build -q --rm -t mov account.mov/.
```

### 5. Levantar con Docker Compose

```bash
docker-compose up -d
```

### 6. Probar la API

Carga la colección `BankingDemoJava.postman_collection.json` en Postman.

### 7. Swagger UI

| Servicio | URL |
|----------|-----|
| account.client | http://localhost:5000/swagger-ui/index.html |
| account.mov | http://localhost:5001/swagger-ui/index.html |

### 8. Detener contenedores

```bash
docker-compose down
```

## API Endpoints

### account.client (puerto 5000 / 8082)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/clientes` | Listar clientes |
| `GET` | `/api/clientes/{id}` | Cliente por ID |
| `POST` | `/api/clientes` | Crear cliente |
| `PUT` | `/api/clientes/{id}` | Actualizar cliente |
| `DELETE` | `/api/clientes/{id}` | Eliminar cliente |

### account.mov (puerto 5001 / 8083)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/movimientos` | Listar movimientos |
| `GET` | `/api/movimientos/{id}` | Movimiento por ID |
| `POST` | `/api/movimientos` | Registrar movimiento |
| `GET` | `/api/movimientos/cuenta/{cuentaId}` | Movimientos por cuenta |

## Licencia

Distribuido bajo MIT License. Ver `LICENSE` para más información.
