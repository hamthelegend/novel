package com.thebrownfoxx.novel.sample

import com.thebrownfoxx.novel.Choice

fun main() {
    val gameState = GameState()
    while (true) {
        println("--------------------------------------------------")
        println("Inventory: ${gameState.inventory.map { it.name }}")
        var promptText = gameState.novel.currentPrompt.text
        if (gameState.novel.currentPrompt.speaker != Narrator) {
            promptText = "${gameState.novel.currentPrompt.speaker.name}: $promptText"
        }
        println(promptText)
        if (gameState.novel.currentPrompt.choices.isEmpty()) {
            break
        }
        val choice = if (gameState.novel.currentPrompt.choices.size == 1) {
            val onlyChoice = gameState.novel.currentPrompt.choices.first()
            print("[Enter] ${onlyChoice.text}")
            readln()
            onlyChoice
        } else {
            var chosenChoice: Choice<Int>? = null
            while (chosenChoice == null) {
                for ((index, choice) in gameState.novel.currentPrompt.choices.withIndex()) {
                    println("[${index + 1}] - ${choice.text}")
                }
                print("Enter the number of your choice: ")
                val chosenIndex = readln().toIntOrNull()?.minus(1) ?: -1
                chosenChoice = gameState.novel.currentPrompt.choices.getOrNull(chosenIndex)
            }
            chosenChoice
        }
        gameState.novel.choose(choice)
    }
}