package com.kaycode.feature.latest_films.data

import com.kaycode.core.model.DiscoverMovieResponse
import com.kaycode.core.model.MovieThumbnail
import com.kaycode.core.network.retrofit.model.ApiResponse

interface ILatestFilmsRepository {
    suspend fun getLatestFilms(page: Int): ApiResponse<DiscoverMovieResponse>
}

//curl --request GET \
//     --url 'https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&primary_release_date.gte=2025-11-01&primary_release_date.lte=2026-02-22&sort_by=primary_release_date.desc' \
//     --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhYTVlN2QwODEzMzE1YmExMDA1YTM0MDEzMGI2ZTBjMSIsIm5iZiI6MTc3MTc3NzA3OS44MzYsInN1YiI6IjY5OWIyYzM3MjkwYTVkNWNkZDgwZWU3MyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ip67K2T-wyTjwC1rmwnOdIh3AZ3NUn-YwiotsdHIuAk' \
//     --header 'accept: application/json'

// im just gonna keep this here ive been coding all day long and im TIRED :thumbsup: