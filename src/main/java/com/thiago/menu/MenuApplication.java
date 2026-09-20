package com.thiago.menu;

import com.thiago.exceptions.EmailInvalidoException;
import com.thiago.exceptions.NomeInvalidoException;
import com.thiago.exceptions.SenhaInvalidaException;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.thiago.CrudApplication.*;

public class MenuApplication {
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

    // Retirei a validação da main pra por aqui pois ficaria confuso de mais de tanto metodos staticos lá

    /*
    Métodos de verificação para ver se Usuario colocou nome, email e senha vazio
    o metodo vai pegar o que o usuario digitou, vai verificar se é vazio com isBlank
    se for, vai dar mensagem de erro.

    A mensagem aparecerá na tela no momento que ele der enter, no nome ou email ou senha
    (no momento essa mensagem ainda quebra o fluxo de cadastro do usuario),
     a pessoa tem que recomeçar o processo até conseguir executar da forma certa.*/

    public static String lerNome(Scanner sc){
        System.out.println("Digite seu Nome: ");
        String nome = sc.nextLine();

        if(nome.isBlank()){
            throw new NomeInvalidoException("Nome inválido");
        }
        return nome;
    }

    public static String lerEmail(Scanner sc){
        System.out.println("Crie seu Email (usando '@gmail' e '.com'): ");
        String email = sc.nextLine();

        if(email.isBlank() || !email.contains("@gmail")){
            throw new EmailInvalidoException("Email inválido");
        }
        return email;
    }

    public static String lerSenha(Scanner sc){
        System.out.println("Crie sua Senha (Min 4 - Max 10 caracteres, letras e números. Caracteres especiais permitidos: @ e #):");
        String senha = sc.nextLine();

        if(senha.isBlank()){
            throw new SenhaInvalidaException("Senha inválida");
        }
        return senha;
    }
}
