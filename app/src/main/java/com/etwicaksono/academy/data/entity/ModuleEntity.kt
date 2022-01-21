package com.etwicaksono.academy.data.entity

import androidx.annotation.NonNull
import androidx.room.*

@Entity(
    tableName = "moduleentities",
    primaryKeys = ["moduleId", "courseId"],
    foreignKeys = [ForeignKey(
        entity = CourseEntity::class,
        parentColumns = ["courseId"],
        childColumns = ["courseId"]
    )],
    indices = [Index(value = ["moduleId"]),
        Index(value = ["courseId"])]
)
data class ModuleEntity(
    @NonNull
    @ColumnInfo(name = "moduleId")
    var moduleId: String,

    @NonNull
    @ColumnInfo(name = "courseId")
    var courseId: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "position")
    var position: Int,

    @ColumnInfo(name = "read")
    var read: Boolean = false
) {
    @Embedded
    var contentEntity: ContentEntity? = null
}
