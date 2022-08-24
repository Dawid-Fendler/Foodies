package pl.api

import io.reactivex.rxjava3.core.Observable
import pl.Constants.API_KEY
import pl.restmodel.recipes.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesApi {

    @GET("/recipes/complexSearch")
    fun getRecipes(
        @Query("number") recipeLimit: Int = 20,
        @Query("apiKey") key: String = API_KEY
    ): Observable<RecipeResponse>
}