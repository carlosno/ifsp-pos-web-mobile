# 🔮 Tarot App

Aplicativo mobile de leitura de cartas de Tarot desenvolvido para a disciplina de **Desenvolvimento para Dispositivos Móveis I**, integrante da Pós-Graduação em Desenvolvimento de Sistemas Web e Aplicativos Móveis do **Instituto Federal de São Paulo (IFSP) - Campus Capivari**.

---

## 📱 Sobre o Projeto

O **Tarot App** é um aplicativo Android nativo que simula leituras e tiragens de Tarot. Ele foi estruturado utilizando as práticas modernas de desenvolvimento Android, focado em uma interface reativa e componentes reaproveitáveis.

### ✨ Funcionalidades
Com base na estrutura do projeto, o aplicativo conta com as seguintes telas e funcionalidades[cite: 1]:
- **Carta do Dia (`DailyCardScreen`):** Sorteio de uma carta diária para orientação rápida[cite: 1].
- **Tiragem / Mesa de Tarot (`SpreadScreen`):** Tela dedicada a jogos e tiragens mais complexas (como passado, presente e futuro)[cite: 1].
- **Catálogo de Cartas (`CardsListScreen`):** Uma lista completa para consulta dos significados de todas as cartas do baralho[cite: 1].

---

## 🛠️ Tecnologias e Arquitetura

O projeto foi construído inteiramente no ecossistema Android moderno[cite: 1]:

- **Linguagem:** Kotlin[cite: 1]
- **Interface Gráfica (UI):** Jetpack Compose (`TarotApp.kt`, `TarotCardView.kt`)[cite: 1]
- **Arquitetura:** MVVM (Model-View-ViewModel) utilizando o `TarotViewModel.kt` para o gerenciamento de estado[cite: 1].
- **Design System:** Material Design 3 configurado via Jetpack Compose (`Theme.kt`, `Color.kt`, `Type.kt`)[cite: 1].

### 📂 Estrutura de Diretórios
A separação de responsabilidades (Separation of Concerns) foi aplicada dividindo o app em camadas[cite: 1]:

```plaintext
tarot/
├── MainActivity.kt          # Ponto de entrada do aplicativo[cite: 1]
├── ui/                      # Camada de Apresentação (UI)[cite: 1]
│   ├── TarotApp.kt          # Setup de navegação e estrutura do Compose[cite: 1]
│   ├── TarotViewModel.kt    # Lógica de apresentação e estado da UI[cite: 1]
│   ├── components/          # Widgets reutilizáveis (ex: TarotCardView)[cite: 1]
│   ├── screens/             # Telas do aplicativo (DailyCard, Spread, CardsList)[cite: 1]
│   └── theme/               # Configurações de tema, tipografia e cores[cite: 1]
└── data/                    # Camada de Dados[cite: 1]
    ├── TarotRepository.kt   # Ponto único de acesso aos dados das cartas[cite: 1]
    ├── TarotDeck.kt         # Lógica e manipulação do baralho completo[cite: 1]
    └── CardImage.kt         # Modelo para mapeamento das imagens das cartas[cite: 1]
