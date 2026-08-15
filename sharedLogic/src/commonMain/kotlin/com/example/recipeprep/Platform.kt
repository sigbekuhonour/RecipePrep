package com.example.recipeprep

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform