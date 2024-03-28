package evam.interestgames.megatigr.model

sealed class Screen {


    data object Load : Screen()
    data object Game : Screen()
}