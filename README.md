# Instruções

Clone o projeto:
```bash
git clone https://github.com/joao0212/banking-validation.git
```

comando para subir o banco de dados
```bash
docker compose up
```

e para subir a api situacao-cadastral

```bash
cd banking-validation && docker compose up
```

prometheus
```bash
docker run -d --name prometheus \
  -p 9090:9090 \
  -v /c/Users/duart/projetos/prometheus.yml:/etc/prometheus/prometheus.yml \
  prom/prometheus
```
ou

```shell script
docker run -d --name prometheus -p 9090:9090 -v C:/Users/duart/Dev/banking-service/prometheus.yaml:/etc/prometheus/prometheus.yml prom/prometheus
```

Prometheus estará acessível em http://localhost:9090 e estará coletando métricas da aplicação Quarkus em execução localmente.

grafana
```bash
docker run -d --name grafana -p 3000:3000 grafana/grafana
```

O Grafana estará acessível em http://localhost:3000 com as credenciais abaixo:

Usuário padrão: admin

Senha padrão: admin

Você será solicitado a redefinir a senha na primeira vez que fizer login.



## Comandos úteis docker
```bash
docker compose down && docker compose up -d
```
