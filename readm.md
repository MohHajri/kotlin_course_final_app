This code implements an Android application that displays NASA's Astronomy Picture of the Day (APOD) using the NASA API. The application uses Retrofit to communicate with the API and retrieves the APOD data in JSON format. The retrieved data is parsed using the Gson library and displayed using Android's Data Binding library.

<br>
<br>
<br>

### The application consists of two fragments:
<br>

ListFragment: This fragment displays a list of APODs using a RecyclerView. When an APOD is clicked, it opens a DetailFragment with more information about the APOD.
DetailFragment: This fragment displays the details of a single APOD. It shows the APOD's title, date, explanation, and an image or video.