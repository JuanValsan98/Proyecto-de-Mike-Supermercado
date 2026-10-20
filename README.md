# API REST de gestión de supermercado

API para administrar categorías, proveedores, productos, clientes y ventas. Está construida con Spring Boot, Spring Data JPA, Bean Validation y MySQL. Los controladores reciben y responden DTOs; los servicios contienen las operaciones y reglas de negocio.

## Requisitos

- Java 25, que es la versión configurada actualmente en `pom.xml`.
- MySQL disponible localmente.
- Maven Wrapper incluido (`mvnw.cmd`), por lo que no hace falta instalar Maven por separado.
- Postman para probar los endpoints.

> El documento de requisitos del proyecto solicita Spring Boot 3.x; el `pom.xml` actual declara Spring Boot 4.1.1. Si la versión 3.x es obligatoria para la entrega, hay que alinear esa versión antes de entregar.

## Configurar MySQL

Inicia MySQL y crea la base de datos antes de arrancar la API:

```sql
CREATE DATABASE demosupermercado;
```

La configuración predeterminada usa `jdbc:mysql://localhost:3306/demosupermercado` y el usuario `root`. La contraseña se lee de la variable de entorno `DB_PASSWORD`. También se pueden indicar otros valores mediante `DB_URL` y `DB_USERNAME`.

En PowerShell, configura las variables para la terminal actual:

```powershell
$env:DB_URL = "jdbc:mysql://localhost:3306/demosupermercado"
$env:DB_USERNAME = "root"
$env:DB_PASSWORD = "tu_contraseña_de_mysql"
```

No guardes contraseñas reales en el repositorio. Hibernate está configurado con `ddl-auto=update`, por lo que crea o actualiza las tablas al iniciar; aun así, la base de datos indicada en la URL debe existir.

## Ejecutar la aplicación

Desde la carpeta del proyecto:

```powershell
.\mvnw.cmd spring-boot:run
```

La API queda disponible en `http://localhost:8080`. Mantén esa terminal abierta mientras pruebas desde Postman.

Para compilar y ejecutar las pruebas automatizadas:

```powershell
.\mvnw.cmd test
```

Las pruebas usan H2 en memoria mediante `src/test/resources/application.properties`; no verifican por sí solas la conexión con MySQL ni todos los endpoints.

## Endpoints

Todas las rutas empiezan con `http://localhost:8080`.

### Categorías

| Método | Ruta | Descripción |
|---|---|---|
| POST | `/api/categorias` | Crear categoría |
| GET | `/api/categorias` | Listar categorías |
| GET | `/api/categorias/{id}` | Consultar categoría |
| PUT | `/api/categorias/{id}` | Actualizar categoría |
| DELETE | `/api/categorias/{id}` | Eliminar categoría si no tiene productos |

Cuerpo de ejemplo para crear o actualizar:

```json
{
  "nombre": "Bebidas",
  "descripcion": "Productos para beber"
}
```

### Proveedores

| Método | Ruta | Descripción |
|---|---|---|
| POST | `/api/proveedores` | Crear proveedor |
| GET | `/api/proveedores` | Listar proveedores |
| GET | `/api/proveedores/{id}` | Consultar proveedor |
| PUT | `/api/proveedores/{id}` | Actualizar proveedor |
| DELETE | `/api/proveedores/{id}` | Eliminar proveedor si no tiene productos |

Cuerpo de ejemplo:

```json
{
  "nombre": "Proveedor Uno",
  "telefono": "1234567890",
  "correo": "proveedor@example.com"
}
```

### Clientes

| Método | Ruta | Descripción |
|---|---|---|
| POST | `/api/clientes` | Crear cliente |
| GET | `/api/clientes` | Listar clientes |
| GET | `/api/clientes/{id}` | Consultar cliente por ID |
| GET | `/api/clientes/documento/{documento}` | Consultar cliente por documento |
| PUT | `/api/clientes/{id}` | Actualizar cliente |
| DELETE | `/api/clientes/{id}` | Eliminar cliente si no tiene ventas |

Cuerpo de ejemplo:

```json
{
  "nombre": "Ana Pérez",
  "documento": "DOC-100",
  "telefono": "1234567890"
}
```

### Productos

| Método | Ruta | Descripción |
|---|---|---|
| POST | `/api/productos` | Crear producto |
| GET | `/api/productos` | Listar productos |
| GET | `/api/productos/{id}` | Consultar producto |
| PUT | `/api/productos/{id}` | Actualizar producto |
| DELETE | `/api/productos/{id}` | Eliminar producto si no está en ventas |
| GET | `/api/productos/categoria/{categoriaId}` | Listar por categoría |
| GET | `/api/productos/buscar?nombre=cafe` | Buscar por nombre, sin distinguir mayúsculas |
| GET | `/api/productos/stock-bajo?limite=10` | Listar con stock menor o igual al límite |

Cuerpo de ejemplo (usa IDs que ya existan):

```json
{
  "codigoDeBarras": "BAR-100",
  "nombre": "Cafe",
  "stock": 12,
  "precio": 3.50,
  "categoriaId": 1,
  "proveedorId": 1
}
```

`proveedorId` es opcional; `categoriaId` es obligatorio. El precio debe ser mayor que cero y el stock debe ser cero o mayor.

### Ventas

| Método | Ruta | Descripción |
|---|---|---|
| POST | `/api/ventas` | Registrar venta y descontar stock |
| GET | `/api/ventas` | Listar ventas |
| GET | `/api/ventas/{id}` | Consultar venta |
| DELETE | `/api/ventas/{id}` | Eliminar venta y restituir stock |
| GET | `/api/ventas/cliente/{clienteId}` | Listar ventas de un cliente |
| GET | `/api/ventas/fecha?inicio=...&fin=...` | Listar por rango de fecha |

Cuerpo de ejemplo (usa IDs que ya existan):

```json
{
  "clienteId": 1,
  "detalles": [
    {
      "productoId": 1,
      "cantidad": 2
    }
  ]
}
```

Las fechas del filtro deben estar en formato ISO `yyyy-MM-dd'T'HH:mm:ss`, por ejemplo:

```text
/api/ventas/fecha?inicio=2026-10-01T00:00:00&fin=2026-10-31T23:59:59
```

Al registrar una venta, el servidor toma el precio actual de cada producto, calcula subtotales y total, registra la fecha y descuenta el stock. Si se elimina la venta, devuelve al stock las cantidades vendidas.

## Probar con Postman

1. Arranca la aplicación y crea una petición nueva en Postman.
2. Selecciona el método y la ruta de la tabla.
3. Para `POST` y `PUT`, selecciona **Body → raw → JSON** y envía el objeto correspondiente.
4. Crea primero una categoría y un cliente; crea también un proveedor si quieres asociarlo al producto.
5. Crea el producto con los IDs devueltos y después registra la venta.
6. Comprueba que la venta haya descontado stock y que eliminarla lo restituya.

Los endpoints responden normalmente con `200 OK` para consultas/actualizaciones, `201 Created` al crear y `204 No Content` al eliminar. Los errores de validación suelen responder `400 Bad Request`, los recursos inexistentes `404 Not Found` y los conflictos de reglas de negocio `409 Conflict`.

