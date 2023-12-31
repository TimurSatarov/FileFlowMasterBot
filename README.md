## Overview

This Java-based Telegram bot is designed for efficient message processing, handling various message types, including text, documents, and photos. It utilizes the Spring Boot framework and RabbitMQ for seamless communication and distribution of messages.

## Key Features

- **Versatile Message Handling**: Capable of processing text, documents, and photos.
- **Integration**: Integrates with RabbitMQ for message distribution and efficient processing.
- **Robust Framework**: Built using Spring Boot for robust and scalable development.
- **Logging**: Utilizes Log4j for comprehensive logging and debugging.
- **Simplified Code**: Implements Lombok for reducing boilerplate code.

## Prerequisites

Before running this project, ensure you have the following dependencies installed:

- Java Development Kit (JDK) 11 or higher
- Maven for managing project dependencies
- RabbitMQ server for message queuing

## Getting Started

1. Clone this repository to your local machine.
2. Configure your Telegram bot's name and token in the `application.properties` file.
3. Make sure your RabbitMQ server is running and properly configured.