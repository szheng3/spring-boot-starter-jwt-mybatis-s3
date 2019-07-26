run:
	docker rm -f accounting | true
	docker rmi accounting | true
	make docker


docker:
	git pull
	mvn package -Dmaven.test.skip=true
	docker build -t accounting -f Dockerfile  --cache-from accounting .
	docker run -d --restart=unless-stopped -p 8080:8080 --link demo-mysql:mysql  --name accounting -v /logs/:/logs/ -v /root/.m2:/root/.m2 accounting
	rm -r target
