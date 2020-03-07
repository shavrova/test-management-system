## Test Management System application
This application is created in learning purposes.

Stack:
- java 11
- spring boot
- thymeleaf
- mysql


To deploy on Google kubernetes engine:
1. Create cluster
2. Connect to cluster:
```
gcloud container clusters get-credentials <cluster_name> --zone <zone> --project <project_name>
```
3. Build spring boot app and push image to dockerHub
4. Create config:
```
kubectl create configmap tms-application-config --from-literal=RDS_DB_NAME=tmsdb --from-literal=RDS_HOSTNAME=mysql --from-literal=RDS_PORT="3306" --from-literal=RDS_USERNAME=tmsdb-user
```
5. Create secret:
```
kubectl create secret generic tms-application-secret --from-literal=RDS_PASSWORD=test
```
6. Deploy on kubernetes:
```
kubectl apply -f mysql-database-data-volume-persistentvolumeclaim.yaml,mysql-deployment.yaml,mysql-service.yaml,tms-application-deployment.yaml,tms-application-service.yaml
```
To run locally:
1. Start mysql docker container:
```
docker run --detach --env MYSQL_ROOT_PASSWORD=root --env MYSQL_USER=tmsdb-user --env MYSQL_PASSWORD=test --env MYSQL_DATABASE=tmsdb --name mysql --publish 3306:3306 mysql:5.7
```
2. Run spring boot application 

