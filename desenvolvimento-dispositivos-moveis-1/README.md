# 🔮 Tarot App

Aplicativo mobile de leitura de cartas de Tarot, desenvolvido como **trabalho final** da disciplina de **Desenvolvimento para Dispositivos Móveis I**, integrante da Pós-Graduação em Desenvolvimento de Sistemas Web e Aplicativos Móveis do **Instituto Federal de São Paulo (IFSP) - Campus Capivari**.

---

## 📱 Sobre o Projeto

O **Tarot App** é um aplicativo Android nativo que simula leituras e tiragens de Tarot. Ele foi estruturado utilizando as práticas modernas de desenvolvimento Android, focado em uma interface reativa e componentes reaproveitáveis.

### ✨ Funcionalidades
- **Carta do Dia (`DailyCardScreen`):** Sorteio de uma carta diária para orientação rápida.
- **Tiragem / Mesa de Tarot (`SpreadScreen`):** Tela dedicada a jogos e tiragens mais complexas (como passado, presente e futuro).
- **Catálogo de Cartas (`CardsListScreen`):** Uma lista completa para consulta dos significados de todas as cartas do baralho.

---

## 🛠️ Tecnologias e Arquitetura

O projeto foi construído inteiramente no ecossistema Android moderno:

- **Linguagem:** Kotlin
- **Interface Gráfica (UI):** Jetpack Compose
- **Arquitetura:** MVVM (Model-View-ViewModel)
- **Design System:** Material Design 3

### 📂 Estrutura de Diretórios
A separação de responsabilidades (Separation of Concerns) foi aplicada dividindo o app em camadas:

```plaintext
tarot/
├── MainActivity.kt          # Ponto de entrada do aplicativo
├── ui/                      # Camada de Apresentação (UI)
│   ├── TarotApp.kt          # Setup de navegação e estrutura do Compose
│   ├── TarotViewModel.kt    # Lógica de apresentação e estado da UI
│   ├── components/          # Widgets reutilizáveis (ex: TarotCardView)
│   ├── screens/             # Telas do aplicativo (DailyCard, Spread, CardsList)
│   └── theme/               # Configurações de tema, tipografia e cores
└── data/                    # Camada de Dados
    ├── TarotRepository.kt   # Ponto único de acesso aos dados das cartas
    ├── TarotDeck.kt         # Lógica e manipulação do baralho completo
    └── CardImage.kt         # Modelo para mapeamento das imagens das cartas
