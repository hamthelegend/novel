package com.thebrownfoxx.novel

data class Character(val name: String)

data class Choice<K : Any>(
    val text: String,
    val promptKey: () -> K,
    val action: () -> Unit = {},
)

data class Prompt<K : Any>(
    val speaker: Character,
    val text: String,
    val choices: List<Choice<K>>,
) {
    constructor(
        character: Character,
        text: String,
        vararg choices: Choice<K>,
    ) : this(character, text, choices.toList())
}