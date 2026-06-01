# Pases reales vs predicciones, medidos en 90m (MED)

Para este caso utilicé [Random Forest Regressor](https://scikit-learn.org/stable/modules/generated/sklearn.ensemble.RandomForestRegressor.html), basado en Regresión Lineal. Con RFR creo, en este caso, 100 árboles diferentes (`n_estimators=100`) los cuales son promediados para llegar a un nivel de precisión (o error) "aceptable".

El eje X muestra la cantidad de pases que el jugador completó realmente en 90m, el eje Y la predicción calculada por el modelo. La línea roja es la que indica de cuánto fue el error, por lo que si el punto cae exactamente en ella el margen de error fue 0 (por lo tanto, mientras más lejos de la línea esté cada punto, más anómalo será el caso de la predicción).

## ¿Qué parámetros fueron usados?

### `X` - Matriz

- Para el perfil defensivo: intercepciones, quites, despejes
- Para el perfil ofensivo: tiros al arco, goles, grandes chances erradas
- Perfil de posesión: conducciones progresivas, toques
- Contexto táctico: el equipo de cada uno
  
Entre otros (pueden verse en el código, los nombres de cada uno están bastante descriptivos).

### `Y` - Vector objetivo

Como se quiere predecir los pases **reales** que hubo, el vector toma el valor de los pases completados para entrenar al modelo. Es por esto que nada dentro de la matriz debe tener información sobre ellos (efectividad, pases intentados, etc).

### 90 minutos

Todo fue medido en 90 minutos para normalizar los datos, ya que hay jugadores que jugaron muchos más minutos que otros y viceversa. De esta forma logro 2 cosas:
- Aislo el factor "talento": se puede evaluar un titular de la misma forma que un suplente
- Cierta coherencia matemática?: Como se está intentando predecir una tasa (pases por partido) utilizando _otras_ tasas (quitas por partido, tiros por partido, etc), se alinean espacios vectoriales. De esta forma reduzco el "ruido" de los datos y se genera menos margen de error.

![Pases reales vs predicciones, medidos en 90m (MED)](https://github.com/blatth/EPL2425/blob/master/analisis_py/modelo_pases_med.png)
