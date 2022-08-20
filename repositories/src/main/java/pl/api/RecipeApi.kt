package pl.api

import io.reactivex.rxjava3.core.Observable
import pl.Constants.API_KEY
import pl.restmodel.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {

    @GET("/recipes/complexSearch")
    fun getRecipes(
        @Query("number") recipeLimit: Int,
        @Query("apiKey") key: String = API_KEY
    ): Observable<RecipeResponse>
}