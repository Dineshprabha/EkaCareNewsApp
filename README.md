# News App

This project demonstrates the use of modern Android development best practices, leveraging Jetpack Compose, MVVM architecture, and several powerful libraries. The project follows a modular structure with clean separation of concerns into `data`, `domain`, and `presentation` layers.

---

## Table of Contents

- [Libraries Used](#libraries-used)
- [Project Structure](#project-structure)
- [MVVM Architecture](#mvvm-architecture)
- [Setup Instructions](#setup-instructions)

---

## Libraries Used

### Retrofit
- **Dependency**:
  ```
  implementation(libs.retrofit)
  implementation(libs.converter.gson)
  ```
- **Purpose**: Used for making HTTP requests to RESTful APIs and handling JSON serialization/deserialization using Gson.

### Room
- **Dependency**:
  ```
  implementation(libs.androidx.room.runtime)
  kapt(libs.androidx.room.compiler)
  implementation(libs.androidx.room.ktx)
  ```
- **Purpose**: Provides an abstraction layer over SQLite to allow fluent database access while following the MVVM pattern.

### Coil
- **Dependency**:
  ```
  implementation(libs.coil.compose)
  ```
- **Purpose**: Image loading library optimized for Jetpack Compose. Efficiently loads images from the network or local resources.

### Accompanist
- **Dependency**:
  ```
  implementation(libs.accompanist.systemuicontroller)
  ```
- **Purpose**: Utility library for Compose. In this project, it is used for managing system UI components like the status bar and navigation bar colors.

### Compose Navigation
- **Dependency**:
  ```
  implementation(libs.androidx.navigation.compose)
  ```
- **Purpose**: Enables navigation between composable screens in a type-safe and declarative way.

### Splash API
- **Dependency**:
  ```
  implementation(libs.androidx.core.splashscreen)
  ```
- **Purpose**: Provides APIs to implement a splash screen that complies with modern Android guidelines.

### Dagger Hilt
- **Dependency**:
  ```
  implementation(libs.hilt.android)
  kapt(libs.hilt.compiler)
  implementation(libs.androidx.hilt.navigation.compose)
  ```
- **Purpose**: Simplifies dependency injection in Android applications. Integrated with ViewModels and Compose.

---

## Project Structure

The project follows the MVVM (Model-View-ViewModel) architecture with a clean separation of concerns. Here's an overview of the structure:

### `data` Layer
- Contains all the data-related logic.
- **Components**:
  - Repositories: Handle API and database operations.
  - Data Sources: Abstract APIs and local database interactions.
  - Models: Represent API responses and database entities.

### `domain` Layer
- Acts as a bridge between `data` and `presentation` layers.
- **Components**:
  - Use Cases: Encapsulate business logic and orchestrate data from repositories.

### `presentation` Layer
- Contains the UI and user interaction logic.
- **Components**:
  - Composables: Define the UI using Jetpack Compose.
  - ViewModels: Manage UI-related data and handle logic by interacting with the `domain` layer.

---

## MVVM Architecture

- **Model**: Responsible for data management (e.g., Room database, Retrofit API).
- **View**: Includes the composable functions that represent the UI.
- **ViewModel**: Acts as a mediator between the View and the Model. It retrieves data from the domain layer and provides it to the View.

---

## Setup Instructions

### Prerequisites
Ensure you have the following installed:
- Android Studio Flamingo or later
- Kotlin 1.8+
- Gradle 8.0+

### Steps to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/your-repository.git
   ```
2. Open the project in Android Studio.
3. Sync the project to download dependencies.
4. Build and run the project on an emulator or a physical device.

---

## Features
- Modern Android UI using Jetpack Compose.
- Persistent data storage with Room.
- Seamless API integration with Retrofit.
- Efficient image loading using Coil.
- Dependency Injection with Dagger Hilt.
- Splash screen implementation compliant with Android guidelines.
- Navigation using Jetpack Compose Navigation.

---

## Contribution
Contributions are welcome! Please open an issue or submit a pull request for any improvements.

---

## License
This project is licensed under the MIT License. See the LICENSE file for more details.

