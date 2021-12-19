# 下载链接 
wget ftp://ftp-2018-openlab:ftp-2018-openlab@10.208.2.210/2018-version/USS/USS_SINGLE/uss-ams-r/$num/*
wget ftp://ftp-2018-openlab:ftp-2018-openlab@10.208.2.210/2018-version/BSS/BSS_SINGLE/bss-ams/6/*

# JAVA命令
java -jar uss-ams-r-service.jar --spring.config.location=123.yml
java -Dloader.path=config/ -jar image/app.jar
jar -xvf app.jar //解压
jar -cvf app.jar //打包

# dump文件导出
jmap -dump:file=ams.dump 7411

# k8s命令
kubectl logs -f  `kubectl get po |grep ams-r|grep -v Running|awk '{print $1}'` -c uss-ams-r-containers
kubectl exec -ti `kubectl get po |grep bss-ams-pod|grep Running|awk '{print $1}'` sh
echo 'kubectl exec -ti `kubectl get po |grep bss-ams-pod|grep Running|awk '{print $1}'` sh' >> log.sh
kubectl delete pod --force --grace-period=0 uss-ams-pod-54fdc75df7-x8fh4
kubectl delete pod --force --grace-period=0 $(kubectl get pod |grep uss-ams-r|grep Terminating|awk '{print $1}')
kubectl delete pod --force --grace-period=0 $(kubectl get pod |grep Terminating|awk '{print $1}')
kubectl create cm ams --from-file config/ -o yaml --dry-run |kubectl replace -f -
kubectl create cm ams --from-file . -o yaml --dry-run |kubectl replace -f -
kubectl delete pod --force --grace-period=0 $(kubectl get pod |grep uss-ams-r|grep Terminating|awk '{print $1}')

kubectl rollout undo deploy mg-scene-alarm-be-pod
# linux命令
ls -l | grep "^-" | wc -l
find . -type f -exec dos2unix {} \;

# 短信猫地址
http://192.168.120.69:8900/sms/sendMessage
http://192.168.8.221:8900/sms/sendMessage

# linux查看端口
netstat -lnp

# linux抓包容器获取Pid
docker inspect -f {{.State.Pid}} 3cc87e12dc82//容器id
120108
nsenter --target 120108 -n/
tcpdump tcp -s 0 -A  -w ./http.cap //抓取存文件
tcpdump -i any host 192.168.121.16 -w Cap20200529.cap   过滤ip
tcpdump -i any port 8080  -w Cap20200529.cap   过滤端口
tcpdump -n -i eth0  tcp and host 172.20.74.36 and not host 172.20.74.21  and not 10.254.159.63  -A|grep -v options
tcpdump -n -i eth0  tcp and host 172.20.73.196  and not host 172.20.73.220  and not 10.254.159.6 -A
tcpdump -n -i ens8f0 port 16501   -A
tcpdump -n -i ens8f0 host   -A //src dst


# 自动化脚本
python manage.py runtest techtest.ams --report-type html -w test_result

# kafka命令
kafka-console-producer.sh  --broker-list  192.168.121.26:9093  --topic  VMS-IDL-ALARM //发kafka
kafka-console-consumer.sh --bootstrap-server kafka-hs:9093 --topic viid_videolabel_topic  --from-beginning
kafka-topics.sh --create --zookeeper zookeeper-cs:2181 --replication-factor 1 --partitions 10 --topic  VMS-IDL-ALARM

kafka-topics.sh --zookeeper zookeeper-cs:2181 --list
kafka-consumer-groups.sh --describe --bootstrap-server kafka-hs:9093 -group uss_ams_r
kafka-topics.sh --delete --zookeeper zookeeper-cs:2181 --topic VMS-IV-ALARMM



kafka-console-consumer.sh --bootstrap-server kafka-hs:9093 --topic VMS-IDL-ALARM --group uss_ams_r //控制台消费kafka
kafka-topics.sh --zookeeper zookeeper-cs:2181 --topic uniform-device-change --describe
# kafka windows命令
kafka-console-consumer.bat --bootstrap-server 127.0.0.1:9092 --topic zz  --from-beginning
kafka-topics.bat --create --zookeeper 127.0.0.1:2181 --replication-factor 1 --partitions 1 --topic  zz
kafka-topics.bat --delete --zookeeper 127.0.0.1:2181  --topic zz
# pgsql命令
psql -h svc-pgsql -U pgsql -d uss_ams

# redis命令
select 0/1切换仓库
del key
kubectl get svc |grep redis
redis-m-svc                   ClusterIP   10.254.125.250   <none>        6379/TCP                                                1d
redis-svc-np                  NodePort    10.254.127.37    <none>        6379:6379/TCP                                           1d
[root@bogon alarm]# kubectl get po |grep redis
redis-node-0                                  1/1       Running     0          1d
redis-node-m-58984589d-dl9zl                  1/1       Running     0          1d
[root@bogon alarm]# kubectl exec -ti redis-node-0 bash
bash-4.3# redis-cli -a admin_123 -h 10.254.127.37
10.254.127.37:6379> smembers "puid_offline_set"
 1) "10000000001131000002"
 2) "10000000001131000001"
 10.254.127.37:6379> SREM "puid_offline_set" "10000000001131000001"
(integer) 1
10.254.127.37:6379> smembers "puid_offline_set"
 1) "10000000001131000002"

