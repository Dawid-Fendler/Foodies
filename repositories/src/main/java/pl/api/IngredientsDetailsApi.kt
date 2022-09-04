package pl.api

import io.reactivex.rxjava3.core.Observable
import pl.Constants
import pl.restmodel.ingredientsdetails.IngredientDetailsResponse
import pl.restmodel.ingredientsdetails.IngredientSubstitutesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IngredientsDetailsApi {
    @GET("/food/ingredients/{id}/information")
    fun getIngredientDetails(
        @Path("id") id: Int,
        @Query("apiKey") key: String = Constants.API_KEY
    ): Observable<IngredientDetailsResponse>

    @GET("/food/ingredients/substitutes")
    fun getIngredientSubstitutes(
        @Query("ingredientName") name: String,
        @Query("apiKey") key: String = Constants.API_KEY
    ): Observable<IngredientSubstitutesResponse>
}