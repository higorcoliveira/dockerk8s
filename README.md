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
Em seguida, criar o serviço
```kubectl apply -f service.yaml```

### Expor o serviço
```kubectl port-forward service/spring-boot-jib 8081:80```

## Push para o ECR
-1) Para realizar o push no ECR, descomentar a linha 71 e comentar a linha 70 do pom.xml.
Em seguida, executar o comando (é necessário ter o aws-cli):
```
ECR_PASSWORD=$(aws ecr get-login-password --region us-east-1) mvn compile jib:build
```
Esse comando pega a credencial para push da imagem no ECR, baseando-se no profile aws configurado 
na máquina e no arquivo ~/.aws/credentials. Em seguida, escreve na variável de ambiente $ECR_PASSWORD

-2) Caso não queira instalar o aws-cli e utilizar variável de ambiente, pode ser utilizada a ferramenta 
https://github.com/awslabs/amazon-ecr-credential-helper. Após instalar e configurar o utilitário
seguindo as instruções contidas no github, descomentar a linha 77 e comentar a linha 76 do pom.xml

## Sobre o Jip 
- Ferramenta para construir containers java (https://github.com/GoogleContainerTools/jib)
- Otimiza a construção das imagens por camada, tornando os deploys mais rápidos
- Para analisar as camadas, instalar a ferramenta *dive* e executar 
    ```dive localhost:5000/spring-boot-jib```
- É possível utilizar imagens diferentes da que é utilizada por padrão

