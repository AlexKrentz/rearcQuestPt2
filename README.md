# Rearc Quest 2 – Population Data Sync with AWS S3

This project is a Spring Boot application that fetches population data from the [DataUSA API](https://honolulu-api.datausa.io/tesseract/data.jsonrecords) and uploads the result as a `.json` file to an AWS S3 bucket.

## Technologies Used

- Java 17
- Spring Boot
- AWS SDK v2 (S3)
- Log4j2
- RestTemplate
- AWS CLI credentials (via `~/.aws/credentials`)

## How It Works

1. **API Client**  
   The application makes an HTTP GET request to the DataUSA API to retrieve population data by Nation and Year.

2. **File Handling**  
   The JSON response is written to a temporary file, named with a timestamp (e.g., `population-data-20250728-230101.json`).

3. **AWS S3 Upload**  
   The temporary file is then uploaded to the S3 bucket named `rearcquestapibucket` under the prefix `population/`.

## Setup Instructions

### 1. AWS Credentials

Make sure your AWS CLI is configured properly:

```bash
aws configure
```
Or ensure the following file exists with valid credentials:

~/.aws/credentials
