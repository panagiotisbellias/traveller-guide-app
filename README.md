# traveller-guide-app
Service that produces city recommandations based on the preferences and criterias of a traveller and uses open data. Developed in the context of semester assignment for "Object-oriented Programming II' lesson and studies program of Department of Informatics and Telematics (DIT) at Harokopio University of Athens, Greece (HUA)

## Build and Run Instructions

This project is a Java desktop application. It can be built and run with **Maven**, and you can still use **IntelliJ IDEA**.

---

### 💡 IntelliJ IDEA

1. Open IntelliJ IDEA and choose **Open Project**, selecting the `traveller-guide-app` folder.
2. Mark `src/main/java` as **Sources Root** if not already marked (Right-click → `Mark Directory as → Sources Root`).
3. Maven dependencies will be automatically detected.
4. Create a Run Configuration with `com.bellias.travellerguide.TravellerGuide` as **Main class** (replace with your actual main class if different).
5. Run the application.

---

### 💻 Command Line (Windows / Linux / WSL)
Requirements: JDK installed and `JAVA_HOME` set.

1. Open a terminal in the project root.
2. Build the project with Maven:
   ```bash
   mvn clean package
   mvn exec:java -Dexec.mainClass="com.bellias.travellerguide.TravellerGuide"
   ```

---

## 📸 Application Screenshots

Here are some screenshots of the Traveller Guide App in action:

| Initial View                                           | Initial View (Filled)                                                | Large View (Filled)                                              |
|--------------------------------------------------------|----------------------------------------------------------------------|------------------------------------------------------------------|
| ![Initial View](docs/screenshots/app-initial-view.png) | ![Initial View Filled](docs/screenshots/app-initial-view-filled.png) | ![Large View Filled](docs/screenshots/app-large-view-filled.png) |

| Help Window                                          | Error Message                                           | Error Modal Popup                                                |
|------------------------------------------------------|---------------------------------------------------------|------------------------------------------------------------------|
| ![Help Window](docs/screenshots/app-help-window.png) | ![Error Message](docs/screenshots/app-error-mesage.png) | ![Error Modal Popup](docs/screenshots/app-error-modal-popup.png) |
