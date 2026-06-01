import requests
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

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
    ]

    print(f"Analizando {len(delanteros)} jugadores (>500min jugados)...")

    # visualización
    sns.set_theme(style="darkgrid")
    plt.figure(figsize=(10, 6))

    scatter = sns.scatterplot(
        data=delanteros,
        x='shotsOnTarget',
        y='goals',
        hue='club',
        size='minPlayed',
        sizes=(50, 400),
        alpha=0.7
    )

    for i in range(delanteros.shape[0]):
        if delanteros['goals'].iloc[i] >= 5:
            plt.text(
                delanteros['shotsOnTarget'].iloc[i] + 0.5,
                delanteros['goals'].iloc[i],
                delanteros['name'].iloc[i],
                fontsize=5
            )

    plt.title('Efectividad de los delanteros y mediocampistas: goles vs. tiros al arco')
    plt.xlabel('Tiros directos al arco')
    plt.ylabel('Goles')
    plt.legend(bbox_to_anchor=(1.05,1), loc='upper left')
    plt.tight_layout()

    plt.savefig("efectividad_del.png", dpi=300)
    plt.show()

else:
    print(f"Error con la API. Status code: {respuesta.status_code}")


