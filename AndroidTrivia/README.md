# AndroidTrivia

The AndroidTrivia app asks the user trivia questions about Android development.
It makes use of the navigation component within Jetpack to move the user between
screens. Each screen is implemented as a fragment.

The app navigates using buttons, the app bar, and a navigation drawer.

***

## Contents

 - [Fragments](#fragments)
   - [Fragment Lifecycle](#fragment-lifecycle)
 - [Navigation](#navigation-components)
   - [Back Button](#the-back-button)
   - [Up Button](#the-up-button)
   - [Options Menu](#the-options-menu)
   - [Navigation Drawer](#the-navigation-drawer)
   - [Resources](#navigation-resources)

## Fragments

A fragment represents a behavior or a portion of UI in an activity. You can combine multiple fragments in a single activity to build a multi-panel UI, and you can reuse a fragment in multiple activities.

- A fragment is a modular section of an activity.
- A fragment has its own lifecycle and receives its own input events.
- Use the `<fragment>` tag to define the layout for the fragment in the XML layout file.
- Inflate the layout for a fragment in `onCreateView()`.
- You can add or remove a fragment while the activity is running.

### Fragment Lifecycle

- To-do ⚠️

## Navigation components
To use the Android navigation library, you need to do some setup:

- Add dependencies for `navigation-fragment-ktx` and `navigation-ui-ktx` in the module-level `build.gradle` file.
- Add an `ext` variable for the `navigationVersion` in the project-level `build.gradle` file.

*Navigation destinations* are fragments, activities, or other app components that the user navigates to. A *navigation graph* defines the possible paths from one navigation destination to the next.

- To create a navigation graph, create a new Android resource file of type **Navigation**. This file defines the navigation flow through the app. The file is in the `res/navigation` folder, and it's typically called `navigation.xml`.
- To see the navigation graph in the Navigation Editor, open the `navigation.xml` file and click the **Design** tab.
- Use the Navigation Editor to add destinations such as fragments to the navigation graph.
- To define the path from one destination to another, use the Navigation Graph to create an action that connects the destinations. In the `navigation.xml` file, each of these connections is represented as an `action` that has an `ID`.

A *navigation host* fragment, usually named `NavHostFragment`, acts as a host for the fragments in the navigation graph:

- As the user moves between destinations defined in the navigation graph, the `NavHostFragment` swaps the fragments in and out and manages the fragment back stack.
- In the `activity_main.xml` layout file, the `NavHostFragment` is represented by a `fragment` element with the name `android:name="androidx.navigation.fragment.NavHostFragment"`.

To define which fragment is displayed when the user taps a view (for example a button), set the `onClick` listener for the view:

- In the `onClick` listener, call `findNavController().navigate()` on the view.
- Specify the `ID` of the `action` that leads to the destination.

*Conditional navigation* navigates to one screen in one case, and to a different screen in another case. To create conditional navigation:

1. Use the Navigation Editor to create a connection from the starting fragment to each of the possible destination fragments.
2. Give each connection a unique ID.
3. In the click-listener method for the `View`, add code to detect the conditions. Then call `findNavController().navigate()` on the view, passing in the `ID` for the appropriate action.

### The Back button
The system's *Back button* is usually at the bottom of the device. By default, the Back button navigates the user back to the screen they viewed most recently. In some situations, you can control where the Back button takes the user:

- In the Navigation Editor, you can use the **Attributes** pane to change an action's **Pop To** setting. This setting removes destinations from the back stack, which has the effect of determining where the Back button takes the user.
- The **Pop To** setting appears as the `popUpTo` attribute in the `navigation.xml` file.
- Selecting the **Inclusive** checkbox sets the `popUpToInclusive` attribute to `true`. All destinations up to and *including* this destination are removed from the back stack.
- If an action's `popUpTo` attribute is set to the app's the starting destination and `popUpToInclusive` is set to `true`, the Back button takes the user all the way out of the app.

### The Up button
Screens in an Android app can have an on-screen *Up button* that appears at the top left of the [app bar](https://developer.android.com/topic/libraries/architecture/navigation/navigation-ui#top_app_bar). (The app bar is sometimes called the action bar.) The Up button navigates "upwards" within the app's screens, based on the hierarchical relationships between screens.

The navigation controller's `NavigationUI` library integrates with the app bar to allow the user to tap the Up button on the app bar to get back to the app's home screen from anywhere in the app.

To link the navigation controller to the app bar:

1. In `onCreate()`, call `setupActionBarWithNavController()` on the `NavigationUI` class, passing in the navigation controller:
```kotlin
val navController = this.findNavController(R.id.myNavHostFragment)
NavigationUI.setupActionBarWithNavController(this,navController)
```
2. Override the `onSupportNavigateUp()` method to call `navigateUp()` in the navigation controller:
```kotlin
override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }
}
```
### The options menu
The *options menu* is a menu that the user accesses from the app bar by tapping the icon with the three vertical dots . To create an options menu with a menu item that displays a fragment, make sure the fragment has an ID. Then define the options menu and code the `onOptionsItemSelected()` handler for the menu items.

1. Make sure the fragment has an ID:

- Add the destination fragment to the navigation graph and note the ID of the fragment. (You can change the ID if you like.)

2. Define the options menu:

- Create an Android resource file of type `Menu`, typically named `options_menu.xml`. The file is stored in the **Res** > **Menu** folder.
- Open the `options_menu.xml` file in the design editor and drag a **Menu Item** widget from the **Palette** pane to the menu.
- For convenience, make the ID of the menu item the same as the ID of the fragment to display when the user clicks this menu item. This step is not required, but it makes it easier to code the `onClick` behavior for the menu item.

3. Code the `onClick` handler for the menu item:

- In the fragment or activity that displays the options menu, in `onCreateView()`, call `setHasOptionsMenu(true)` to enable the options menu.
- Implement `onCreateOptionsMenu()` to inflate the options menu:
```kotlin
override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
   super.onCreateOptionsMenu(menu, inflater)
   inflater?.inflate(R.menu.options_menu, menu)
}
```
- Override the `onOptionsItemSelected()` method to take the appropriate action when the menu item is clicked. The following code displays the fragment that has the same ID as the menu item. (This code only works if the menu item and the fragment have identical ID values.)
```kotlin
override fun onOptionsItemSelected(item: MenuItem?): Boolean {
   return NavigationUI.onNavDestinationSelected(item!!,
           view!!.findNavController())
           || super.onOptionsItemSelected(item)
}
```

### The navigation drawer
The **navigation drawer** is a panel that slides out from the edge of the screen. There are two ways for the user to open the navigation drawer:

- Swipe from the starting edge (usually the left) on any screen.
- Use the drawer button (three lines)  on the app bar at the top of the app.

To add a navigation drawer to your app:

1. Add dependencies to `build.gradle (app)`.
2. Make sure each destination fragment has an ID.
3. Create the menu for the drawer.
4. Add the drawer to the layout for the fragment.
5. Connect the drawer to the navigation controller.
6. Set up the drawer button in the app bar.

These steps are explained in more detail below.

1. Add dependencies to `build.gradle`:

- The navigation drawer is part of the Material Components for Android library. Add the Material library to the `build.gradle (app)` file:
```kotlin
dependencies {
    ...
    implementation "com.google.android.material:material:$supportlibVersion"
    ...
}
```
2. Give each destination fragment an ID:
- If a fragment is reachable from the navigation drawer, open it in the navigation graph to make sure that it has an ID.
3. Create the menu for the drawer:
- Create an Android resource file of type **Menu** (typically called `navdrawer_menu`) for a navigation drawer menu. This creates a new `navdrawer_menu.xml` file in the **Res** > **Menu** folder.
- In the design editor, add **Menu Item** widgets to the **Menu**.
4. Add the drawer to the layout for the fragment:
- In the layout that contains the navigation host fragment (which is typically the main layout), use `<androidx.drawerlayout.widget.DrawerLayout>` as the root view.
- Add a `<com.google.android.material.navigation.NavigationView>` view to the layout.
5. Connect the drawer to the navigation controller:
- Open the activity that creates the navigation controller. (The main activity is typically the one you want here.) In `onCreate()`, use `NavigationUI.setupWithNavController()` to connect the navigation drawer with the navigation controller:
```kotlin
val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
       this, R.layout.activity_main)
NavigationUI.setupWithNavController(binding.navView, navController)
```
6. Set up the drawer button in the app bar:
- In `onCreate()` in the activity that creates the navigation controller (which is typically the main activity), pass the drawer layout as the third parameter to `NavigationUI.setupActionBarWithNavController`:
```kotlin
val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
    this, R.layout.activity_main)

NavigationUI.setupActionBarWithNavController(
    this, navController, binding.drawerLayout)
```
- To make the Up button work with the drawer button, edit `onSupportNavigateUp()` to return `NavigationUI.navigateUp()`. Pass the navigation controller and the drawer layout to `navigateUp()`.
```kotlin
override fun onSupportNavigateUp(): Boolean {
   val navController = this.findNavController(R.id.myNavHostFragment)
   return NavigationUI.navigateUp(navController, drawerLayout)
}
```

### Navigation resources

Android developer documentation:

- [Navigation Architecture Component](https://developer.android.com/topic/libraries/architecture/navigation/)
- [Implement navigation with the navigation components](https://developer.android.com/topic/libraries/architecture/navigation/navigation-implementing)
- [Update UI components with NavigationUI](https://developer.android.com/topic/libraries/architecture/navigation/navigation-ui)
- [`NavigationUI`](https://developer.android.com/reference/androidx/navigation/ui/NavigationUI)
- [`NavHostFragment`](https://developer.android.com/reference/androidx/navigation/fragment/NavHostFragment)
- [Designing Back and Up navigation](https://developer.android.com/training/design-navigation/ancestral-temporal)
- [Providing proper Back navigation](https://developer.android.com/training/implementing-navigation/temporal)
- [Creating an options menu](https://developer.android.com/guide/topics/ui/menus#options-menu)
- [Create a navigation drawer](https://developer.android.com/training/implementing-navigation/nav-drawer)

Material Design documentation:

- [Understanding navigation](https://material.io/design/navigation/understanding-navigation.html#)
- [App bars: top](https://material.io/design/components/app-bars-top.html#)
- [Navigation drawer](https://material.io/design/components/navigation-drawer.html)

## Safe Args

- To help catch errors caused by missing keys or mismatched types when you pass data from one fragment to another, use the [Safe Args](https://developer.android.com/topic/libraries/architecture/navigation/navigation-pass-data#Safe-args) Gradle plugin.
- For each fragment in your app, Safe Args generates a corresponding `NavDirection` class. You can use the class to pass arguments between fragments.
- `NavDirection` represent navigation from all the app's actions.

### Implicit intents

- An [implicit intent](https://developer.android.com/training/basics/intents/sending) declares an action that your app wants some other app (e.g., camera or email) to perform on its behalf.
- If several Android apps could handle an implicit intent, Android shows the user a chooser, which prompts the user which app they want to use.
- To build an intent, declare an action to perform, for example [`ACTION_SEND`](https://developer.android.com/reference/android/content/Intent.html#ACTION_SEND).
- Several [`Intent()`](https://developer.android.com/reference/android/content/Intent.html#public-constructors_1) constructors are available to help build intents.

### Sharing functionality

- [`Intent.ACTION_SEND`](https://developer.android.com/reference/android/content/Intent.html#ACTION_SEND) delivers some data to someone else.

### Options menu

- To add an options menu to a fragment, set the `setHasOptionsMenu()` method to `true`.
- Override the `onCreateOptionsMenu()` method to inflate the menu.
- Override the `onOptionsItemSelected()` to run code when a menu item is selected.

### Safe Args resources

- [Use Safe Args to pass data with type safety](https://developer.android.com/topic/libraries/architecture/navigation/navigation-pass-data#Safe-args)
- [`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)
- [`Intent`](https://developer.android.com/reference/android/content/Intent)
- [Sending the User to Another App](https://developer.android.com/training/basics/intents/sending)