package pl.api

import io.reactivex.rxjava3.core.Observable
import pl.Constants
import pl.model.recipedetails.RecipeDetails
import pl.restmodel.recipedetails.RecipeDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeDetailsApi {

    @GET("/recipes/complexSearch/{id}/information")
    fun getRecipeDetails(
        @Path("id") id: Int,
        @Query("includeNutrition") isNutrition: Boolean = true,
        @Query("apiKey") key: String = Constants.API_KEY
    ): Observable<RecipeDetailsResponse>
}