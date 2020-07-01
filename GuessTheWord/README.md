# GuessTheWord

This app is a two player game, GuessTheWord. It is a word guessing app you can play with one or more friends. To play, hold the device in landscape, facing away from you with your thumbs on the **Skip** and **Got It** buttons. Your friends can then give you clues to help you guess the word. If you get the word right, press **Got It**. If you're stuck, press **Skip**.
You will modify the app to use Architecture components and best practices.

## Content
- [Summary](#summary)
- [ViewModel](#viewmodel)
- [ViewModelFactory](#viewmodelfactory)
- [LiveData](#livedata)
- [Observer Pattern](#observer-pattern)
- [ViewModel Data Binding](#viewmodel-data-binding)
  - [Listener bindings](#listener-bindings)
- [Adding LiveData to data binding](#adding-livedata-to-data-binding)
- [Transforming `LiveData`](#transforming-livedata)
- [Displaying the result of a transformation in a `TextView`](#displaying-the-result-of-a-transformation-in-a-textview)
- [Resources](#resources)

## Summary

- The Android [app architecture](https://developer.android.com/jetpack/guide) guidelines recommend separating classes that have different responsibilities.
- A *UI controller* is a UI-based class like `Activity` or `Fragment`. UI controllers should only contain logic that handles UI and OS interactions; they shouldn't contain data to be displayed in the UI (put that data in a `ViewModel`).
The [`ViewModel`](https://developer.android.com/reference/android/arch/lifecycle/ViewModel.html) is one of the recommended [Android Architecture Components](https://developer.android.com/jetpack/#architecture-components).

![d115344705100cf1.png](d115344705100cf1.png)

Table below compares UI controllers with the `ViewModel`:

| UI controller | ViewModel |
|---------------|-----------|
| Doesn't contain any data to be displayed in the UI | Contains data that the UI controller displays in the UI |
| Contains code for displaying data, and user-event code such as click listeners | Contains code for data processing |
| Destroyed and re-created during every configuration change | Destroyed only when the associated UI controller goes away permanently—for an activity, when the activity finishes, or for a fragment, when the fragment is detached |
| Contains views | Should never contain references to activities, fragments, or views, because they don't survive configuration changes, but the `ViewModel` does |
| Contains a reference to the associated `ViewModel` | Doesn't contain any reference to the associated UI controller |

## ViewModel

The `ViewModel` class stores and manages UI-related data. It allows data to survive configuration changes such as screen rotations.

## ViewModelFactory

`ViewModelProvider.Factory` is an interface used to create a `ViewModel` object.

## LiveData

`LiveData` lets you build data objects that notify views when the underlying database changes.

The use the `LiveData` class, set up "observers" (e.g., activities or fragments) that observe changes in the app's data. `LiveData` is lifecycle-aware, so it only updates app-component observers that are in an active lifecycle state.

- `LiveData` is observable, which means that an [`Observer`](https://developer.android.com/reference/android/arch/lifecycle/Observer.html) is notified when the data held by the `LiveData` object changes
- `LiveData` holds data; `LiveData` is a wrapper that can be used with any data
- `LiveData` is lifecycle-aware. When you attach an observer to the `LiveData`, the observer is associated with a [`LifecycleOwner`](https://developer.android.com/topic/libraries/architecture/lifecycle#lco) (usually an activity or fragment). The `LiveData` only updates observers that are in an active lifecycle state such as [`STARTED`](https://developer.android.com/reference/android/arch/lifecycle/Lifecycle.State.html#STARTED) or [`RESUMED`](https://developer.android.com/reference/android/arch/lifecycle/Lifecycle.State.html#RESUMED).

```kotlin
val viewModel: GameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)
viewModel.score.observe(viewLifecycleOwner, Observer { newScore -> binding.scoreText.text = newScore.toString() })
viewModel.word.observe(viewLifecycleOwner, Observer { newWord -> binding.wordText.text = newWord.toString() })
```

## Observer pattern

The *observer pattern* specifies communication between objects: an *observable* (the "subject" of observation) and *observers*. An observable is an object that notifies observers about the changes in its state.
![b608df5e5e5fa4f8.png](b608df5e5e5fa4f8.png)

## ViewModel Data Binding

- You can associate a `ViewModel` with a layout by using data binding
- `ViewModel` objects hold the UI data. By passing `ViewModel` objects into the data binding, you can automate some of the communication between the views and the `ViewModel` objects.

- In the layout file, add a data binding variable of the type `ViewModel`
```xml
<data>
    <variable
        name="gameViewModel"
        type="com.example.android.guesstheword.screens.game.GameViewModel" />
</data>
```

- In the `GameFragment` file, pass the `GameViewModel` into the data binding
```kotlin
binding.gameViewModel = viewModel
```

### Listener bindings

- [*Listener bindings*](https://developer.android.com/topic/libraries/data-binding/expressions#listener_bindings) are binding expressions in the layout that run when click events such as `onClick()` are triggered
- Listener bindings are written as lambda expressions
- Replace the click listeners in the UI controllers with listener bindings in the layout file
- Data binding creates a listener and sets the listener on the view

```xml
<Button android:onClick="@{() -> gameViewModel.onSkip()}" />
```

## Adding LiveData to data binding

- `LiveData` objects can be used as a data binding source to automatically notify the UI about changes in the data
- You can bind the view directly to the `LiveData` object in the `ViewModel`. When the `LiveData` in the `ViewModel` changes, the views in the layout can be automatically update, without the observer methods in the UI controllers

```xml
<TextView android:text="@{gameViewModel.word}" />
```

- To make the `LiveData` data binding work, set the current activity as the lifecycle owner of the `binding` variable in the UI controller

```kotlin
binding.lifecycleOwner = viewLifecycleOwner
```

## Transforming `LiveData`

- Sometimes you want to transform the results of `LiveData`. E.g., formatting a `Date` string as "hours:mins:secs," or return the number of items in a list rather than the list itself.
- Use the helper methods in the [`Transformations`](https://developer.android.com/reference/androidx/lifecycle/Transformations.html) class to perform transformations on `LiveData`
- The [`Transformations.map()`](https://developer.android.com/reference/androidx/lifecycle/Transformations.html#map(androidx.lifecycle.LiveData%3CX%3E,%20androidx.arch.core.util.Function%3CX,%20Y%3E)) method provides an easy way to perform data manipulations on the `LiveData` and return another `LiveData` object. The recommended practice is to put data-formatting logic that uses the `Transformations` class in the `ViewModel` along with the UI data.

### Displaying the result of a transformation in a `TextView`

- Make sure the source data is defined as `LiveData` in the `ViewModel`
- Define a variable, e.g. `newResult`. Use the `Transformations.map()` to perform the transformation and return the result to the variable

```kotlin
val newResult = Transformations.map(someLiveData) { input ->
    // Do some transformation on the input live data
    // and return the new value
}
```

- Make sure the layout file that contains the `TextView` declares a `<data>` variable for the `ViewModel`. E.g.:
```xml
<TextView android:text="@{SomeViewModel.newResult}" />
```

## Resources

- [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) (model-view-viewmodel) architectural pattern.
- [Separation of concerns](https://en.wikipedia.org/wiki/Separation_of_concerns) (SoC) design principle
- [Factory method pattern](https://en.wikipedia.org/wiki/Factory_method_pattern)

- [`ViewModel` Overview](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [Handling Lifecycles with Lifecycle-Aware Components](https://developer.android.com/topic/libraries/architecture/lifecycle)
- [Guide to app architecture](https://developer.android.com/jetpack/docs/guide)
- [`ViewModelProvider`](https://developer.android.com/reference/android/arch/lifecycle/ViewModelProvider)
- [`ViewModelProvider.Factory`](https://developer.android.com/reference/android/arch/lifecycle/ViewModelProvider.Factory)

- [`LiveData` Overview](https://developer.android.com/topic/libraries/architecture/livedata)
- [`MutableLiveData`](https://developer.android.com/reference/android/arch/lifecycle/MutableLiveData)

- [Bind layout views to Architecture Components](https://developer.android.com/topic/libraries/data-binding/architecture)
- [Listener bindings](https://developer.android.com/topic/libraries/data-binding/expressions#listener_bindings)

- [`Transformations`](https://developer.android.com/reference/androidx/lifecycle/Transformations.html) class
- [`Transformations.map()`](https://developer.android.com/reference/androidx/lifecycle/Transformations.html#map(androidx.lifecycle.LiveData%3CX%3E,%20androidx.arch.core.util.Function%3CX,%20Y%3E)) method
- [`DateUtils.formatElapsedTime()`](https://developer.android.com/reference/android/text/format/DateUtils.html#formatElapsedTime(long))
- [`CountDownTimer`](https://developer.android.com/reference/android/os/CountDownTimer)