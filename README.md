# Dealer Vehicle Management Application

This is a **Full-Stack Web Application** built using **Spring Boot (Backend)**, **React.js (Frontend)**, and **PostgreSQL (Neon Cloud Database)**.  
It enables dealers to manage and perform CRUD operations on vehicle and dealer data.

---

## Live Application

- **Frontend (React):** [https://dynamic-raindrop-d8cc0a.netlify.app/]
- **Backend (Spring Boot):** [https://dealer-vehicle-api.onrender.com]
- **GitHub Repository:** [https://github.com/bikashwebsite/dealer-vehicle-api]


> **Note:**  
> The backend is hosted on Render’s free tier, so it may take **40–60 seconds** to respond on the first request if inactive for a while.  
> After the first hit, it runs normally.

---

## Project Overview

This application allows **dealers** to manage their vehicle details in a centralized system.  
It demonstrates **full-stack integration** of React frontend and Spring Boot backend with a Neon PostgreSQL cloud database.

### Features
- Add, View, Update, Delete vehicles and dealers  
- Fetch premium dealers’ vehicle list  
- Responsive React UI  
- Cloud-deployed backend and database  
- Environment-based configuration for secure deployment

---

## Tech Stack

**Frontend:**
- React.js (Hooks, Functional Components)
- Axios for REST API calls
- React Router for navigation
- Netlify for deployment

**Backend:**
- Spring Boot 3.5.7  
- Spring Data JPA  
- PostgreSQL (Neon Cloud)
- Render for deployment

**Database:**
- Neon Cloud PostgreSQL (SSL enabled)

---

## Database Configuration (Neon Cloud)

Backend connects to **Neon Cloud PostgreSQL** using SSL.  

> **Note:**  
> Database credentials (URL, username, password) are **not publicly shared** in this repository for security reasons.  
> They are securely stored as environment variables in the Render dashboard.

---

## Project Structure

dealer-vehicle-api/
├── pom.xml
├── src/
│ ├── main/java/com/microservice/dealervehicle/dealer_vehicle_api/
│ │ ├── controller/
│ │ │ ├── DealerController.java
│ │ │ └── VehicleController.java
│ │ ├── model/
│ │ ├── repository/
│ │ ├── service/
│ │ └── DealerVehicleApiApplication.java
├── frontend/
│ ├── public/
│ ├── src/
│ │ ├── components/
│ │ ├── pages/
│ │ ├── App.js
│ │ └── index.js
│ └── package.json
└── README.md


---

## API Endpoints

| Endpoint | Method | Description |
|-----------|---------|-------------|
| `/vehicles` | GET | Get all vehicles |
| `/vehicles/{id}` | GET | Get vehicle by ID |
| `/vehicles` | POST | Add a new vehicle |
| `/vehicles/{id}` | PUT | Update a vehicle |
| `/vehicles/{id}` | DELETE | Delete a vehicle |
| `/vehicles/premium` | GET | Fetch all premium dealers’ vehicle list |
| `/dealers` | GET | Get all dealers |
| `/dealers/{id}` | GET | Get dealer by ID |
| `/dealers` | POST | Add a new dealer |
| `/dealers/{id}` | PUT | Update dealer |
| `/dealers/{id}` | DELETE | Delete dealer |

---

## Application Flow

1. **React Frontend (Netlify)** – The user interacts with the frontend UI to manage dealer and vehicle data.  
2. **Axios API Calls** – All frontend actions trigger REST API requests to the backend.  
3. **Spring Boot Backend (Render)** – The backend processes the requests and interacts with the Neon PostgreSQL database.  
4. **Neon Cloud PostgreSQL** – Stores all dealer and vehicle records.  
5. **Response Returned to Frontend** – The data is displayed dynamically in the frontend UI.  

---

## Key Highlights

- Fully functional **Full Stack Java + React** application  
- **Cloud PostgreSQL** database with SSL security  
- Hosted on **Render (Backend)** and **Netlify (Frontend)**  
- Uses **Environment Variables** for sensitive data  
- Implements **Premium Dealer Vehicle API**  
- Fast, scalable, and modular structure  
- Clean UI design with error handling  

---

## Future Enhancements

- **JWT Authentication & Role-based Access Control**
- **Microservices-based Architecture**
- **Containerization with Docker & Kubernetes**
- **Integration of JUnit and Mockito Tests**
- **CI/CD Pipeline with Jenkins or GitHub Actions**
- **Metrics & Monitoring via Grafana/Prometheus**
- **AI-assisted recommendations for dealers (future scope)**

---

##  Deployment Details

### Backend (Render)
- Type: **Web Service**
- Source: GitHub (`dealer-vehicle-api`)
- Language: **Docker**
- Environment Variables:
  - `SPRING_DATASOURCE_URL`
  - `SPRING_DATASOURCE_USERNAME`
  - `SPRING_DATASOURCE_PASSWORD`
- Exposed URL: `https://dealer-vehicle-api.onrender.com`

### Frontend (Netlify)
- Source: GitHub (`dealer-vehicle-api`)
- Directory: `/frontend`
- Build Command: `npm run build`
- Publish Directory: `frontend/build`
- Live URL: `https://dynamic-raindrop-d8cc0a.netlify.app/`

---

## How to Run Locally

### Backend Setup

```bash
# Clone repository
git clone https://github.com/bikashwebsite/dealer-vehicle-api.git
cd dealer-vehicle-api

# Run Spring Boot app
mvn spring-boot:run

----

###  SCREENSHOTS

-- All API screenshots are pushed to the application repository with a folder name "docs".