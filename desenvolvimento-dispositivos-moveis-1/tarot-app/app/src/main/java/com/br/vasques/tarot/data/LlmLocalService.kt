package com.br.vasques.tarot.data

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.codeshipping.llamakotlin.LlamaModel
import java.io.File
import java.io.FileOutputStream

class LlmLocalService(private val context: Context) {

    private var llamaModel: LlamaModel? = null
    private val modelFileName = "gemma-3-270m-it-qat-Q4_0.gguf"

    suspend fun initializeModel() {
        withContext(Dispatchers.IO) {
            val modelFile = File(context.filesDir, modelFileName)

            if (!modelFile.exists()) {
                context.assets.open(modelFileName).use { input ->
                    FileOutputStream(modelFile).use { output ->
                        input.copyTo(output)
                    }
                }
            }

            llamaModel = LlamaModel.load(modelFile.absolutePath) {
                contextSize = 1024
                threads = 4
                temperature = 0.8f
            }
        }
    }

    suspend fun getTarotReading(card1: String, card2: String, card3: String, userQuestion: String): Result<String> {
        return withContext(Dispatchers.IO) {
            try {
                if (llamaModel == null) {
                    return@withContext Result.failure(Exception("A IA ainda não foi inicializada."))
                }

                val questionContext = if (userQuestion.isNotBlank()) {
                    "A dúvida do consulente é: '$userQuestion'"
                } else {
                    "Faça uma leitura geral."
                }

                // Prompt com Priming Forte
                val prompt = """
                    <start_of_turn>user
                    Você é um tarólogo conselheiro místico. $questionContext
                    
                    Cartas sorteadas:
                    - Passado: $card1
                    - Presente: $card2
                    - Futuro: $card3
                    
                    Com base nos significados acima, escreva um parágrafo de conselho profundo e místico.<end_of_turn>
                    <start_of_turn>model
                    Os astros
                """.trimIndent()

                val rawResponse = llamaModel!!.generate(prompt).trim()

                // Reconstrói a frase porque a IA vai começar a escrever DEPOIS de "Os astros"
                val finalResponse = "Os astros $rawResponse".trim()

                if (finalResponse.length < 20) {
                    Result.failure(Exception("Os astros estão confusos agora. Tente de novo."))
                } else {
                    Result.success(finalResponse)
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    fun close() {
        llamaModel?.close()
    }
}