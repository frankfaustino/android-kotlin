# GuessTheWord

This app is a two player game, GuessTheWord. It is a word guessing app you can play with one or more friends. To play, hold the device in landscape, facing away from you with your thumbs on the **Skip** and **Got It** buttons. Your friends can then give you clues to help you guess the word. If you get the word right, press **Got It**. If you're stuck, press **Skip**.
You will modify the app to use Architecture components and best practices.

## Content
- [Summary](#summary)
- [ViewModel](#viewmodel)
- [ViewModelFactory](#viewmodelfactory)
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

## Resources

- [`ViewModel` Overview](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [Handling Lifecycles with Lifecycle-Aware Components](https://developer.android.com/topic/libraries/architecture/lifecycle)
- [Guide to app architecture](https://developer.android.com/jetpack/docs/guide)
- [`ViewModelProvider`](https://developer.android.com/reference/android/arch/lifecycle/ViewModelProvider)
- [`ViewModelProvider.Factory`](https://developer.android.com/reference/android/arch/lifecycle/ViewModelProvider.Factory)

- [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) (model-view-viewmodel) architectural pattern.
- [Separation of concerns](https://en.wikipedia.org/wiki/Separation_of_concerns) (SoC) design principle
- [Factory method pattern](https://en.wikipedia.org/wiki/Factory_method_pattern)