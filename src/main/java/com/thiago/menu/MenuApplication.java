package com.thiago.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.thiago.CrudApplication.*;

public class InterfaceApplication {
    Scanner sc = new Scanner(System.in);

    public void menuInicial(){
        while(true){
            System.out.println("----- MENU INICIAL -----");
            System.out.println("[1] - CADASTRAR");
            System.out.println("[2] - LOGIN");
            System.out.println("[3] - EXIT");
            System.out.println("-------------------------");
            System.out.print("Escolha: ");
            int opcao;

            try {
                opcao = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida, digite somente os números apresentados na tela!");

                sc.next();
                continue;
            }

            switch (opcao){
                case 1: cadastrar(); break;
                case 2: login(); break;
                case 3: System.out.print("Encerrando...");
                    sc.close();
                    return;

                default: System.out.println("Opção inválida");
            }
        }
    }

    public void menuUsuario(){
        while(true){
            System.out.println("----- MENU DE USUARIO -----");
            System.out.println("[1] - ATUALIZAR EMAIL");
            System.out.println("[2] - ATUALIZAR SENHA");
            System.out.println("[3] - DELETAR CONTA");
            System.out.println("[4] - VOLTAR");
            System.out.println("----------------------------");
            System.out.print("Escolha: ");
            int segundaOpcao;

            try {
                segundaOpcao = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida, digite somente os números apresentados na tela!");

                sc.next();
                continue;
            }

            switch(segundaOpcao){
                case 1: atualizarEmail(); break;
                case 2: atualizarSenha(); break;
                case 3: deletar(); break;
                case 4: System.out.println("Voltando...");
                    return;

                default: System.out.println("Opção inválida");
            }
        }
    }
}
