import requests
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import plotly.express as px
import plotly.graph_objects as go

#scikit-learn
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestRegressor
from sklearn.metrics import mean_absolute_error, r2_score

print("Conectando con la API...")

url = "http://localhost:8080/players"
respuesta = requests.get(url)

df = pd.json_normalize(respuesta.json())
# filtro jugadors con >300min
df = df[(df['minPlayed'] > 300) & (df['position'].str.contains('MID', na=False))].copy()

print(f"Preparando datos sobre {len(df)} mediocampistas y normalizando por 90min")

tactic_vars = [
    'touches', 'goals', 'assists', 'shots', 'shotsOnTarget', 'bigChancesMissed',
    'crosses', 'carries', 'progCarries', 'carriesEWGoal', 'carriesEWAssist', 
    'carriesEWShot', 'carriesEWChance', 'possessionWon', 'dispossessed', 
    'interceptions', 'clearances', 'blocks', 'tackles', 'groundDuels', 'groundDuelsWon', 
    'aerialDuels', 'aerialDuelsWon', 'yellowCards', 'redCards', 'fouls'
]

for var in tactic_vars:
    df[var + '_p90'] = (df[var] / df['minPlayed']) * 90

# matriz con vars normalizadas y equipos 

model_cols = [var + '_p90' for var in tactic_vars] + ['team.name']
X_raw = df[model_cols]

print("Aplicando One-Hot encoding")

X = pd.get_dummies(X_raw, columns=['team.name'], drop_first=True)
Y = (df['passesCompleted'] / df['minPlayed']) * 90

print("Filtrando datos")

nombres = df['name']

X_train, X_test, Y_train, Y_test, nombres_train, nombres_test = train_test_split(X, Y, nombres, test_size=0.2, random_state=42)

print("Entrenando modelo")
model = RandomForestRegressor(n_estimators=100, random_state=42)
model.fit(X_train, Y_train)

print("Calculando predicciones")
predicts = model.predict(X_test)

print("Resultados del modelo:")
error = mean_absolute_error(Y_test, predicts)
precision = r2_score(Y_test, predicts) * 100

print(f"En promedio, el modelo se equivoca por: {error:.0f} pases por partido (90min)")
print(f"- El modelo explica el {precision:.1f}% del comportamiento de los pases.")

print("Generando gráfico")
plt.figure(figsize=(10, 6))
sns.set_theme(style="darkgrid")

results_df = pd.DataFrame({
    'Jugador': nombres_test,
    'Pases_Reales': Y_test,
    'Pases_Predichos': predicts,
    'Error_Absoluto': abs(Y_test - predicts)
})

fig = px.scatter(
    results_df, 
    x='Pases_Reales', 
    y='Pases_Predichos',
    hover_name='Jugador',
    text='Jugador',
    hover_data={'Pases_Reales': ':.1f', 'Pases_Predichos': ':.1f', 'Error_Absoluto': ':.1f'},
    color='Error_Absoluto', # color más oscuro a los que tienen mayor error
    color_continuous_scale='Viridis',
    title='Evaluación del modelo: pases reales vs predicciones, medidos en 90m (MED)',
    labels={'Pases_Reales': 'Pases Completados REALES', 
            'Pases_Predichos': 'Pases PREDICHOS por la IA'}
)

min_val = min(Y_test.min(), predicts.min())
max_val = max(Y_test.max(), predicts.max())
fig.add_shape(
    type="line", line=dict(dash='dash', color='red', width=2),
    x0=min_val, y0=min_val, x1=max_val, y1=max_val
)

fig.update_traces(textposition='top center')
fig.update_layout(template="plotly_dark", hovermode="closest")
fig.show()
fig.write_image("modelo_pases_med.png", scale=3)