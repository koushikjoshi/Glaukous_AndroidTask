# Glaukous Android Task
An Android app that fetches warehouse data from APIs and displays it.

## Project Structure
This project can be divided into 2 modules
- **The UI/Logic part**
All the XML files and UI rendering logic
- **The API fetching part**
- interfaces, classes and data classes to send requests to APIs

### UI/Logic part

This app has one activity -

- MainActivity.kt
This has a FrameLayout with a fragment, and a bottom-bar based navigation with 5 categories.

- HomeFragment.kt
This fragment is inflated in the FrameLayout when the home button is pressed.

#### Tech used

- RecyclerView
- Fragments
- BottomNavigation
- DiffUtil

### API fetching part

- BatchNumData, Data, Item, TodoFirstX, TodoApi, RetrofitInstance are files used for fetching API and parsing the response JSON.

#### Tech used

- Retrofit
- Kotlin Data Classes

# Download the APK:

You can send a request to download the apk [here](https://drive.google.com/file/d/1Kol2n9XpbVIpnS3ECTXwnRiVtHEjT_r5/view?usp=sharing)