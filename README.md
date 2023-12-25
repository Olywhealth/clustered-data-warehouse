# ClusteredData Warehouse

This project implements a data warehouse for Bloomberg to analyze FX deals. The system accepts deal details and persists them into a MySQL database. The primary goal is to provide a reliable and efficient solution for handling FX deals with the specified requirements.

## Features

- **Request Logic:**
  - The system processes deal details with the following fields:
    - Deal Unique Id
    - From Currency ISO Code (Ordering Currency)
    - To Currency ISO Code
    - Deal Timestamp
    - Deal Amount in Ordering Currency

- **Validation:**
  - The system performs basic validation on the row structure, including checks for missing fields and type formats using Javax validation and cutomize annotation for validating currency code. While it may not cover all possible cases, the implementation showcases how validations are handled.

- **Data Integrity:**
  - The system ensures that the same request is not imported twice to maintain data integrity by checking the unique request ID before persisting the request.

- **Persistence:**
  - No rollback is allowed; every imported row is saved in the MySQL database.

- **Deployment:**
  - The project includes a Docker Compose configuration for easy deployment. You can set up the environment using the provided Docker Compose file.

## Getting Started

### Prerequisites

- Docker
- Docker Compose
- Maven

### Deployment

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-username/ClusteredData-Warehouse.git
