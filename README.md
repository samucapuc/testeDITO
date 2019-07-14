# Teste DITO
Test for developer position
By Samuel Oliveira Chaves
14/07/2019
### Parte 1:
  Foi utilizado o spring boot versão 2.0.0.RELEASE com suporte ao ElasticSource 6.5.4.
  Para executar a aplicação, siga os passos abaixo:
  1)  Certifique-se que as variáveis JAVA_HOME e PATH estão configuradas
  2)  Instale o Maven.
  3)  Instale o Elasticsearch 6.5.4
  4)  Abra ${CAMINHO ELASTIC_SOURCE_INSTALADO}\config\elasticsearch.yml e acrescente no final do arquivo: cluster.name: dito-teste
  5)  Inicie o elasticsource em ${CAMINHO ELASTIC_SOURCE_INSTALADO}\bin\elasticsearch.bat ou elasticsearch.sh
  
  Por padrão, na inicialização, a aplicação vai inserir alguns dados caso o perfil seja DEV ou TEST.
  Para buscar todos os eventos por auto complete, execute:
  http://localhost:8080/api/events/findAll?name=com
  
  A pesquisa também pode ser realizada diretamente no elasticsource, no link:
  http://localhost:9200/dito/events/_search?pretty=true
  
  Para executar a coleta de dados, execute:
  http://localhost:8080/api/events e passe um json na estrutura abaixo:
  ```
    {
      "id": 1,	
      "event": "comprou-produto",
      "timestamp": "2016-09-22T13:57:32.2311892-03:00",
      "custom_data": [
        {
          "key": "product_name",
          "value": "Camisa Azul"
        },
        {
          "key": "transaction_id",
          "value": "3029384"
        },
        {
          "key": "product_price",
          "value": 100
        }
      ]
    }
  ```

### Parte 2:
  Para trazer as timelines dos eventos ordenados pelo id da transação, execute:
  http://localhost:8080/api/timelines
  
