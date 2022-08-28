# Currency Exchange Rate Trend API

Currency exchange rate trend app provides API to retrieve a GIF animation displaying whether the specified currency grew over last day comparing to RUB.

(this is just a demo project, it doesn't solve any real problem)

## Running

To run the application, run the following command in a terminal window (in the complete) directory:

```bash
./gradlew bootRun
```

## Documentation API

[OpenAPI](openapi.yaml)

## Usage

For usage we need JDK 11.0.2.
After running application we can send GetRequest with interested currency in the format of the ISO 4217 standard.

Example:
http://localhost:8080/trend?currency=USD
