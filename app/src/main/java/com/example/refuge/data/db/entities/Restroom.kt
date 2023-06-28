package com.example.refuge.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Restroom(
    @PrimaryKey
    val id: Long,
    val name: String,
    val street: String,
    val city: String,
    val state: String,
    val accessible: Boolean,
    val unisex: Boolean,
    val directions: String,
    val comment: String,
    val latitude: Double,
    val longitude: Double,
    val downvote: Long,
    val upvote: Long,
    val country: String,
    val changingTable: Boolean,
    val editID: Long,
    val approved: Boolean,
    val distance: Double,
    val bearing: String? = null
)