package com.vibhorpatil.composeapplication.music

import androidx.annotation.DrawableRes
import com.vibhorpatil.composeapplication.R

sealed class Screen(val title: String, val route: String) {

    sealed class DrawerScreen(val dTitle: String, val dRoute: String, @DrawableRes val icon: Int)
        : Screen(dTitle, dRoute){
        object Account : DrawerScreen(
            "Account",
            "account",
            R.drawable.ic_account
        )

        object Subscription : DrawerScreen(
            "Subscription",
            "subscribe",
            R.drawable.ic_subscribe
        )

        object AddAccount : DrawerScreen(
            "Add Account",
            "add_account",
            R.drawable.ic_add_account
        )

    }

    sealed class BottomScreen(val bTitle: String, val bRoute: String, @DrawableRes val icon: Int)
        : Screen(bTitle, bRoute)//return type
    {
        object Home : BottomScreen("Home", "home", R.drawable.ic_music)
        object Library : BottomScreen("Library", "library", R.drawable.ic_music_library)
        object Browse : BottomScreen("Browse", "browse", R.drawable.ic_browse_music)
    }

}

val screensInBottom = listOf(
    Screen.BottomScreen.Home,
    Screen.BottomScreen.Browse,
    Screen.BottomScreen.Library
)

val screensInDrawer = listOf(
    Screen.DrawerScreen.Account,
    Screen.DrawerScreen.Subscription,
    Screen.DrawerScreen.AddAccount
)