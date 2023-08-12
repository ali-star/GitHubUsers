# 👥 GitHubUsers
![GitHubUsersUI](https://user-images.githubusercontent.com/5895322/159549828-b185c150-21d4-45ce-bfa2-f8b6b1b2c4fd.png)

Sample app for searching github users.

This application is using the latest tools and libraries with Hexagonal Architecture + MVI pattern.

## ⚒️ Tools

### 🧑🏻‍💻 Development
- Application entirely written in [Kotlin](https://kotlinlang.org)
- [Jetpack Compose](https://developer.android.com/jetpack/compose) for the ui
- Asynchronous processing using [Coroutines](https://kotlin.github.io/kotlinx.coroutines/)
- Dependency injection with [Hilt](https://dagger.dev/hilt/)
- [Retrofit](https://square.github.io/retrofit/) for the HTTP client
- Image loading with [Coil](https://coil-kt.github.io/coil/), it's written in Kotlin, uses coroutines, and supports Jetpack Compose.

### 🧪 Test
- [Robolectric](http://robolectric.org/) for faster ui tests
- [Mockk](https://mockk.io/) for mocking objects
- [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) for mocking apis

### 👌 Quality
- [klint](https://github.com/shyiko/ktlint)
- [detekt](https://github.com/arturbosch/detekt)

All used dependencies can be accessed in the [DependenciesFile](https://github.com/ali-star/GitHubUsers/blob/master/gradle/libs.versions.toml)

## 🏛 Architacture & Patterns
This project is based on the [Hexagonal Architecture](https://alistair.cockburn.us/hexagonal-architecture/) by Alistair Cockburn. The application also relies heavily on modularization for better separation of concerns and encapsulation.

### 🧩 Application modules

* **app** - The Application module. It contains all the initialization logic for the Android
  environment and starts the _Jetpack Navigation Compose Graph_.
* **features** - The module/folder containing all the features (visual or not) from the application
  * **search-api** - Search feature **port**.
  * **search** - Search screen of the application used for searching users the **adapter** for the search-api.
  * **user-detail-api** - User detail feature **port**.
  * **user-detail** - User detail screen to show user detailed info and the **adapter** for the user-detail-api.
* **domain** - The modules containing the most important part of the application: the business
  logic. This module depends only on itself and all interaction it does is via _dependency
  inversion_.
* **data** - The module containing the data (repository, remote) from the app and uses the _repository pattern_.
  * **repository** - The **port** for data layer.
  * **remote** - The remote **adapter** for the data layer.
* **libraries** - The module that contains libraries that may be used by any module of the app.
  * **core** - Includes common components, functions, and resources that may be needed to share between several modules.
  * **design** - Includes common components and resource for the UI.
  * **navigation** - Only containing the UI destinations and destination arguments.
  * **test** - The module that shares common components and functions for testing.

To better represents the idea behind the modules, here is an architecture image representing the flow
of dependency:


![GitHubUsersArchitecture](https://github.com/ali-star/GitHubUsers/assets/5895322/e521abd6-6644-48df-b2a2-188499029aa1)


### 👁️🏁 Presentation Pattern

This application uses MVI pattern for the presentation module, the key advantages of MVI is:
- Single source of truth - one immutable state common to all layers, as the only source of truth
- Unidirectional and cyclic data flow
- Easiness of catching and fixing bugs
- Easiness of code testability

### 🧪🏁 Testing Pattern

The project uses BDD (Behaviour Driven Development) and Robot pattern, cucumber library is not used and decided to go with a simpler approach.
Using BDD along with the Robot pattern makes test cases look simpler and more human-readable.
