package com.br.vasques.tarot.data

import android.content.Context

fun cardImageModel(context: Context, card: TarotCard): Any {
    val resId = context.resources.getIdentifier(
        card.imageRes, "drawable", context.packageName
    )
    return resId
}
