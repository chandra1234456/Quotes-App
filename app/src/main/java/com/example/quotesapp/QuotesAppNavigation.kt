package com.example.quotesapp

sealed class QuotesAppNavigation(val route: String) {
    object HomeScreenNav : QuotesAppNavigation(Constants.HOME_SCREEN)
    object ExploreScreenNav : QuotesAppNavigation(Constants.EXPLORE_SCREEN)
    object SavedScreenNav : QuotesAppNavigation(Constants.SAVED_SCREEN)
}