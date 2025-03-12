# Despliegues de back-end

Para seguir esta guía se da por entendido que ya se cuenta con la sintalación de docker y minikube.

## Desplegar Base de datos Postgres en k8
Aplicar los siguientes comandos para desplegar una base de datos en postgres en K8.

```bash
    kubectl apply -f postgres-configmap.yml
    kubectl apply -f postgres-deploy.yml
    kubectl apply -f postgres-service.yml
```

Valida que los pods de postgres se estan ejecutando correctamente.

```bash
    kubectl get pods
```

## Preparar imagen del backend
1. Clonar repositorio del back-end en Java
2. Compilar repo

```bash
    mvn clean package -DskipTests
```
3. Asegurarse de que se generó el archivo .jar dentro de la carpeta */target*. Dentro del repo ejecutar el siguiente comando para generar la imagen.
```bash
    docker build -t user-service:1.0 .
```
4. Cargar la imagen de docker local al docker de minikube
```bash
    minikube image load user-service:1.0
```
5. Conectarse al docker de minikube.
```bash
    eval $(minikube -p minikube docker-env)
```
6. Validar que la imagen "user-service:1.0" se cargo correctamente con el comando:
```bash
    docker images
```
Tomese en cuenta que la lista de imagenes corresponde al docker de minikube.

7. Regresar al docker de la maquina local
```bash
    eval $(minikube -p minikube docker-env --unset)
```

## Desplegar backend en k8
```bash
    kubectl apply -f users-configmap.yml
    kubectl apply -f users-deploy.yml
    kubectl apply -f users-service.yml
```

Valida que los pods de users se estan ejecutando correctamente.

```bash
    kubectl get pods
```
Para obtener la URL de user-service puede ejecutar el siguiente comando:

```bash
    minikube service user-service --url
```