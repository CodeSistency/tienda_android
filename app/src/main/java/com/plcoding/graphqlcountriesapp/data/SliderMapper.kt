package com.plcoding.graphqlcountriesapp.data

import com.plcoding.CategoryListQuery
import com.plcoding.SliderListQuery
import com.plcoding.graphqlcountriesapp.domain.model.categories.Categories
import com.plcoding.graphqlcountriesapp.domain.model.slider.Slider

fun SliderListQuery.Attributes.toSlider(): Slider {
    return Slider(
        titulo = titulo,
        button = button,
        image = img.data?.attributes?.url
    )
}