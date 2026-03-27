package com.vibhorpatil.composeapplication.music

sealed class MusicScreenNavigation(val route: String) {

    sealed class DrawerScreenNavigation(dRoute: String) : MusicScreenNavigation(dRoute) {
        object Account : DrawerScreenNavigation(("Account"))
        object Subscription : DrawerScreenNavigation(("Account"))
        object AddAccount : DrawerScreenNavigation(("Account"))
    }

    sealed class BottomSheetDialogScreenNavigation(bsRoute: String) : MusicScreenNavigation(bsRoute) {
        object Home: BottomSheetDialogScreenNavigation("Home")
        object Browse: BottomSheetDialogScreenNavigation("Browse")
        object Library: BottomSheetDialogScreenNavigation("Library")
    }
}