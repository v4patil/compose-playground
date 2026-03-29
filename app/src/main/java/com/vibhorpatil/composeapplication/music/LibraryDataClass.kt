package com.vibhorpatil.composeapplication.music

import androidx.annotation.DrawableRes
import com.vibhorpatil.composeapplication.R


data class LibraryDataClass(@DrawableRes val icon: Int,val title: String)

val libraryList = listOf<LibraryDataClass>(
LibraryDataClass(R.drawable.ic_music_library, "Playlist"),
LibraryDataClass(R.drawable.ic_music_library, "Playlist"),
LibraryDataClass(R.drawable.ic_music_library, "Playlist"),
LibraryDataClass(R.drawable.ic_music_library, "Playlist"),
LibraryDataClass(R.drawable.ic_music_library, "Playlist"),
LibraryDataClass(R.drawable.ic_music_library, "Playlist"),
LibraryDataClass(R.drawable.ic_music_library, "Playlist"),
LibraryDataClass(R.drawable.ic_music_library, "Playlist"),
LibraryDataClass(R.drawable.ic_music_library, "Playlist"),
LibraryDataClass(R.drawable.ic_music_library, "Playlist"),
LibraryDataClass(R.drawable.ic_music_library, "Playlist"),
LibraryDataClass(R.drawable.ic_music_library, "Playlist"),
)
