package com.abdhilabs.learnroomdb.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Todo(
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "deadline") var deadline: String,
    @ColumnInfo(name = "description") var desc: String,
    @PrimaryKey(autoGenerate = true) var id: Long = 0
) : Parcelable