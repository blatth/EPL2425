import requests
import pandas as pd
import plotly.express as px
from sklearn.preprocessing import StandardScaler
from sklearn.decomposition import PCA
from sklearn.cluster import KMeans

print("Conectando con la API...")

url = "http://host.docker.internal:8080/players"
respuesta = requests.get(url)

if respuesta.status_code == 200:
    print("Datos obtenidos")
    datos_json = respuesta.json()

    df = pd.json_normalize(datos_json)
    df.rename(columns={'team.name': 'club'}, inplace=True)

    df = df[df['minPlayed'] > 500].copy()
    print(f"Analizando {len(df)} jugadores (>500min jugados)...")

    tactic_vars = [
        'touches', 'passesCompleted', 'succfThirdPasses', 'progCarries', 
        'carriesEWChance', 'shots', 'crosses', 'interceptions', 'tackles', 
        'blocks', 'clearances', 'groundDuelsWon', 'aerialDuelsWon', 'fouls'
    ]

    for var in tactic_vars:
        df[var + '_p90'] = (df[var] / df['minPlayed']) * 90

    variables_p90 = [var + '_p90' for var in tactic_vars]

    df['perfil'] = 'No analizado'

    # arquetipo por posición - k_clusters
    subarq = {'DEF': 3, 'MID': 4, 'FW': 3}

    print("--- Iniciando análisis por subarquetipos ---")

    for pos, k_clusters in subarq.items():
        print(f"\n>> Analizando la posición asociada: {pos}")
        
        # divido jugadores por posición
        mask = df['position'].str.contains(pos, na=False)
        sub_df = df[mask].copy()
        
        if sub_df.empty:
            continue
            
        scaler_pos = StandardScaler()
        X_sub = scaler_pos.fit_transform(sub_df[variables_p90])
        
        kmeans = KMeans(n_clusters=k_clusters, random_state=42, n_init='auto')
        sub_df['cluster_id'] = kmeans.fit_predict(X_sub)
        
        # todo esto es principalmente utilizado para clasificar los subarquetipos con algún nombre
        resumen = sub_df.groupby('cluster_id')[variables_p90].mean()
        
        # extraigo la matriz de desvíaciones respecto al prom general
        centroid = kmeans.cluster_centers_

        for cluster_id in range(k_clusters):
            cantidad = len(sub_df[sub_df['cluster_id'] == cluster_id])
            
            # guardo las variables más representativas de cada arquetipo ★
            centroid_dic = pd.Series(centroid[cluster_id], index=variables_p90)
            top_rasgos = centroid_dic.nlargest(4).index

            conj_tactico = " + ".join([rasgo.replace('_p90','') for rasgo in top_rasgos])

            etiqueta = f"{pos}_tipo_{cluster_id}"
            print(f"[{etiqueta}] -> {cantidad} jugadores | Conjunto táctico: {conj_tactico}")
            
            
            indices = sub_df[sub_df['cluster_id'] == cluster_id].index
            df.loc[indices, 'perfil'] = etiqueta

    print("Generando el gráfico")
    
    # PCA global para obtener los ejes del gráfico
    scaler_global = StandardScaler()
    X_global = scaler_global.fit_transform(df[variables_p90])
    pca = PCA(n_components=2)
    componentes = pca.fit_transform(X_global)
    
    df['eje_X'] = componentes[:, 0]
    df['eje_Y'] = componentes[:, 1]

    # para excluir arqueros
    df_plot = df[df['perfil'] != 'No analizado'].copy()
    df_plot = df_plot.sort_values('perfil')

    dic_nombres = {
        'DEF_tipo_0': 'Lateral ofensivo',
        'DEF_tipo_1': 'Central clásico',
        'DEF_tipo_2': 'Líbero',
        'MID_tipo_0': 'Volante mixto - 8',
        'MID_tipo_1': 'Enganche - Volante creativo - 10',
        'MID_tipo_2': 'Volante de contención',
        'MID_tipo_3': 'Organizador - 5',
        'FW_tipo_0': 'Primer delantero - De desgaste',
        'FW_tipo_1': '9 de área',
        'FW_tipo_2': 'Extremo'
    }

    df_plot['perfil'] = df_plot['perfil'].replace(dic_nombres)

    fig = px.scatter(
        df_plot, 
        x='eje_X', 
        y='eje_Y', 
        color='perfil', 
        hover_name='name', 
        hover_data=['club', 'position'],
        title='Composición táctica - Sub-clustering por posición',
        labels={
            'eje_X': '- Contención <-> Creación +',
            'eje_Y': 'Volumen de juego',
            'perfil': 'Posición'
        },
        template='plotly_dark'
    )
    
    fig.update_traces(marker=dict(size=9, opacity=0.8, line=dict(width=1, color='DarkSlateGrey')))

    # filtro de equipos
    equipos = sorted(df_plot['club'].unique())
    botones_lista = []
    
    botones_lista.append(dict(
        label="TODOS",
        method="restyle",
        args=[{"marker.opacity": 0.8}]
    ))
    
    for equipo in equipos:
        opacidades_por_color = []
        
        for trace in fig.data:
            opacidad = [0.9 if data[0] == equipo else 0.05 for data in trace.customdata]
            opacidades_por_color.append(opacidad)
            
        botones_lista.append(dict(
            label=equipo,
            method="restyle",
            args=[{"marker.opacity": opacidades_por_color}]
        ))

    fig.update_layout(
        updatemenus=[
            dict(
                type="dropdown",
                active=0,
                buttons=botones_lista,
                direction="down",
                showactive=True,
                x=1.02,          
                xanchor="left",
                y=1.12,          
                yanchor="top",
                bgcolor="#2A2A2A",
                bordercolor="#555555",
                font=dict(color="white", size=12)
            )
        ],
        legend=dict(
            y=0.9,
            yanchor="top"
        )
    )

    fig.write_image("clustering_pos.png", scale=3)
    fig.write_html("grafico_clustering_pos.html")

else: 
    print(f"Error con la API. Status code: {respuesta.status_code}")


# ★ - al tener extraida la matriz con las desvíaciones al promedio general (el centroide de cada cluster)
# luego puedo matchearlos con las variables (o rasgos) tácticas con las que entrené al modelo para extraer
# las (en este caso) 4 más representativas