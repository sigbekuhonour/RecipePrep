package com.honoursigbeku.recipeprep.domain.model

import kotlin.jvm.JvmInline

@JvmInline
value class RecipeId(val value: String) {
    init {
        require(value.isNotBlank()) { "Recipe ID cannot be blank" }
    }
}

@JvmInline
value class CategoryName(val value: String) {
    init {
        require(value.isNotBlank()) { "Category name cannot be blank" }
    }
}

data class Ingredient(
    val name: String,
    val measure: String
)

data class RecipeSummary(
    val id: RecipeId,
    val title: String,
    val thumbnailUrl: String
){
    val idValue: String get() = id.value
}

data class RecipeDetail(
    val id: RecipeId,
    val title: String,
    val category: CategoryName,
    val area: String,
    val instructions: String,
    val thumbnailUrl: String,
    val youtubeUrl: String?,
    val ingredients: List<Ingredient>
){
    val idValue: String get() = id.value
    val categoryValue: String get() = category.value
}