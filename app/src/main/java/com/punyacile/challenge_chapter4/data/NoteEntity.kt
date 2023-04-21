package com.punyacile.challenge_chapter4.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "table_note")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id_notes: Int? = null,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "description")
    var description: String
) : Parcelable