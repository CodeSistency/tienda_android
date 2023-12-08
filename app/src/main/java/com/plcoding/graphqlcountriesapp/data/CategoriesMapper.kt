package com.plcoding.graphqlcountriesapp.data

import com.plcoding.CategoryListQuery
import com.plcoding.graphqlcountriesapp.domain.model.categories.Categories

fun CategoryListQuery.Attributes.toCategory(): Categories {
    return Categories(
        titulo = titulo,
        img = img.data?.attributes?.url
    )
}