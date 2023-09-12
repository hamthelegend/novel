package com.thebrownfoxx.novel.sample

import com.thebrownfoxx.novel.Character
import com.thebrownfoxx.novel.Choice
import com.thebrownfoxx.novel.Novel
import com.thebrownfoxx.novel.Prompt

val Narrator = Character("Narrator")
val Steve = Character("Steve")
val Alex = Character("Alex")

data class Item(val name: String)

class GameState {
    val shovel = Item("Shovel")

    private val _inventory = mutableListOf<Item>()
    val inventory: List<Item> = _inventory

    private val prompts = mapOf(
        1 to Prompt(
            character = Steve,
            text = "Man, I'm horny.",
            Choice(
                text = "Next",
                promptKey = { 2 },
            )
        ),
        2 to Prompt(
            character = Steve,
            text = "What should I do?",
            Choice(
                text = "Masturbate",
                promptKey = { 3 },
            ),
            Choice(
                text = "Fuck ${Alex.name}",
                promptKey = { 4 },
            )
        ),
        3 to Prompt(
            character = Narrator,
            text = "Steve is no longer horny. You win!",
        ),
        4 to Prompt(
            character = Alex,
            text = "${Steve.name}, what are you doing?",
            Choice(
                text = "Next",
                promptKey = { 5 },
            )
        ),
        5 to Prompt(
            character = Narrator,
            text = "${Alex.name} died from ${Steve.name}'s horse cock.",
            Choice(
                text = "Okay",
                promptKey = { 6 },
            ),
        ),
        6 to Prompt(
            character = Steve,
            text = "[Whimpers] What do I do? What do I do?",
            Choice(
                text = "Bury ${Alex.name}",
                promptKey = { if (shovel !in _inventory) 7 else 11 },
            ),
            Choice(
                text = "Open closet",
                promptKey = { if (shovel !in _inventory) 8 else 10 },
            )
        ),
        7 to Prompt(
            character = Steve,
            text = "I need a shovel for that.",
            Choice(
                text = "Okay",
                promptKey = { 6 },
            )
        ),
        8 to Prompt(
            character = Steve,
            text = "Nice! A shovel!",
            Choice(
                text = "Take shovel",
                promptKey = { 9 }
            )
        ),
        9 to Prompt(
            character = Narrator,
            text = "Steve got a shovel.",
            Choice(
                text = "Okay",
                promptKey = { 6 },
            ) { _inventory.add(shovel) },
        ),
        10 to Prompt(
            character = Steve,
            text = "I don't think there's any more for me here.",
            Choice(
                text = "Okay",
                promptKey = { 6 },
            ),
        ),
        11 to Prompt(
            character = Narrator,
            text = "You buried Alex. You win!",
        ),
    )

    val novel = Novel(prompts, firstPromptKey = 1)
}