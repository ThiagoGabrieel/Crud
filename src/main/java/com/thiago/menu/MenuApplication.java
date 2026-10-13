package com.thiago.menu;

import com.thiago.exceptions.*;
import com.thiago.repository.UsuarioRepository;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.thiago.CrudApplication.*;

public class MenuApplication {
    Scanner sc = new Scanner(System.in);
    static UsuarioRepository repository = new UsuarioRepository();

    public void menuInicial(){
        while(true){
            int opcao;

            try {
                System.out.println("----- MENU INICIAL -----");
                System.out.println("[1] - CADASTRAR");
                System.out.println("[2] - LOGIN");
                System.out.println("[3] - EXIT");
                System.out.println("-------------------------");
                System.out.print("Escolha: ");
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

            int segundaOpcao;
            try {
                System.out.println("----- MENU DE USUARIO -----");
                System.out.println("[1] - ATUALIZAR EMAIL");
                System.out.println("[2] - ATUALIZAR SENHA");
                System.out.println("[3] - DELETAR CONTA");
                System.out.println("[4] - VOLTAR");
                System.out.println("----------------------------");
                System.out.print("Escolha: ");
                segundaOpcao = sc.nextInt();
            }
            catch (InputMismatchException e) {
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

    // Retirei a validação da main pra por aqui, pois ficaria confuso de mais de tanto metodos staticos lá

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
        System.out.println("Crie seu Email (usando '@' e '.com'): ");
        String email = sc.nextLine();

        if(email.isBlank() || !email.contains("@") || !email.contains(".com")){
            throw new EmailInvalidoException("Email inválido");
        }
        if (repository.emailJaExistente(email)) {
            throw new EmailJaCadastradoException("Email já Existente no momento!");
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

    public static String lerEmailDigitadoLogin(Scanner sc){
        System.out.println("Digite seu Email: ");
        String emailDigitado = sc.nextLine();

        if (emailDigitado.isBlank()) {
            throw new EmailInvalidoException("Email inválido");
        }
        return emailDigitado;
    }

    public static String lerSenhaDigitadaLogin(Scanner sc){
        System.out.println("Digite seu Senha: ");
        String senhaDigitada = sc.nextLine();

        if (senhaDigitada.isBlank()) {
            throw new SenhaInvalidaException("Senha inválida");
        }
        return senhaDigitada;
    }

    public static String lerSenhaDigitadaAtualizarEmail(Scanner sc){
        System.out.println("Primeiro, confirme sua senha: ");
        String senhaDigitada = sc.next();

        if (senhaDigitada.isBlank()) {
            throw new SenhaInvalidaException("Senha inválida");
        }
        return senhaDigitada;
    }

    public static String lerEmailAtualizarEmail(Scanner sc){
        System.out.println("Digite o novo email: ");
        String email = sc.next();

        if(email.isBlank() || !email.contains("@") || !email.contains(".com")){
            throw new EmailInvalidoException("Email inválido");
        }
        if (repository.emailJaExistente(email)) {
            throw new EmailJaCadastradoException("Email já Existente no momento!");
        }
        return email;
    }

    public static String lerSenhaDigitadaAtualizarSenha(Scanner sc){
        System.out.println("Primeiro, confirme sua senha atual: ");
        String senhaDigitada = sc.next();

        if(senhaDigitada.isBlank()) {
            throw new SenhaInvalidaException("Senha inválida");
        }
        return senhaDigitada;
    }

    public static String lerSenhaAtualizarSenha(Scanner sc){
        System.out.println("Digite a nova senha (maximo 10 caracteres, letras e números. Caracteres especiais permitidos: @ e #): ");
        String senha = sc.next();

        if(senha.isBlank()){
            throw new SenhaInvalidaException("Senha inválida");
        }
        return senha;
    }

    public static String lerSenhaDeletar(Scanner sc){
        System.out.println("Primeiro, confirme sua senha: ");
        String senha = sc.next();

        if(senha.isBlank()){
            throw new SenhaInvalidaException("Senha inválida");
        }
        return senha;
    }
}
