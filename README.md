# traveller-guide-app
Service that produces city recommandations based on the preferences and criterias of a traveller and uses open data. Developed in the context of semester assignment for "Object-oriented Programming II' lesson and studies program of Department of Informatics and Telematics (DIT) at Harokopio University of Athens, Greece (HUA)

## Build and Run Instructions

This project is a Java desktop application. It was originally created with **NetBeans**, but you can also build and run it with **IntelliJ IDEA** or directly from the command line.

---

### ✅ NetBeans

1. Open NetBeans.
2. Go to **File → Open Project…** and select the `traveller-guide-app` folder.
3. Right-click the project in the Projects view and choose **Run**.
   - NetBeans uses the included `nbproject` and `build.xml`.

---

### 💡 IntelliJ IDEA
1. Open IntelliJ IDEA and choose **Open Project**, selecting the `traveller-guide-app` folder.
2. Mark `src/main/java` as **Sources Root** if not already marked (Right-click → `Mark Directory as → Sources Root`).
3. Add the `libs/` directory to the classpath:
   - `File → Project Structure → Libraries → + → Java → select all jars in libs/`.
4. Create a Run Configuration with `traveller.guide.TravellerGuide` as **Main class**
5. Run the application.

---

### 💻 Command Line (Windows)
Requirements: JDK installed and `JAVA_HOME` set.

1. Open **Command Prompt** in the project root.
2. Run:
   ```bat
   ant clean compile jar
   java -cp dist/Traveller_Guide_App.jar;libs/* traveller.guide.TravellerGuide
   ```

---

### 💻 Command Line (Linux / WSL / Ubuntu)
Requirements: JDK and Ant installed.

1. Install Ant if needed:
   ```bash
   sudo apt update
   sudo apt install ant
   ```
2. From the project root, run:
   ```bash
   ant clean compile jar
   java -cp "dist/Traveller_Guide_App.jar:libs/*" traveller.guide.TravellerGuide
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
