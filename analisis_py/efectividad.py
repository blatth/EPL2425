import requests
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import plotly.express as px
import plotly.graph_objects as go

print("Conectando con la API...")

url = "http://localhost:8080/players"
respuesta = requests.get(url)

if respuesta.status_code == 200:
    print("Datos obtenidos")
    datos_json = respuesta.json()

    df = pd.json_normalize(datos_json)      # aplano con normalize por el obj team dentro de player => team = team.name
    df.rename(columns={'team.name': 'club'}, inplace=True)

    # jugadores solo con >500min
    delanteros = df[
        ((df['position'].str.contains('FW', na=False)) | (df['position'].str.contains('MID', na=False))) &
        (df['minPlayed'] > 500)
    ].copy()

    print(f"Analizando {len(delanteros)} jugadores (>500min jugados)...")

    # visualización
    delanteros['tag_goles'] = delanteros.apply(
        lambda row: row['name'] if row['goals'] >= 5 else '', axis=1
    )

    fig = px.scatter(
        delanteros,
        x='shotsOnTarget',
        y='goals',
        color='club',
        size='minPlayed',          
        hover_name='name',         
        text='tag_goles',     
        title='Efectividad de los delanteros y mediocampistas: goles vs. tiros al arco',
        labels={
            'shotsOnTarget': 'Tiros directos al arco',
            'goals': 'Goles',
            'club': 'Club',
            'minPlayed': 'Minutos jugados'
        },
        template='plotly_dark'     
    )

    fig.update_traces(textposition='top center')

    fig.write_image("efectividad_goles_mid_del.png", scale=3)

    fig.show()

else:
    print(f"Error con la API. Status code: {respuesta.status_code}")


