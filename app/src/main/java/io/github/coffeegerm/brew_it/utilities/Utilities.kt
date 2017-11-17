package io.github.coffeegerm.brew_it.utilities

class Utilities {

    fun convertBrewDuration(originalValue: Int): String {
        return if (originalValue >= 60) {
            val simplifiedTime = originalValue / 60
            simplifiedTime.toString() + ":00 hrs"
        } else {
            originalValue.toString() + ":00 min"
        }
    }

    fun convertBrewDurationForTimer(originalValue: Int): String {
        return if (originalValue >= 60) {
            val simplifiedTime = originalValue / 60
            simplifiedTime.toString() + ":00 hrs"
        } else {
            originalValue.toString() + ":00"
        }
    }

}