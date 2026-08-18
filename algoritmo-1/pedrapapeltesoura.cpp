#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main() {

    int postura;
    int computador;
    char jogarNovamente = 'S';

    // Inicializa o gerador de números aleatórios
    srand(time(NULL));

    while(jogarNovamente == 'S' || jogarNovamente == 's') {

        // Sorteia a postura do computador
        computador = rand() % 3 + 1;

        printf("\n=================================\n");
        printf("      PEDRA PAPEL TESOURA\n");
        printf("=================================\n");
        printf("1 - Pedra\n");
        printf("2 - Papel\n");
        printf("3 - Tesoura\n");
        printf("Escolha sua postura: ");
        scanf("%d", &postura);

        if(postura < 1 || postura > 3) {
            printf("\nOpcao invalida! Escolha 1, 2 ou 3.\n");
            continue;
        }

        printf("\nSua postura:\n");

        if(postura == 1) {
            printf("PEDRA\n");
            printf("    _______\n");
            printf("---'   ____)\n");
            printf("      (_____)\n");
            printf("      (_____)\n");
            printf("      (____)\n");
            printf("---.__(___)\n");
        }
        else if(postura == 2) {
            printf("PAPEL\n");
            printf("     _______\n");
            printf("---'    ____)____\n");
            printf("           ______)\n");
            printf("          _______)\n");
            printf("         _______)\n");
            printf("---.__________)\n");
        }
        else {
            printf("TESOURA\n");
            printf("    _______\n");
            printf("---'   ____)____\n");
            printf("          ______)\n");
            printf("       __________)\n");
            printf("      (____)\n");
            printf("---.__(___)\n");
        }

        printf("\nComputador escolheu:\n");

        // Exibe a postura do computador
        if(computador == 1) {
            printf("PEDRA\n");
            printf("    _______\n");
            printf("---'   ____)\n");
            printf("      (_____)\n");
            printf("      (_____)\n");
            printf("      (____)\n");
            printf("---.__(___)\n");
        }
        else if(computador == 2) {
            printf("PAPEL\n");
            printf("     _______\n");
            printf("---'    ____)____\n");
            printf("           ______)\n");
            printf("          _______)\n");
            printf("         _______)\n");
            printf("---.__________)\n");
        }
        else {
            printf("TESOURA\n");
            printf("    _______\n");
            printf("---'   ____)____\n");
            printf("          ______)\n");
            printf("       __________)\n");
            printf("      (____)\n");
            printf("---.__(___)\n");
        }

        // Determina o vencedor
        if(postura == computador) {
            printf("\nEMPATE!\n");
        }
        else if((postura == 1 && computador == 3) ||
                (postura == 2 && computador == 1) ||
                (postura == 3 && computador == 2)) {
            printf("\nVOCE VENCEU!\n");
        }
        else {
            printf("\nCOMPUTADOR VENCEU!\n");
        }

        printf("\nDeseja jogar novamente? (S/N): ");
        scanf(" %c", &jogarNovamente);
    }

    printf("\n=================================\n");
    printf("Obrigado por jogar!\n");
    printf("===================================\n");

    return 0;
}