#include <stdio.h>

int main(void) {

    char remedio[50];

    int intervalo;
    int horarioInicial;

    int horario2, horario3, horario4;

    printf("=== Controle de Horarios de Medicamentos ===\n");

    // Nome do remédio
    printf("Digite o nome do remedio: ");
    scanf("%s", remedio);

    // Primeira dose
    printf("Digite o horario da primeira dose: ");
    scanf("%d", &horarioInicial);

    // Intervalo
    printf("Digite o intervalo do remedio (6, 8 ou 12 horas): ");
    scanf("%d", &intervalo);

    printf("\n=== HORARIOS SUGERIDOS ===\n");

    printf("Remedio: %s\n", remedio);

    // 12 em 12 horas
    if (intervalo == 12) {

        horario2 = horarioInicial + 12;

        if (horario2 >= 24) {
            horario2 = horario2 - 24;
        }

        printf("Tomar as %dh\n", horarioInicial);
        printf("Tomar as %dh\n", horario2);

    }

    // 8 em 8 horas
    else if (intervalo == 8) {

        horario2 = horarioInicial + 8;

        if (horario2 >= 24) {
            horario2 = horario2 - 24;
        }

        horario3 = horario2 + 8;

        if (horario3 >= 24) {
            horario3 = horario3 - 24;
        }

        printf("Tomar as %dh\n", horarioInicial);
        printf("Tomar as %dh\n", horario2);
        printf("Tomar as %dh\n", horario3);

    }

    // 6 em 6 horas
    else if (intervalo == 6) {

        horario2 = horarioInicial + 6;

        if (horario2 >= 24) {
            horario2 = horario2 - 24;
        }

        horario3 = horario2 + 6;

        if (horario3 >= 24) {
            horario3 = horario3 - 24;
        }

        horario4 = horario3 + 6;

        if (horario4 >= 24) {
            horario4 = horario4 - 24;
        }

        printf("Tomar as %dh\n", horarioInicial);
        printf("Tomar as %dh\n", horario2);
        printf("Tomar as %dh\n", horario3);
        printf("Tomar as %dh\n", horario4);

    }

    else {

        printf("Intervalo invalido.\n");

    }

    return 0;
}