#!/bin/sh
APP="categories"
echo "preparing..."
POD=$(kubectl get pod -l app=$APP -o jsonpath="{.items[0].metadata.name}")
SVC=$(kubectl get svc -l app=$APP -o jsonpath="{.items[0].metadata.name}")
minikube service $SVC --url &> temp.file &
SERVICE_PID=$!
sleep 10
IP=$(cat ./temp.file | grep -oP 'http://[0-9.]+:[0-9]+' | head -n 1)
echo "now doing load test on address: $IP"
start=`date +%s`
for i in $(seq 1 1000); do
    curl -s -o /dev/null $IP/$APP
done
end=`date +%s`
runtime=$((end-start))
echo "$runtime seconds"

kill $SERVICE_PID
