# Composición táctica - Sub-clustering por posición

En este caso usé [K-means](https://en.wikipedia.org/wiki/K-means_clustering). De acuerdo al agrupamiento que el modelo hace en [clusters](https://en.wikipedia.org/wiki/Cluster_analysis), calculando la distancia entre ellos de acuerdo a las desvíaciones del promedio general (el [centroide](https://en.wikipedia.org/wiki/Centroid) de cada cluster), se extraen las 4 variables tácticas (o rasgos) más respresentativas de cada jugador para tratar de encontrar su _"posición real"_. 

El eje X mide el "modo de juego" de cada jugador, siendo la parte negativa la **contención** y la parte positiva la **creación**.

El eje Y mide el volumen de juego. Separa a los jugadores que son el centro de participación del equipo (como los 5 posicionales) de aquellos con intervenciones más esporádicas o aisladas (por ejemplo, centrales)

## ¿Qué parámetros fueron usados?

### `X` - Matriz

- Para el perfil defensivo: quites, intercepciones, despejes, bloqueos, duelos terrestres y aéreos, faltas
- Para el perfil mediocampista/de transición/posesión: toques, pases completados, conducciones progresivas, pases exitosos en el último tercio
- Para el perfil ofensivo: tiros, centros, conducciones que terminan con chance de gol

### 90 minutos

Todo fue medido en 90 minutos para normalizar los datos, ya que hay jugadores que jugaron muchos más minutos que otros y viceversa. De esta forma logro 2 cosas:
- Aislo el factor "talento": se puede evaluar un titular de la misma forma que un suplente
- Cierta coherencia matemática?: Como se está intentando predecir una tasa (pases por partido) utilizando _otras_ tasas (quites por partido, tiros por partido, etc), se alinean espacios vectoriales. De esta forma reduzco el "ruido" de los datos y se genera menos margen de error.

### Z-scores - Estandarización por desviación

Usé [StandardScaler](https://scikit-learn.org/stable/modules/generated/sklearn.preprocessing.StandardScaler.html) para convertir todos los números a [Z-scores](https://en.wikipedia.org/wiki/Standard_score). Si no hacía esto, el volumen absoluto de los pases iba a ser muy grande en comparación a las acciones defensivas, por lo que todos los jugadores iban a tener un perfil más "organizador" y perdería el propósito el análisis.
De esta forma, el modelo se centra en las anomalías o desvíaciones estadísticas para determinar en qué se destaca cada jugador por encima del resto.

![Composición táctica - Sub-clustering por posición](https://github.com/blatth/EPL2425/blob/master/analisis_py/analisis-posiciones/clustering_pos.png)