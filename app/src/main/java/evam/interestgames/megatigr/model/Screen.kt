package evam.interestgames.megatigr.model

sealed class Screen {


    data object Load : Screen()
    data object Game : Screen()

    data object Main:Screen()
    data object Settings:Screen()
    data object Policy:Screen()
}