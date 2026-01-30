package com.canaldothiago.motivation.repository

import com.canaldothiago.motivation.helpers.MotivationConstants
import kotlin.random.Random

data class Phrase(var description: String, var category: Int)

class PhraseRepository {
    val happy = MotivationConstants.PHRASE.HAPPY
    val sunny = MotivationConstants.PHRASE.SUNNY

    val listPhrase = listOf(
        Phrase("Não sabendo que era impossível, foi lá e fez.", happy),
        Phrase("Você não é derrotado quando perde, você é derrotado quando desiste!", happy),
        Phrase("Quando está mais escuro, vemos mais estrelas!", happy),
        Phrase("Insanidade é fazer sempre a mesma coisa e esperar um resultado diferente.", happy),
        Phrase("Não pare quando estiver cansado, pare quando tiver terminado.", sunny),
        Phrase("O que você pode fazer agora que tem o maior impacto sobre o seu sucesso?", sunny),
        Phrase("A melhor maneira de prever o futuro é inventá-lo.", sunny),
        Phrase("Você perde todas as chances que você não aproveita.", sunny)
    )

    fun getPhrase(filter: Int): String {
        if (filter == MotivationConstants.PHRASE.ALL) {
            return listPhrase[Random.nextInt(listPhrase.size)].description
        } else {
            val filtered = listPhrase.filter {
                (it.category == filter || filter == MotivationConstants.PHRASE.ALL)
            }
            return filtered[Random.nextInt(filtered.size)].description
        }
    }
}