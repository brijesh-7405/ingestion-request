
# Ingestion Request API Integration

### Introduction
This document provides guidance on integrating the Ingestion Request API with your Angular project. It covers setting up CORS (Cross-Origin Resource Sharing), configuring database connections, and ensuring seamless communication between the frontend and backend modules.

## Environment Setup
Before proceeding with the integration, ensure you have the following prerequisites installed:
* **Node.js and npm**: Ensure Node.js is installed on your system.
* **Angular CLI**: Install Angular CLI globally using npm if not already installed:
```sh
npm install -g @angular/cli
```

## Backend Configuration
### CORS Configuration
To allow your Angular application to communicate with the backend API, configure CORS settings in your backend application. Modify the `ApplicationConfiguration.java` class file as follows:
```sh
@Configuration
public class ApplicationConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200") // Update with your Angular application's domain and port
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Add other HTTP methods as needed
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}

```
**Note:** Update `allowedOrigins` with your Angular application's domain and port. Add any additional HTTP methods under `allowedMethods` that your application requires.
#
## Frontend Integration
### API Configuration
To connect your Angular application with the backend API, update the `app.service.ts` file located in src/app directory:
```sh
import { HttpBackend, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { ApplicationReference, DecisionRequestDTO, IngestionRequest, IngestionRequestDetails } from './models/models';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  private url = 'http://localhost:3000/api/v1/';


  // Example method to fetch data from the API
  getRequestById(id: any): Observable<IngestionRequestDetails> {
    return this.httpWithoutInterceptor.get<any>(`${this.url}ingestion_requests/${id}`)
  }

}

```
**Note:** Update `apiUrl` with your backend API URL, including the correct port and domain if different from `localhost:3000`.
#
## Running the Application

To run your integrated application, follow these steps:
1. **Start Backend Server:** Ensure your backend server is running. Navigate to the backend project directory and start the server using the appropriate command.
2. **Install Required Dependencies:** Ensure all Angular dependencies are installed. Navigate to your Angular project directory and install dependencies:
```sh
npm install
```
3. **Start Angular Development Server:** Navigate to your Angular project directory and start the development server:
```sh
 ng serve
```

4. **Access the Application:** Open your web browser and navigate to http://localhost:4200 (or your custom Angular application URL). Ensure that the backend API calls from Angular to the backend server are working as expected without CORS errors.

#
## Conclusion
By following the steps outlined in this document, you should have successfully integrated the Ingestion Request API with your Angular application. Ensure all configurations are correctly set up to facilitate smooth communication between the frontend and backend modules.

