package com.punyacile.challenge_chapter4.data

import androidx.room.*

@Dao
interface NoteDao {
    @Insert
    suspend fun insertNote(note: NoteEntity)

    @Query("SELECT * FROM table_note ORDER BY title DESC")
    suspend fun getAllNotesDesc(): List<NoteEntity>

    @Query("SELECT * FROM table_note ORDER BY title ASC")
    suspend fun getAllNotesAsc(): List<NoteEntity>

    @Query("SELECT * FROM table_note")
    suspend fun getAllNotes(): List<NoteEntity>

    @Delete
    suspend fun deleteNote(note: NoteEntity)

    @Update
    suspend fun updateNote(note: NoteEntity)
}