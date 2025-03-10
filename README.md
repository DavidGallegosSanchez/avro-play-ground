# Producer and Consumer using avro schema

## Set up Kafka Environment using Docker

- This command will set up Zookeeper and the Kafka broker in your local environment.

```aidl
docker-compose up
```
## Sending request to the producer

- Once both Spring Boot services, shop-order-consumer and shop-order-service, are up and running, you can use the following JSON to make a POST request to http://localhost:8083/api/createOrder:

```
{
  "nameShop": "Scalpers",
  "address": "Reyes católicos",
  "city": "Granada",
  "client": {
    "id": 123,
    "name": "David",
    "cardNumber": 12345
  },
  "items": [
    {
      "id": "123",
      "name": "Scbeach oxford",
      "quantity": 1,
      "price": 59.90
    },
    {
      "id": "231",
      "name": "Sc vacances",
      "quantity": 1,
      "price": 59.90
    },
    {
      "id": "2231",
      "name": "Sckull socks",
      "quantity": 1,
      "price": 14.90
    }
  ],
  "cp": "18009"
}

```
