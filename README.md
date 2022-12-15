# Gallery App using Jetpack Compose

[LinkedIn](https://linkedin.com/in/prabhatsdp) | [Twitter](https://twitter.com/prabhatsdp) | [Facebook](https://facebook.com/prabhatsdp)

It is a Gallery app developed by me using Jetpack Compose, a declarative UI framework for Android.

It uses Unsplash Public API to query paginated image list. When you click on an it shows the full image in another screen.

### Tools Used


| Tools / Libraries   | Used For                      |
|---------------------|-------------------------------|
| Retrofit            | Network Calls                 |
| Hilt                | Dependency Injection          |
| Landscapist / Glide | Image Loading                 |
| Compose Navigation  | For navigation around screens |
| Timber              | For logging                   |

### Steps to Build and Run
* Clone the repository either from Command line or from Android Studio itself. 
* Add a file named ```keys.properties``` in root folder. 
* Define a key ```CLIENT_ID``` inside ```keys.properties``` file.
  * ```CLIENT_ID="YOUR_UNSPLASH_API_KEY"```
  * Get your client ID from Unsplash site if you don't have it already.
* Hit run and enjoy.