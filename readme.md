# ¿Qué es esto?

Es un proyecto de análisis de datos sobre la [Premier League 24-25](https://fbref.com/en/comps/9/2024-2025/2024-2025-Premier-League-Stats). El sistema consta de una API hecha con Java para manipular data cruda de, principalmente, jugadores. Utilizo Python para esto, perfilando y tratando de predecir el comportamiento de jugadores. 

## Tecnologías utilizadas

|                      |                                     |
| -------------------- | ----------------------------------- |
| **Backend/API**      | Java - Spring Boot, Hibernate, JPA  |
| **Base de datos**    | PostgreSQL con Docker               |
| **Machine learning** | Python - Pandas, Scikit-learn       |
| **Visualización**    | Plotly, Kaleido.                    |
| **Infra**            | Docker                              |

## Prerequisitos

Para ejecutar el proyecto es necesario tener preinstalado lo siguiente:
- [Java (JDK)](https://openjdk.org/)
- [Maven](https://maven.apache.org/)
- [Docker](https://www.docker.com/)

### 1. Levantar el proyecto

Ejecutar en la raiz:

```bash
make infra-up
```

### 2. Levantar la API
Ejecutar en la raiz:

```bash
make api-run
```

### 3.
Para ejecutar algunos de los análisis de datos, podés ver los disponibles en la carpeta [analisis_py](https://github.com/blatth/EPL2425/blob/master/analisis_py/anlisis-prediccion-pases/modelo_pases_med.png) y ejecutar en la raiz:

```bash
make *nombre del analisis*
```

Cada código genera una imagen estática y un .html interactivo para poder ver los gráficos con mayor detalle.