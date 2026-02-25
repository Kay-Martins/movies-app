# movies-app
A simple android application that displays a list of the latest movies from the TMDB API and shows extended information when certain movies are clicked.

## Technical trade-offs

There's obviously a lot missing or things that could have been done better. The majority of these issues are simply due to the lack of time to properly finish and polish them. I'll explain as much as I can

### Lack of tests
There are a lack of tests due to the restricted time, and I've only completed tests for one small class in the data layer. However If I were to have time, I'd definitely finish them all in all layers. I think with the way all the modules and individual classes are set up, mocking and testing would be a lot easier, which can give me a higher overall test coverage percentage

### 2nd use case (show movie details when tapping on a specific film)
The prerequisite work has been done to get started, but due to a lack of time I was not able to actually get started on it. But I think if I did complete it, it would be in a separate feature module with all of its logic from the repositories to the view model to the screen itself. It would have its own activity and I would have instructed the app to switch to that new activity from the LatestFilmsActivity, passing the film ID as a Int bundle to go with it. 

This Int would be our key, because this will be passed onto the API call I would make to fetch the film details. I believe it was https://api.themoviedb.org/3/movie/{movie_id}. From here I would display the details (not all, but maybe a few important ones like overview, genres, title, release date and original language). From there I would fetch the data, handling any of the various responses I could get. Based on the response this is reflected in the screen (with the view model emitting the right states so the screen itself knows what to render, basically like how the Latest Films Screen does it). One thing to improve would just be some cleaner navigation so the user can easily go back

### API key being stored locally
This isn't actually the best in terms of security, but I thought that it may not be the biggest deal as all we're fetching is film data. If it was more sensitive like payments, this would obviously have to be fetched from the server side, probably rotated and needs to expire quickly, but that's a different use case. I went with this as it was quick to do, given the restricted time

### build.gradle.kts files are a bit of a mess
A lot of duplicate dependencies are being shoved into them. The best solution would be to have a conventional plugin, so all the common dependencies and plugins I need can be put in the appropriate build.gradle files

### Considerations
This was build with scalability in mind, probably why it's quite modular (although it could be argued that the granularity is a bit overkill given that it's a very small app. I did want to showcase these skills, though)

### Dependency Injection issues
It's a shame that this is what took the majority of the time away, and DI can be a bit of a hassle to deal with. I did my best to resolve as many as I can but there were still some that prevented the app from doing what it needed to do


again, this isn't really an excuse as to why the app is pretty subpar. It can definitely be made a lot better, regardless of time!
