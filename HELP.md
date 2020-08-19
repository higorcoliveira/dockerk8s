# Getting Started

### Ambiente
Importante configurar o JAVA_HOME para que o maven utilize o Java 11

```export JAVA_HOME=/opt/amazon-corretto-11/```

### Para checar a versão do Java com o maven
```mvn -v```

### Build da aplicação com Jip
Caso tenha o docker instalado localmente
```mvn compile jib:dockerBuild```

Caso não tenha o docker instalado localmente (necessário ter um repositório docker válido)
```mvn compile jib:build```

### Build com parâmetros
```mvn compile jib:dockerBuild -Djib.to.image=myregistry/myimage:latest -Djib.to.auth.username=$USERNAME -Djib.to.auth.password=$PASSWORD```

Exemplo:
```mvn compile jib:dockerBuild -Djib.to.image=localhost:5000/spring-boot-jib:1.0.1```

### Build com properties do spring-boot
```mvn compile jib:dockerBuild -Djib.container.environment=MY_CONFIG="Grupo Mult!"```

### Deploy no k8s
No diretório resources/k8s está o arquivo de deployment, basta aplicar no seu cluster k8s com
```kubectl apply -f deployment.yaml```
```kubectl apply -f service.yaml```

### Expor o serviço
```kubectl port-forward service/spring-boot-jib 8081:80```

## Sobre o Jip 
- Ferramenta para construir containers java (https://github.com/GoogleContainerTools/jib)
- Otimiza a construção das imagens por camada, tornando os deploys mais rápidos
- Para analisar as camadas, instalar a ferramenta *dive* e executar 
    ```dive localhost:5000/spring-boot-jib```
- É possível utilizar imagens diferentes da que é utilizada por padrão

