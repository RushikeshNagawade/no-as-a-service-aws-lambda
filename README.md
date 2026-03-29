# Serverless Spring Boot 3 API - Demo1

A serverless API application built with Spring Boot 3 and AWS Lambda, created using [`aws-serverless-java-container`](https://github.com/aws/serverless-java-container).

## Project Overview

This project demonstrates a simple REST API that provides random reasons for saying "no". It includes:
- Multiple endpoints for fetching reasons and reason counts
- Spring Boot 3 with Java 17
- AWS Lambda integration via SAM (Serverless Application Model)
- Comprehensive unit tests

The project includes a `template.yml` file for deployment to AWS Lambda and Amazon API Gateway, and can be tested locally using the [SAM CLI](https://github.com/awslabs/aws-sam-cli). 

## Prerequisites

* [Java 17](https://www.oracle.com/java/technologies/downloads/#java17) or later
* [Maven 3.6+](https://maven.apache.org/) (build tool)
* [AWS CLI](https://aws.amazon.com/cli/) (AWS account access)
* [SAM CLI](https://github.com/awslabs/aws-sam-cli) (local testing and deployment)

## Building the Project

You can build the project using Maven:

```bash
$ mvn clean package
```

Or build and deploy using the SAM CLI:

```bash
$ sam build
Building resource 'AwsServerlessSpringboot3ArchetypeFunction'
Running Maven:GradleBuild
Running Maven:CopyArtifacts

Build Succeeded

Built Artifacts  : .aws-sam/build
Built Template   : .aws-sam/build/template.yaml
```

## Testing Locally with SAM CLI

From the project root folder - where the `template.yml` file is located - start the API with the SAM CLI:

```bash
$ sam local start-api

...
Mounting com.aws.noService.StreamLambdaHandler::handleRequest (java17) at http://127.0.0.1:3000/{proxy+} [OPTIONS GET HEAD POST PUT DELETE PATCH]
...
```

### Available Endpoints

Using a new shell, you can send test requests to your API:

**Root endpoint (ping):**
```bash
$ curl -s http://127.0.0.1:3000/

Do you meant to say no?
```

**Get a random reason:**
```bash
$ curl -s http://127.0.0.1:3000/no

<random reason from the service>
```

**Get total count of reasons:**
```bash
$ curl -s http://127.0.0.1:3000/no/count

<total number of reasons>
``` 

## Deploying to AWS

To deploy the application to your AWS account, use the SAM CLI's guided deployment process:

```bash
$ sam deploy --guided
```

Follow the prompts to configure your deployment settings. Once completed, the SAM CLI will output the stack's details, including the API endpoint URL.

### Testing the Deployed API

Once deployment is complete, you can test the endpoints using the provided URL:

```bash
$ curl https://<your-api-id>.execute-api.<region>.amazonaws.com/Prod/

Do you meant to say no?
```

```bash
$ curl https://<your-api-id>.execute-api.<region>.amazonaws.com/Prod/no

<random reason>
```

```bash
$ curl https://<your-api-id>.execute-api.<region>.amazonaws.com/Prod/no/count

<total count>
```
