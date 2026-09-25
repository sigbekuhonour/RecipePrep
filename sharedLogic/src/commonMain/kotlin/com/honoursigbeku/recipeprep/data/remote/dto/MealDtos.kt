package com.honoursigbeku.recipeprep.data.remote.dto

import com.honoursigbeku.recipeprep.domain.model.CategoryName
import com.honoursigbeku.recipeprep.domain.model.Ingredient
import com.honoursigbeku.recipeprep.domain.model.RecipeDetail
import com.honoursigbeku.recipeprep.domain.model.RecipeId
import com.honoursigbeku.recipeprep.domain.model.RecipeSummary
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable
data class MealSummaryResponseDto(
    @SerialName("meals") val meals: List<MealSummaryDto>?
)

@Serializable
data class MealSummaryDto(
    @SerialName("idMeal") val idMeal: String,
    @SerialName("strMeal") val strMeal: String,
    @SerialName("strMealThumb") val strMealThumb: String
)

@Serializable
data class MealDetailResponseDto(
    @SerialName("meals") val meals: List<JsonObject>?
)

// Data Mappers
fun MealSummaryDto.toDomain(): RecipeSummary = RecipeSummary(
    id = RecipeId(idMeal),
    title = strMeal,
    thumbnailUrl = strMealThumb
)

fun JsonObject.toRecipeDetailDomain(): RecipeDetail {
    val id = this["idMeal"]?.jsonPrimitive?.content.orEmpty()
    val title = this["strMeal"]?.jsonPrimitive?.content.orEmpty()
    val category = this["strCategory"]?.jsonPrimitive?.content.orEmpty()
    val area = this["strArea"]?.jsonPrimitive?.content.orEmpty()
    val instructions = this["strInstructions"]?.jsonPrimitive?.content.orEmpty()
    val thumbnail = this["strMealThumb"]?.jsonPrimitive?.content.orEmpty()
    val youtube = this["strYoutube"]?.jsonPrimitive?.content

    val ingredients = mutableListOf<Ingredient>()
    for (i in 1..20) {
        val ingredientName = this["strIngredient$i"]?.jsonPrimitive?.content?.trim()
        val measure = this["strMeasure$i"]?.jsonPrimitive?.content?.trim().orEmpty()

        if (!ingredientName.isNullOrBlank()) {
            ingredients.add(Ingredient(name = ingredientName, measure = measure))
        }
    }

    return RecipeDetail(
        id = RecipeId(id),
        title = title,
        category = CategoryName(category),
        area = area,
        instructions = instructions,
        thumbnailUrl = thumbnail,
        youtubeUrl = youtube?.takeIf { it.isNotBlank() },
        ingredients = ingredients
    )
}