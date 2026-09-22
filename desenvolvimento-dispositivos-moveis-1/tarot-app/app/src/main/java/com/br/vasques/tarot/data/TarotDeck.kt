package com.br.vasques.tarot.data

data class TarotCard(
    val id: Int,
    val numeral: String,
    val name: String,
    val imageRes: String,
    val keywords: String,
    val upright: String,
    val reversed: String
)

val SPREAD_POSITIONS = listOf("Passado", "Presente", "Futuro")

object TarotDeck {
    fun byId(id: Int): TarotCard = cards.first { it.id == id }

    val cards: List<TarotCard> = listOf(
        TarotCard(
            id = 0,
            numeral = "0",
            name = "O Louco",
            imageRes = "rws_00_fool",
            keywords = "Novos começos • espontaneidade • confiança no desconhecido",
            upright = "Marca o início de uma jornada. A carta convida você a dar um passo com leveza e coragem, mesmo sem saber todos os detalhes do caminho.",
            reversed = "Imprudência, decisões precipitadas ou medo de começar algo novo."
        ),
        TarotCard(
            id = 1,
            numeral = "I",
            name = "O Mago",
            imageRes = "rws_01_magician",
            keywords = "Vontade • habilidade • manifestação",
            upright = "Você tem os recursos e o talento necessários para transformar uma ideia em realidade. É hora de agir com foco e intenção.",
            reversed = "Talento mal aproveitado, manipulação ou falta de foco."
        ),
        TarotCard(
            id = 2,
            numeral = "II",
            name = "A Sacerdotisa",
            imageRes = "rws_02_high_priestess",
            keywords = "Intuição • mistério • sabedoria interior",
            upright = "Pede que você escute a sua voz interior e confie na intuição. Nem tudo está à vista: há respostas que surgem no silêncio.",
            reversed = "Ignorar a intuição, segredos ou desconexão de si mesmo."
        ),
        TarotCard(
            id = 3,
            numeral = "III",
            name = "A Imperatriz",
            imageRes = "rws_03_empress",
            keywords = "Criatividade • abundância • cuidado",
            upright = "Simboliza fertilidade, criatividade e acolhimento. Fase de crescimento, prazer e cuidado com quem você ama.",
            reversed = "Dependência, excesso de zelo ou bloqueio criativo."
        ),
        TarotCard(
            id = 4,
            numeral = "IV",
            name = "O Imperador",
            imageRes = "rws_04_emperor",
            keywords = "Autoridade • estrutura • estabilidade",
            upright = "Representa ordem, disciplina e liderança. Bom momento para organizar a vida, definir limites e assumir responsabilidades.",
            reversed = "Rigidez, autoritarismo ou falta de controle."
        ),
        TarotCard(
            id = 5,
            numeral = "V",
            name = "O Hierofante",
            imageRes = "rws_05_hierophant",
            keywords = "Tradição • aprendizado • orientação",
            upright = "Indica busca por conhecimento, valores e mentores. Pode sugerir seguir regras estabelecidas ou aprender com quem tem mais experiência.",
            reversed = "Rebeldia contra regras, dogmatismo ou necessidade de abrir o próprio caminho."
        ),
        TarotCard(
            id = 6,
            numeral = "VI",
            name = "Os Enamorados",
            imageRes = "rws_06_lovers",
            keywords = "Amor • escolhas • harmonia",
            upright = "Fala de relações profundas e de decisões alinhadas aos seus valores. Uma escolha importante exige coração e consciência.",
            reversed = "Desequilíbrio em uma relação, indecisão ou escolhas contrárias aos seus valores."
        ),
        TarotCard(
            id = 7,
            numeral = "VII",
            name = "O Carro",
            imageRes = "rws_07_chariot",
            keywords = "Determinação • vitória • controle",
            upright = "Vitória por meio de foco e disciplina. Você conduz forças opostas na mesma direção e avança com confiança.",
            reversed = "Falta de direção, agressividade ou perda de controle."
        ),
        TarotCard(
            id = 8,
            numeral = "VIII",
            name = "A Força",
            imageRes = "rws_08_strength",
            keywords = "Coragem • paciência • domínio interior",
            upright = "Não é força bruta, e sim a serenidade de lidar com desafios e emoções com compaixão e firmeza.",
            reversed = "Insegurança, dúvida sobre si mesmo ou emoções descontroladas."
        ),
        TarotCard(
            id = 9,
            numeral = "IX",
            name = "O Eremita",
            imageRes = "rws_09_hermit",
            keywords = "Introspecção • busca • solitude",
            upright = "Momento de recolhimento para refletir e buscar respostas dentro de si. A luz que você procura já está com você.",
            reversed = "Isolamento excessivo, solidão ou fuga da reflexão."
        ),
        TarotCard(
            id = 10,
            numeral = "X",
            name = "A Roda da Fortuna",
            imageRes = "rws_10_wheel_of_fortune",
            keywords = "Ciclos • destino • mudança",
            upright = "A vida se move em ciclos. Uma virada está a caminho, lembrando que nada é permanente, nem os altos nem os baixos.",
            reversed = "Fase ruim passageira, resistência à mudança ou sensação de azar."
        ),
        TarotCard(
            id = 11,
            numeral = "XI",
            name = "A Justiça",
            imageRes = "rws_11_justice",
            keywords = "Equilíbrio • verdade • consequências",
            upright = "Trata de imparcialidade, honestidade e responsabilidade. Suas ações têm consequências e a verdade virá à tona.",
            reversed = "Injustiça, desonestidade ou fuga de responsabilidades."
        ),
        TarotCard(
            id = 12,
            numeral = "XII",
            name = "O Enforcado",
            imageRes = "rws_12_hanged_man",
            keywords = "Pausa • entrega • nova perspectiva",
            upright = "Pede para suspender a pressa e olhar a situação por outro ângulo. Às vezes, esperar e soltar é o caminho.",
            reversed = "Estagnação, sacrifício inútil ou resistência em soltar."
        ),
        TarotCard(
            id = 13,
            numeral = "XIII",
            name = "A Morte",
            imageRes = "rws_13_death",
            keywords = "Transformação • fim de ciclo • renascimento",
            upright = "Raramente fala de morte literal. Indica o encerramento de uma fase para dar lugar a algo novo.",
            reversed = "Medo da mudança, apego ao passado ou transformação adiada."
        ),
        TarotCard(
            id = 14,
            numeral = "XIV",
            name = "A Temperança",
            imageRes = "rws_14_temperance",
            keywords = "Equilíbrio • moderação • paciência",
            upright = "Harmonia entre opostos, com calma e medida. Busque o meio-termo e deixe as coisas se combinarem no tempo certo.",
            reversed = "Excessos, desequilíbrio ou impaciência."
        ),
        TarotCard(
            id = 15,
            numeral = "XV",
            name = "O Diabo",
            imageRes = "rws_15_devil",
            keywords = "Apego • tentação • sombra",
            upright = "Mostra correntes que muitas vezes nós mesmos criamos: vícios, dependências, medos. Reconhecê-los é o primeiro passo para se libertar.",
            reversed = "Libertação de padrões antigos e retomada do poder pessoal."
        ),
        TarotCard(
            id = 16,
            numeral = "XVI",
            name = "A Torre",
            imageRes = "rws_16_tower",
            keywords = "Ruptura • revelação • mudança súbita",
            upright = "Algo construído sobre bases frágeis desmorona. É doloroso, mas abre espaço para reconstruir com mais verdade.",
            reversed = "Medo de uma mudança inevitável ou crise evitada por pouco."
        ),
        TarotCard(
            id = 17,
            numeral = "XVII",
            name = "A Estrela",
            imageRes = "rws_17_star",
            keywords = "Esperança • renovação • inspiração",
            upright = "Depois da tempestade, a calma. Traz cura, fé no futuro e a sensação de estar no caminho certo.",
            reversed = "Desânimo, perda de esperança ou falta de fé."
        ),
        TarotCard(
            id = 18,
            numeral = "XVIII",
            name = "A Lua",
            imageRes = "rws_18_moon",
            keywords = "Ilusão • medo • subconsciente",
            upright = "Nem tudo é o que parece. Emoções e medos vêm à tona: confie na intuição, mas evite decisões baseadas em ilusões.",
            reversed = "Confusão se dissipando e medos sendo superados."
        ),
        TarotCard(
            id = 19,
            numeral = "XIX",
            name = "O Sol",
            imageRes = "rws_19_sun",
            keywords = "Alegria • sucesso • vitalidade",
            upright = "Uma das cartas mais positivas: clareza, otimismo e conquistas. Energia para brilhar e compartilhar felicidade.",
            reversed = "Otimismo excessivo ou alegria temporariamente bloqueada."
        ),
        TarotCard(
            id = 20,
            numeral = "XX",
            name = "O Julgamento",
            imageRes = "rws_20_judgement",
            keywords = "Renascimento • chamado • avaliação",
            upright = "Momento de olhar para a jornada, perdoar-se e atender a um chamado interior. É hora de decidir quem você quer ser.",
            reversed = "Autocrítica excessiva, dúvida ou ignorar o chamado."
        ),
        TarotCard(
            id = 21,
            numeral = "XXI",
            name = "O Mundo",
            imageRes = "rws_21_world",
            keywords = "Conclusão • realização • plenitude",
            upright = "Um ciclo se completa com sucesso. Sensação de integração, conquista e prontidão para um novo começo.",
            reversed = "Falta de fechamento ou projetos inacabados."
        )
    )
}