# 🔮 Tarot App

<div align="center">
  <img src="https://github.com/carlosno/ifsp-pos-web-mobile/raw/main/desenvolvimento-dispositivos-moveis-1/demo.gif" width="600" alt="Demonstração do Tarot App" />
</div>

Aplicativo mobile de leitura de cartas de Tarot, desenvolvido como **trabalho final** da disciplina de **Desenvolvimento para Dispositivos Móveis I**, integrante da Pós-Graduação em Desenvolvimento de Sistemas Web e Aplicativos Móveis do **Instituto Federal de São Paulo (IFSP) - Campus Capivari**.

---

## 📥 Download e Instalação

Pode fazer download e testar o aplicativo diretamente no seu dispositivo Android através do link abaixo:

🔗 **[Download APK do Tarot App](https://drive.google.com/file/d/1O8MqANbNXfv0SioM7mz2ZKrfoynk4Oop/view?usp=sharing)**

---

## 📱 Sobre o Projeto

O **Tarot App** é um aplicativo Android nativo que simula leituras e tiragens de Tarot. Ele foi estruturado utilizando as práticas modernas de desenvolvimento Android, focado em uma interface reativa e componentes reaproveitáveis. Além das funcionalidades tradicionais, o app conta com Inteligência Artificial rodando localmente para oferecer interpretações personalizadas e um sistema completo de gerenciamento do histórico de leituras.

### ✨ Funcionalidades
- **Carta do Dia (`DailyCardScreen`):** Sorteio de uma carta diária para orientação rápida, com um cronômetro de contagem regressiva indicando exatamente quando o próximo sorteio estará disponível.
- **Tiragem de 3 Cartas (`SpreadScreen`):** Uma mesa de Tarot focada no Passado, Presente e Futuro. O usuário pode inserir uma dúvida específica (opcional), como por exemplo, uma pergunta sobre a sua carreira.
- **Interpretação com IA:** Através do botão "Interpretar Tiragem com IA", o aplicativo analisa as 3 cartas sorteadas juntamente com a pergunta do usuário, gerando uma leitura completa, personalizada e profunda.
- **Histórico e Anotações:** O usuário pode salvar suas tiragens favoritas clicando em "Salvar leitura". Na tela "Minhas leituras", é possível visualizar o histórico de tiragens, adicionar e editar anotações pessoais, ou excluir leituras antigas.
- **Catálogo de Cartas (`CardsListScreen`):** Uma lista completa para consulta dos significados de todas as cartas do baralho (tanto na posição normal quanto invertida). Inclui uma barra de pesquisa dinâmica ("Buscar carta") para encontrar cartas rapidamente pelo nome.

---

## 🛠️ Tecnologias e Arquitetura

O projeto foi construído inteiramente no ecossistema Android moderno:

- **Linguagem:** Kotlin
- **Interface Gráfica (UI):** Jetpack Compose
- **Arquitetura:** MVVM (Model-View-ViewModel)
- **Design System:** Material Design 3
- **Inteligência Artificial (LLM Local):** Integração com o modelo `gemma-3-270m-it-qat-Q4_0.gguf` para a interpretação das tiragens. 
  > ⚠️ **Nota:** O arquivo do modelo `.gguf` não foi incluído na pasta `assets` deste repositório devido ao fato de ultrapassar o limite de tamanho de 100 MB permitido pelo GitHub.

### 📂 Estrutura de Diretórios
A separação de responsabilidades (Separation of Concerns) foi aplicada dividindo o app em pacotes específicos de dados e interface visual:

```plaintext
com.br.vasques.tarot/
├── data/                         # Camada de Dados e Lógica de Negócio
│   ├── CardImage.kt              # Mapeamento das imagens
│   ├── LlmLocalService           # Serviço de integração com o modelo de IA
│   ├── TarotDeck.kt              # Lógica do baralho de Tarot
│   └── TarotRepository.kt        # Repositório de acesso aos dados
├── ui/                           # Camada de Apresentação (UI)
│   ├── components/               
│   │   └── TarotCardView.kt      # Componente visual reutilizável das cartas
│   ├── screens/                  # Telas do aplicativo
│   │   ├── CardsListScreen.kt    
│   │   ├── DailyCardScreen.kt    
│   │   └── SpreadScreen.kt       
│   ├── theme/                    # Configurações de design e aparência
│   │   ├── Color.kt              
│   │   ├── Theme.kt              
│   │   └── Type.kt               
│   ├── TarotApp.kt               # Estrutura principal e navegação Compose
│   └── TarotViewModel            # Gerenciamento de estado da UI
├── MainActivity                  # Activity principal do aplicativo
├── assets/                       # Arquivos estáticos e modelos
│   └── gemma-3-270m-it-qat-Q4_0.gguf
└── res/
    └── drawable/                 # Recursos gráficos e imagens em domínio público
        ├── ic_launcher_background.xml
        ├── ic_launcher_foreground.xml
        ├── rws_00_fool.png
        ├── rws_01_magician.png
        ├── rws_02_high_priestess.png
        └── ... (demais cartas do baralho)
