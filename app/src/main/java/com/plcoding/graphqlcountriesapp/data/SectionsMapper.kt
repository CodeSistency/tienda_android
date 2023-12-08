package com.plcoding.graphqlcountriesapp.data

import com.plcoding.SectionListQuery
import com.plcoding.graphqlcountriesapp.domain.model.sections.Sections




fun SectionListQuery.Attributes.toSection(): Sections {
    return Sections(
        titulo = titulo,
        img = img.data?.attributes?.url
    )
}