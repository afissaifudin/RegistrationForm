# RegistrationForm

The App uses a set of Android Jetpack libraries and data from REST API [GoAPI](https://goapi.id/)

## Architecture

The project uses MVVM architecture pattern.

## Tech Stack & Libraries
* Minimum SDK level 24
* Setup Project with Kotlin DSL
* [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://kotlinlang.org/docs/coroutines-guide.html) for asynchronous
* [Dagger-Hilt](https://dagger.dev/hilt/) - For Dependency Injection
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel/) - Manage UI
  related data in a lifecycle conscious way and act as a channel between use cases and ui
* [DataBinding](https://developer.android.com/topic/libraries/data-binding) - support library that allows binding of UI components in layouts to data sources,binds character details and search results to UI
* [Kotlin Flow](https://developer.android.com/kotlin/flow) - To access data sequentially
* [Retrofit](https://square.github.io/retrofit/) - To access the Rest Api
* [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started) - Android Jetpack's Navigation component helps in implementing navigation between fragments
* [MockK](https://mockk.io/) - To mocking library for Kotlin.

## Common Module
The common module provides essential classes, extensions, constants, and shared components for the app module. It ensures consistency and reusability across the application, simplifying maintenance and feature expansion.

## Custom View
CustomInput is a component created to efficiently manage all fields within this application. This versatile component simplifies the handling of various input fields, enhancing the overall usability and functionality of the application.

## Preview Navigation
<img src="https://github.com/afissaifudin/RegistrationForm/tree/master/preview/navigation.png"/>

## Preview Apps
![Welcome Page](https://github.com/afissaifudin/RegistrationForm/tree/master/preview/welcome.png) ![Personal Page](https://github.com/afissaifudin/RegistrationForm/tree/master/preview/personal.png) ![Residence Page](https://github.com/afissaifudin/RegistrationForm/tree/master/preview/residence.png) ![Review Page](https://github.com/afissaifudin/RegistrationForm/tree/master/preview/review.png) ![Success Page](https://github.com/afissaifudin/RegistrationForm/tree/master/preview/success.png)