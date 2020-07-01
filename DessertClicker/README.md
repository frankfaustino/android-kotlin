# DessertClicker

DessertClicker is a game about making desserts. Press the button, make a dessert, earn the big bucks.

This app was made to explore the Android lifecycle and logging messages to the Android console (Logcat).

## Content
- [Lifecycle](#lifecycle)
  - [Activity Lifecycle](#activity-lifecycle)
  - [Fragment Lifecycle](#fragment-lifecycle)
  - [onSaveInstanceState()](#saving-transient-ui-state)
  - [Lifecycle Library](#lifecycle-library)
  - [Lifecycle Resources](#lifecycle-resources)

## Lifecycle

Every activity and fragment has a **lifecycle** which is made of the different states that an activity can go through, from when the activity is first initialized to when it is finally destroyed and its memory reclaimed by the system.

![activity_lifecycle.png](activity_lifecycle.png)

### Activity Lifecycle

The `Activity` class, and any subclasses of `Activity` such as `AppCompatActivity`, implements a set of lifecycle callback methods which enables you to run code when an activity lifecycle state changes. You can override these methods to perform tasks in response to those lifecycle state changes.

[`onCreate()`](https://developer.android.com/reference/android/app/Activity.html#onCreate(android.os.Bundle)) - Called only once when the activity is created.
[`onStart()`](https://developer.android.com/reference/android/app/Activity.html#onStart()) — Called after `onCreate()` or after `onRestart()` when the activity had been stopped.
[`onResume()`](https://developer.android.com/reference/android/app/Activity.html#onResume()) - Called when the activity has focus.
[`onPause()`](https://developer.android.com/reference/android/app/Activity.html#onPause()) - Called when activity no longer has focus.
[`onStop()`](https://developer.android.com/reference/android/app/Activity.html#onStop()) - Called when activity is no longer visible on the screen (`Activity` object is still in memory, in the *background*).
    - Not called if the app is partially visible on screen.
[`onRestart()`](https://developer.android.com/reference/android/app/Activity.html#onRestart()) — Called after `onStop()` when the current activity is being re-displayed.
[`onDestroy()`](https://developer.android.com/reference/android/app/Activity.html#onDestroy()) - Called only once when activity is fully shut down and can be garbage collected.

![f6b25a71cec4e401.png](f6b25a71cec4e401.png)

### Fragment Lifecycle

A fragment's lifecycle is similar to an activity's lifecycle:

`onAttach()` — Called when the fragment is associated with its owner activity.
`onCreate()` — Similarly to `onCreate()` for the activity, `onCreate()` for the fragment is called to do initial fragment creation (other than layout).
`onCreateView()` — Called to inflate the fragment's layout.
`onActivityCreated()` — Called when the owner activity's `onCreate()` is complete. Your fragment will not be able to access the activity until this method is called.
`onStart()` — Called when the fragment becomes visible; parallel to the activity's `onStart()`.
`onResume()` — Called when the fragment gains the user focus; parallel to the activity's `onResume()`.

`onPause()` — Called when the fragment loses the user focus; parallel to the activity's `onPause()`.
`onStop()` — Called when the fragment is no longer visible on screen; parallel to the activity's `onStop()`.
`onDestroyView()` — Called when the fragment's view is no longer needed, to clean up the resources associated with that view.

![dfde69e6a42d54b3.png](dfde69e6a42d54b3.png)

### Saving transient UI state

The system may destroy the app's activity during a configuration change, such as rotation or switching into multi-window mode. When this occurs, the system remembers that it existed and creates a new instance of that activity using a set of saved data that describes the state of the activity when it was destroyed. This saved data is called *instance state* and is a collection of key-value pairs stored in a [**Bundle**](https://developer.android.com/reference/android/os/Bundle) object.

### Lifecycle library

- Use the Android lifecycle library to shift lifecycle control from the activity or fragment to the actual component that needs to be lifecycle-aware.
- Lifecycle *owners* are components that have (and thus "own") lifecycles, including `Activity` and `Fragment`. Lifecycle owners implement the `LifecycleOwner` interface.
- Lifecycle *observers* pay attention to the current lifecycle state and perform tasks when the lifecycle changes. Lifecycle observers implement the `LifecycleObserver` interface.
- `Lifecycle` objects contain the actual lifecycle states, and they trigger events when the lifecycle changes.

To create a lifecycle-aware class:

- Implement the `LifecycleObserver` interface in classes that need to be lifecycle-aware.
- Initialize a lifecycle observer class with the lifecycle object from the activity or fragment.
- In the lifecycle observer class, annotate lifecycle-aware methods with the lifecycle state change they are interested in.
  - For example, the `@OnLifecycleEvent(Lifecycle.Event.ON_START)` annotation indicates that the method is watching the `onStart` lifecycle event.

### Lifecycle resources

- [Activities](http://developer.android.com/guide/components/activities.html) (API guide)
- [Fragments](https://developer.android.com/guide/components/fragments) (API guide)
- [`Activity`](http://developer.android.com/reference/kotlin/android/app/Activity.html) (API reference)
- [`Fragment`](https://developer.android.com/reference/kotlin/android/app/Fragment#Lifecycle) (API reference)
- [Understand the Activity Lifecycle](https://developer.android.com/guide/components/activities/activity-lifecycle)
- [Handling Lifecycles with Lifecycle-Aware Components](https://developer.android.com/topic/libraries/architecture/lifecycle)
- [`LifecycleOwner`](https://developer.android.com/reference/android/arch/lifecycle/LifecycleOwner.html)
- [`Lifecycle`](https://developer.android.com/reference/android/arch/lifecycle/Lifecycle.html)
- [`LifecycleObserver`](https://developer.android.com/reference/android/arch/lifecycle/LifecycleObserver.html)
- [`onSaveInstanceState()`](https://developer.android.com/reference/android/app/Activity#onSaveInstanceState(android.os.Bundle))
- [Handle configuration changes](https://developer.android.com/guide/topics/resources/runtime-changes)
- [Saving UI States](https://developer.android.com/topic/libraries/architecture/saving-states.html)

- [The Android Lifecycle cheat sheet — part I: Single Activities](https://medium.com/google-developers/the-android-lifecycle-cheat-sheet-part-i-single-activities-e49fd3d202ab) is a visual recap of much of the material here.
- [The Android Lifecycle cheat sheet — part II: Multiple Activities](https://medium.com/@JoseAlcerreca/the-android-lifecycle-cheat-sheet-part-ii-multiple-activities-a411fd139f24) shows the order of lifecycle calls when two activities interact.
- [The Android Lifecycle cheat sheet — part III: Fragments](https://medium.com/@JoseAlcerreca/the-android-lifecycle-cheat-sheet-part-iii-fragments-afc87d4f37fd) shows the order of lifecycle calls when an activity and fragment interact.
