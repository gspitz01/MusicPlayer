package com.gregspitz.musicplayer.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "track")
data class Track(@PrimaryKey val id: String = UUID.randomUUID().toString(),
                 val fileName: String, val displayName: String, val lengthSeconds: Int)
