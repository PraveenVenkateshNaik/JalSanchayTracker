# Jal-Sanchay Tracker 💧

## Smart Rainwater Harvesting & Water Conservation Android Application

Jal-Sanchay Tracker is a modern Android application developed to help households monitor and manage rainwater harvesting effectively. The application calculates rainwater collection, visualizes tank storage, tracks historical water savings, and promotes sustainable water usage through an interactive futuristic dashboard.

The project is built using **Kotlin**, **Jetpack Compose**, **Room Database**, and **MVVM Architecture** with a fully offline-first approach.

---

# 📌 Features

## 🌧 Rainwater Harvesting Calculator

* Calculates harvested rainwater using rooftop area and rainfall data.
* Uses runoff coefficient for realistic estimation.

## 💧 Animated Water Tank

* Dynamic water tank fill animation.
* Shows real-time tank storage percentage.

## 📊 Dashboard Analytics

* Total water saved
* Tank fill percentage
* Household water days
* Sustainability score
* Monthly statistics

## 📈 Reports & Charts

* Weekly reports
* Monthly reports
* Yearly reports
* Bar charts
* Pie charts
* Line graphs

## 🗂 Local Data Storage

* Offline-first application.
* Uses Room Database for storing:

  * rainfall entries,
  * water savings,
  * reports,
  * user data.

## 🌱 Water Conservation Tips

* Rainwater harvesting awareness
* Gardening tips
* Sustainable water usage guidance

## 🌙 Dark Mode Support

* Light and dark themes
* Smooth UI transitions

## ✨ Advanced UI/UX

* Futuristic modern design
* Glassmorphism effects
* Smooth animations
* Water ripple effects
* Material 3 UI

---

# 🛠 Technologies Used

## Frontend

* Kotlin
* Jetpack Compose
* Material 3
* Compose Navigation
* Lottie Animations

## Backend

* Room Database
* SQLite
* MVVM Architecture
* Repository Pattern

## Other Libraries

* Hilt Dependency Injection
* Coroutines
* StateFlow
* DataStore Preferences
* Compose Charts / MPAndroidChart

---

# 🧠 Formula Used

The application calculates rainwater harvested using:

\text{Water Saved} = \text{Area} \times \text{Rainfall} \times 0.0929 \times \text{Runoff Coefficient}

Where:

* Area = Rooftop area
* Rainfall = Rainfall in millimeters
* 0.0929 = Unit conversion factor
* Runoff Coefficient = Water collection efficiency

---

# 🏗 Architecture

The project follows **MVVM Architecture**:

* **Model** → Database & business logic
* **View** → UI screens
* **ViewModel** → Connects UI and data layer

Benefits:

* Clean code
* Scalability
* Better maintainability
* Easier debugging

---

# 📱 Application Screens

1. Splash Screen
2. Onboarding Screen
3. Login Screen
4. Home Dashboard
5. Data Entry Screen
6. History Screen
7. Reports Screen
8. Water Saving Tips
9. Profile Screen

---

# 🗄 Database Structure

## RainfallEntry Table

* id
* date
* roofArea
* rainfall
* runoffCoefficient
* litersSaved
* tankCapacity
* tankLevel

## UserEntity Table

* id
* username
* password
* totalSavings
* ecoScore

---

# 🚀 How the Project Works

1. User enters:

   * roof area,
   * rainfall,
   * runoff coefficient,
   * and tank capacity.

2. The application calculates harvested rainwater.

3. Water level is visualized using an animated water tank.

4. All data is stored locally using Room Database.

5. Reports and analytics are generated dynamically.

6. Users can track water conservation over time.

---

# 📦 Installation Steps

## Prerequisites

* Android Studio Hedgehog or newer
* Kotlin support enabled
* Minimum SDK: 24

---

## Clone the Repository

```bash
git clone https://github.com/your-username/Jal-Sanchay-Tracker.git
```

---

## Open Project

1. Open Android Studio
2. Click “Open Project”
3. Select project folder

---

## Build the Project

* Sync Gradle
* Run the application on emulator or physical device

---

# 📂 Project Structure

```text
app/
 ├── data/
 ├── database/
 ├── repository/
 ├── ui/
 ├── screens/
 ├── components/
 ├── navigation/
 ├── viewmodel/
 ├── utils/
 └── theme/
```

---

# 🎯 Advantages

* Encourages water conservation
* Works offline
* Easy to use
* Modern user interface
* Real-time visual analytics
* Supports sustainability awareness

---

# 🔮 Future Enhancements

* Weather API integration
* AI-based rainfall prediction
* IoT sensor connectivity
* Cloud backup
* Smart notifications

---

# 👨‍💻 Developed By

Praveen Naik

---

# 📜 License

This project is developed for educational and sustainability purposes.
