infra-up:
	docker compose up -d --build

infra-down:
	docker compose down

api-run:
	./mvnw spring-boot:run

posiciones:
	docker exec -w /app/analisis-posiciones -it entorno_analisis python clustering_pos.py

prediccion_pases:
	docker exec -w /app/analisis-prediccion-pases -it entorno_analisis python prediccion_pas.py

efectividad_gol:
	docker exec -w /app/analisis-efectividad -it entorno_analisis python efectividad_gol.py