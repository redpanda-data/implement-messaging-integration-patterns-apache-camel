docker network create redpanda-network

docker run -d --name=redpanda --rm ^
    --network redpanda-network ^
    -p 9092:9092 ^
	-v C:\Users\venkara\Documents\Personal\GitHub\apache-camel-and-redpanda\redpanda\custom-scripts:/opt/redpanda/custom-scripts/ ^
	docker.vectorized.io/vectorized/redpanda:latest ^
    redpanda start ^
    --advertise-kafka-addr PLAINTEXT://redpanda:29092,PLAINTEXT_HOST://host.docker.internal:9092 ^
	--kafka-addr PLAINTEXT://0.0.0.0:29092,PLAINTEXT_HOST://0.0.0.0:9092 ^
    --overprovisioned ^
    --smp 1  ^
    --memory 1G ^
    --reserve-memory 500M ^
    --node-id 0 ^
    --check=false