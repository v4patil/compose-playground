package com.vibhorpatil.composeapplication.music

import androidx.annotation.DrawableRes
import com.vibhorpatil.composeapplication.R

sealed class Screen(val title: String, val route: MusicScreenNavigation) {

    sealed class DrawerScreen(val dTitle: String, val dRoute: MusicScreenNavigation.DrawerScreenNavigation, @DrawableRes val icon: Int)
        : Screen(dTitle, dRoute){
        object Account : DrawerScreen(
            "Account",
            MusicScreenNavigation.DrawerScreenNavigation.Account,
            R.drawable.ic_account
        )

        object Subscription : DrawerScreen(
            "Subscription",
            MusicScreenNavigation.DrawerScreenNavigation.Subscription,
            R.drawable.ic_subscribe
        )

        object AddAccount : DrawerScreen(
            "Add Account",
            MusicScreenNavigation.DrawerScreenNavigation.AddAccount,
            R.drawable.ic_add_account
        )

    }

    sealed class BottomScreen(val bTitle: String, val bRoute: MusicScreenNavigation.BottomSheetDialogScreenNavigation, @DrawableRes val icon: Int)
        : Screen(bTitle, bRoute)//return type
    {
        object Home : BottomScreen("Home", MusicScreenNavigation.BottomSheetDialogScreenNavigation.Home, R.drawable.ic_music)
        object Library : BottomScreen("Library", MusicScreenNavigation.BottomSheetDialogScreenNavigation.Library, R.drawable.ic_music_library)
        object Browse : BottomScreen("Browse", MusicScreenNavigation.BottomSheetDialogScreenNavigation.Browse, R.drawable.ic_browse_music)
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