# 📦 Smart Inventory & Billing Management System

A production-ready full-stack web application for managing inventory, products, customers, suppliers, billing, and analytics — built with **React + Vite** on the frontend and **Spring Boot + MySQL** on the backend.

---

## 📋 Table of Contents

- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Prerequisites](#-prerequisites)
- [Database Setup](#-database-setup)
- [Backend Setup](#-backend-setup)
- [Frontend Setup](#-frontend-setup)
- [Environment Variables](#-environment-variables)
- [API Documentation](#-api-documentation)
- [Default Credentials](#-default-credentials)
- [Seed Data](#-seed-data)
- [Deployment](#-deployment)
- [Troubleshooting](#-troubleshooting)

---

## ✨ Features

### 🔐 Authentication & Authorization
- JWT-based login and registration
- Role-based access control (Admin / User)
- Protected frontend routes
- Token refresh and auto-logout on expiry
- Password encryption with BCrypt

### 📊 Dashboard
- Live KPI cards (Total Products, Revenue, Low Stock Alerts, Total Invoices)
- Monthly revenue and order trend charts (Chart.js)
- Stock by category donut chart
- Inventory health progress bars
- Recent activity feed
- Performance KPI mini-cards

### 📦 Product Management
- Add / Edit / Delete products
- Auto-generated SKU codes
- Product image upload
- Category-wise filtering
- Paginated product table with search
- Low stock detection and alerts

### 🏭 Inventory Management
- Stock In / Stock Out operations
- Full inventory transaction history
- Per-product audit log
- Auto low-stock notification trigger
- Warehouse-level stock tracking

### 🧾 Billing System
- Create invoices with multiple line items
- Auto GST calculation (18% default, configurable)
- Invoice status tracking (Pending / Paid / Cancelled)
- PDF invoice download
- Customer billing history

### 👥 Customers
- Add / Edit / Delete customers
- GSTIN storage for B2B customers
- Customer purchase history
- Search and filter

### 🚛 Suppliers
- Add / Edit / Delete suppliers
- Supplier-to-product mapping
- Contact person and GSTIN fields

### 📈 Analytics
- Revenue trends (last 12 months)
- Best-selling products
- Category-wise sales breakdown
- Export reports

### 🔔 Notifications
- Low stock alerts (auto-generated)
- Invoice generated alerts
- Order status updates
- Mark as read / clear all

---

## 🛠 Tech Stack

| Layer       | Technology                                      |
|-------------|--------------------------------------------------|
| Frontend    | React 18, Vite, Tailwind CSS, React Router v6   |
| State       | Context API, Axios                              |
| Charts      | Chart.js, react-chartjs-2                       |
| Backend     | Java 17, Spring Boot 3.2                        |
| Security    | Spring Security, JWT (jjwt 0.11.5)             |
| ORM         | Spring Data JPA, Hibernate                      |
| Database    | MySQL 8.x                                       |
| PDF         | iText 7                                         |
| Build       | Maven (backend), npm (frontend)                 |

---

## 📁 Project Structure

```
smart-inventory/
│
├── backend/                          # Spring Boot application
│   ├── pom.xml
│   └── src/main/
│       ├── java/com/smartinv/
│       │   ├── SmartInventoryApplication.java
│       │   ├── config/
│       │   │   ├── SecurityConfig.java        # CORS, JWT filter chain
│       │   │   ├── WebConfig.java             # Static file serving
│       │   │   └── DataSeeder.java            # Dummy data on startup
│       │   ├── controller/
│       │   │   ├── AuthController.java
│       │   │   ├── DashboardController.java
│       │   │   ├── ProductController.java
│       │   │   ├── CategoryController.java
│       │   │   ├── InventoryController.java
│       │   │   ├── InvoiceController.java
│       │   │   ├── CustomerController.java
│       │   │   ├── SupplierController.java
│       │   │   ├── AnalyticsController.java
│       │   │   └── NotificationController.java
│       │   ├── service/
│       │   │   ├── AuthService.java
│       │   │   ├── DashboardService.java
│       │   │   ├── ProductService.java
│       │   │   ├── InventoryService.java
│       │   │   ├── InvoiceService.java
│       │   │   ├── CustomerService.java
│       │   │   ├── SupplierService.java
│       │   │   ├── AnalyticsService.java
│       │   │   └── NotificationService.java
│       │   ├── repository/
│       │   │   ├── UserRepository.java
│       │   │   ├── ProductRepository.java
│       │   │   ├── CategoryRepository.java
│       │   │   ├── CustomerRepository.java
│       │   │   ├── SupplierRepository.java
│       │   │   ├── InvoiceRepository.java
│       │   │   ├── InventoryLogRepository.java
│       │   │   └── NotificationRepository.java
│       │   ├── entity/
│       │   │   ├── User.java
│       │   │   ├── Product.java
│       │   │   ├── Category.java
│       │   │   ├── Customer.java
│       │   │   ├── Supplier.java
│       │   │   ├── Invoice.java
│       │   │   ├── InvoiceItem.java
│       │   │   ├── InventoryLog.java
│       │   │   └── Notification.java
│       │   ├── dto/
│       │   │   ├── request/
│       │   │   │   ├── LoginRequest.java
│       │   │   │   ├── RegisterRequest.java
│       │   │   │   ├── ProductRequest.java
│       │   │   │   ├── InvoiceRequest.java
│       │   │   │   ├── CustomerRequest.java
│       │   │   │   ├── SupplierRequest.java
│       │   │   │   └── StockUpdateRequest.java
│       │   │   └── response/
│       │   │       ├── AuthResponse.java
│       │   │       ├── DashboardStatsResponse.java
│       │   │       ├── ProductResponse.java
│       │   │       ├── InvoiceResponse.java
│       │   │       ├── CustomerResponse.java
│       │   │       ├── SupplierResponse.java
│       │   │       └── ApiResponse.java
│       │   ├── security/
│       │   │   ├── jwt/
│       │   │   │   ├── JwtUtils.java
│       │   │   │   └── JwtAuthFilter.java
│       │   │   └── UserDetailsServiceImpl.java
│       │   ├── exception/
│       │   │   ├── GlobalExceptionHandler.java
│       │   │   ├── ResourceNotFoundException.java
│       │   │   └── BadRequestException.java
│       │   └── utils/
│       │       ├── SkuGenerator.java
│       │       └── InvoiceNumberGenerator.java
│       └── resources/
│           └── application.properties
│
├── frontend/                         # React + Vite application
│   ├── package.json
│   ├── vite.config.js
│   ├── tailwind.config.js
│   ├── index.html
│   └── src/
│       ├── main.jsx
│       ├── App.jsx
│       ├── index.css
│       ├── routes/
│       │   └── AppRoutes.jsx          # All routes with protection
│       ├── context/
│       │   ├── AuthContext.jsx        # JWT state, login/logout
│       │   └── NotificationContext.jsx
│       ├── services/
│       │   ├── api.js                 # Axios instance with interceptors
│       │   ├── authService.js
│       │   ├── productService.js
│       │   ├── inventoryService.js
│       │   ├── invoiceService.js
│       │   ├── customerService.js
│       │   ├── supplierService.js
│       │   ├── analyticsService.js
│       │   └── notificationService.js
│       ├── hooks/
│       │   ├── useAuth.js
│       │   ├── usePagination.js
│       │   └── useDebounce.js
│       ├── layouts/
│       │   ├── MainLayout.jsx         # Sidebar + topbar shell
│       │   └── AuthLayout.jsx
│       ├── components/
│       │   ├── common/
│       │   │   ├── Loader.jsx
│       │   │   ├── Toast.jsx
│       │   │   ├── Pagination.jsx
│       │   │   ├── ConfirmModal.jsx
│       │   │   ├── EmptyState.jsx
│       │   │   └── StatusBadge.jsx
│       │   ├── layout/
│       │   │   ├── Sidebar.jsx
│       │   │   └── Topbar.jsx
│       │   ├── charts/
│       │   │   ├── RevenueLineChart.jsx
│       │   │   └── CategoryDonutChart.jsx
│       │   └── modals/
│       │       ├── ProductModal.jsx
│       │       ├── StockModal.jsx
│       │       ├── InvoiceModal.jsx
│       │       ├── CustomerModal.jsx
│       │       └── SupplierModal.jsx
│       ├── pages/
│       │   ├── auth/
│       │   │   ├── Login.jsx
│       │   │   └── Register.jsx
│       │   ├── dashboard/
│       │   │   └── Dashboard.jsx
│       │   ├── products/
│       │   │   └── Products.jsx
│       │   ├── inventory/
│       │   │   └── Inventory.jsx
│       │   ├── billing/
│       │   │   └── Billing.jsx
│       │   ├── customers/
│       │   │   └── Customers.jsx
│       │   ├── suppliers/
│       │   │   └── Suppliers.jsx
│       │   ├── analytics/
│       │   │   └── Analytics.jsx
│       │   └── notifications/
│       │       └── Notifications.jsx
│       └── utils/
│           ├── formatCurrency.js
│           ├── formatDate.js
│           └── constants.js
│
├── sql/
│   ├── schema.sql                    # Full database schema
│   └── seed.sql                      # Sample data
│
└── README.md
```

---

## ✅ Prerequisites

Make sure the following are installed on your machine:

| Tool        | Version     | Download |
|-------------|-------------|----------|
| Java JDK    | 17+         | [adoptium.net](https://adoptium.net) |
| Maven       | 3.8+        | [maven.apache.org](https://maven.apache.org) |
| Node.js     | 18+         | [nodejs.org](https://nodejs.org) |
| MySQL       | 8.0+        | [mysql.com](https://dev.mysql.com/downloads/) |
| Git         | Latest      | [git-scm.com](https://git-scm.com) |

Verify installations:
```bash
java -version
mvn -version
node -version
npm -version
mysql --version
```

---

## 🗄 Database Setup

### 1. Start MySQL and log in
```bash
mysql -u root -p
```

### 2. Create the database
```sql
CREATE DATABASE smart_inventory CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 3. (Optional) Create a dedicated user
```sql
CREATE USER 'smartinv_user'@'localhost' IDENTIFIED BY 'StrongPass@123';
GRANT ALL PRIVILEGES ON smart_inventory.* TO 'smartinv_user'@'localhost';
FLUSH PRIVILEGES;
```

### 4. Run the schema file
```bash
mysql -u root -p smart_inventory < sql/schema.sql
```

### 5. Load seed data
```bash
mysql -u root -p smart_inventory < sql/seed.sql
```

> **Note:** If you run the Spring Boot app with `spring.jpa.hibernate.ddl-auto=update`, Hibernate will auto-create the tables. You only need to run `seed.sql` for sample data.

---

## ⚙️ Backend Setup

### 1. Navigate to backend folder
```bash
cd backend
```

### 2. Update `application.properties`

Open `src/main/resources/application.properties` and set your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/smart_inventory?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

### 3. Build the project
```bash
mvn clean install -DskipTests
```

### 4. Run the application
```bash
mvn spring-boot:run
```

Or run the generated JAR:
```bash
java -jar target/smart-inventory-1.0.0.jar
```

The backend starts at: **http://localhost:8080**

### 5. Verify it's running
```bash
curl http://localhost:8080/api/health
# Expected: {"status":"UP"}
```

---

## 🖥 Frontend Setup

### 1. Navigate to frontend folder
```bash
cd frontend
```

### 2. Install dependencies
```bash
npm install
```

### 3. Set environment variables

Create a `.env` file in the `frontend/` folder:

```env
VITE_API_BASE_URL=http://localhost:8080/api
```

### 4. Start the development server
```bash
npm run dev
```

The frontend runs at: **http://localhost:5173**

### 5. Build for production
```bash
npm run build
```

The production build goes into `frontend/dist/`.

---

## 🔐 Environment Variables

### Backend — `application.properties`

| Key | Description | Default |
|-----|-------------|---------|
| `spring.datasource.url` | MySQL JDBC URL | `jdbc:mysql://localhost:3306/smart_inventory` |
| `spring.datasource.username` | MySQL username | `root` |
| `spring.datasource.password` | MySQL password | `root` |
| `app.jwt.secret` | JWT signing secret (change in prod!) | Long random string |
| `app.jwt.expiration` | Token expiry in ms | `86400000` (24 hours) |
| `app.upload.dir` | Directory for product image uploads | `uploads/` |

### Frontend — `.env`

| Key | Description | Default |
|-----|-------------|---------|
| `VITE_API_BASE_URL` | Backend API base URL | `http://localhost:8080/api` |

---

## 🔑 Default Credentials

After running the seed data, you can log in with:

| Role  | Email | Password |
|-------|-------|----------|
| Admin | `admin@smartinv.com` | `Admin@123` |
| User  | `user@smartinv.com` | `User@123` |

> ⚠️ Change these credentials before deploying to production.

---

## 🌱 Seed Data

The `DataSeeder.java` bean runs automatically on the first startup and inserts:

- **2 users** (admin + user)
- **6 product categories** (Electronics, Peripherals, Accessories, Cables, Networking, Storage)
- **4 suppliers** (DigiParts India, TechSupply Co, Bharat Electronics, QuickStock Pvt Ltd)
- **20 products** with realistic SKUs, prices, and stock levels
- **10 customers** with GSTIN details
- **30 invoices** with line items spread across the last 12 months
- **50 inventory log entries**
- **10 notifications** (low stock + invoice alerts)

---

## 📡 API Documentation

### Base URL
```
http://localhost:8080/api
```

### Authentication

All endpoints except `/auth/**` require a Bearer token in the header:
```
Authorization: Bearer <your_jwt_token>
```

---

### 🔐 Auth Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | `/auth/login` | Login, returns JWT | ❌ |
| POST | `/auth/register` | Register new user | ❌ |
| GET | `/auth/me` | Get current user profile | ✅ |

**Login Request:**
```json
{
  "email": "admin@smartinv.com",
  "password": "Admin@123"
}
```

**Login Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "type": "Bearer",
  "id": 1,
  "fullName": "Arjun Kulkarni",
  "email": "admin@smartinv.com",
  "role": "ADMIN"
}
```

---

### 📊 Dashboard Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/dashboard/stats` | KPI cards data |
| GET | `/dashboard/activity` | Recent activity feed |
| GET | `/dashboard/chart/revenue` | Monthly revenue (12 months) |
| GET | `/dashboard/chart/orders` | Monthly orders (12 months) |
| GET | `/dashboard/inventory-health` | Category health bars |

**Stats Response:**
```json
{
  "totalProducts": 284,
  "totalRevenue": 2450000.00,
  "lowStockCount": 17,
  "totalInvoices": 1847,
  "totalCustomers": 287,
  "pendingInvoices": 37
}
```

---

### 📦 Product Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/products?search=&categoryId=&page=0&size=10` | Paginated product list |
| GET | `/products/{id}` | Single product |
| POST | `/products` | Create product |
| PUT | `/products/{id}` | Update product |
| DELETE | `/products/{id}` | Soft delete product |
| POST | `/products/{id}/image` | Upload product image |
| GET | `/products/low-stock` | All low-stock products |

**Create Product Request:**
```json
{
  "name": "Mechanical Keyboard MK-Pro",
  "description": "TKL mechanical keyboard with RGB lighting",
  "categoryId": 3,
  "supplierId": 1,
  "price": 4999.00,
  "costPrice": 3200.00,
  "stock": 50,
  "lowStockThreshold": 10
}
```

---

### 🏭 Inventory Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/inventory/logs?productId=&page=0&size=20` | Stock history |
| POST | `/inventory/stock-in` | Add stock |
| POST | `/inventory/stock-out` | Remove stock |
| PUT | `/inventory/adjust/{productId}` | Manual stock adjustment |

**Stock In Request:**
```json
{
  "productId": 5,
  "quantity": 100,
  "reason": "Restocking from supplier DigiParts India"
}
```

---

### 🧾 Invoice Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/invoices?search=&status=&page=0&size=10` | Paginated invoice list |
| GET | `/invoices/{id}` | Invoice details |
| POST | `/invoices` | Create invoice |
| PUT | `/invoices/{id}/status` | Update status |
| DELETE | `/invoices/{id}` | Cancel invoice |
| GET | `/invoices/{id}/pdf` | Download PDF |

**Create Invoice Request:**
```json
{
  "customerId": 3,
  "gstRate": 18.00,
  "notes": "Bulk order — Q2 2026",
  "items": [
    { "productId": 1, "quantity": 2, "unitPrice": 4999.00 },
    { "productId": 7, "quantity": 5, "unitPrice": 1299.00 }
  ]
}
```

---

### 👥 Customer Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/customers?search=&page=0&size=10` | Paginated customer list |
| GET | `/customers/{id}` | Customer details |
| GET | `/customers/{id}/invoices` | Customer billing history |
| POST | `/customers` | Create customer |
| PUT | `/customers/{id}` | Update customer |
| DELETE | `/customers/{id}` | Soft delete customer |

---

### 🚛 Supplier Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/suppliers?search=&page=0&size=10` | Paginated supplier list |
| GET | `/suppliers/{id}` | Supplier details |
| POST | `/suppliers` | Create supplier |
| PUT | `/suppliers/{id}` | Update supplier |
| DELETE | `/suppliers/{id}` | Soft delete supplier |

---

### 📈 Analytics Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/analytics/revenue?months=12` | Revenue trend data |
| GET | `/analytics/best-selling?limit=10` | Top selling products |
| GET | `/analytics/category-sales` | Sales by category |
| GET | `/analytics/export?type=revenue&format=csv` | Export reports |

---

### 🔔 Notification Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/notifications?page=0&size=20` | All notifications |
| GET | `/notifications/unread-count` | Unread count |
| PUT | `/notifications/{id}/read` | Mark as read |
| PUT | `/notifications/read-all` | Mark all as read |
| DELETE | `/notifications/{id}` | Delete notification |

---

## 🚀 Deployment

### Deploy Backend to a Linux Server

```bash
# Build JAR
cd backend
mvn clean package -DskipTests

# Transfer JAR
scp target/smart-inventory-1.0.0.jar user@your-server:/opt/smartinv/

# Run as a service
ssh user@your-server
java -jar /opt/smartinv/smart-inventory-1.0.0.jar \
  --spring.datasource.password=PROD_PASSWORD \
  --app.jwt.secret=PROD_LONG_SECRET_KEY
```

### Deploy Frontend to Netlify / Vercel

```bash
cd frontend
npm run build
# Drag and drop the 'dist' folder to Netlify
# Or: npx netlify deploy --prod --dir=dist
```

Update `.env` for production:
```env
VITE_API_BASE_URL=https://api.yourdomain.com/api
```

### Docker (Optional)

A `docker-compose.yml` can be added to containerize both services with MySQL. Contact the maintainer for the Docker setup.

---

## 🐛 Troubleshooting

### ❌ "Access Denied" or 403 errors
- Make sure you are sending the `Authorization: Bearer <token>` header
- Token may have expired — log in again
- Check that the user has the correct role for the endpoint

### ❌ MySQL connection refused
- Ensure MySQL service is running: `sudo service mysql start`
- Verify credentials in `application.properties`
- Check the database name matches exactly: `smart_inventory`

### ❌ CORS errors in browser
- The backend CORS config allows `http://localhost:5173` by default
- For production, update `SecurityConfig.java` to allow your frontend domain

### ❌ `npm run dev` — module not found
- Delete `node_modules` and reinstall: `rm -rf node_modules && npm install`

### ❌ Port 8080 already in use
```bash
# Find and kill the process
lsof -i :8080
kill -9 <PID>
```

### ❌ Image upload not working
- Create the `uploads/` directory in the backend root: `mkdir -p backend/uploads`
- Check write permissions: `chmod 755 uploads/`

---

## 🧑‍💻 Development Tips

- **Hot reload backend:** Use Spring DevTools — add the dependency to `pom.xml` and changes auto-restart
- **API testing:** Import the Postman collection from `docs/SmartInventory.postman_collection.json`
- **Logs:** Backend logs are in the console. Set `logging.level.com.smartinv=DEBUG` for verbose output
- **Database GUI:** Use TablePlus, DBeaver, or MySQL Workbench to inspect tables visually

---

## 📄 License

This project is for educational and internal business use. Not for redistribution without permission.

---


 
