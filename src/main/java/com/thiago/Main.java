package com.thiago;

import com.thiago.model.Usuario;
import com.thiago.repository.UsuarioRepository;
import com.thiago.service.UsuarioService;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static UsuarioService service = new UsuarioService();
    static Usuario usuario;
    static UsuarioRepository repository = new UsuarioRepository();

    public static void main(String[] args) {

        while(true){
            System.out.println("----- MENU INICIAL -----");
            System.out.println("[1] - CADASTRAR");
            System.out.println("[2] - LOGIN");
            System.out.println("[3] - EXIT");
            System.out.println("-------------------------");
            int opcao = sc.nextInt();

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

    public static void menuUsuario(){
        while(true){
            System.out.println("----- MENU DE USUARIO -----");
            System.out.println("[1] - ATUALIZAR EMAIL");
            System.out.println("[2] - ATUALIZAR SENHA");
            System.out.println("[3] - DELETAR CONTA");
            System.out.println("[4] - VOLTAR");
            System.out.println("----------------------------");
            int segundaOpcao = sc.nextInt();

            switch(segundaOpcao){
                case 1: atualizarEmail(); break;
                case 2: atualizarSenha(); break;
                case 3: deletar(); break;
                case 4: System.out.print("Voltando...");
                    return;

                default: System.out.print("Opção inválida");
            }
        }
    }

    public static void cadastrar(){
        try {
            System.out.print("Nos diga seu nome: ");
            String nome = sc.next();

            System.out.print("Crie seu email (usando '@gmail'): ");
            String email = sc.next();

            System.out.print("Crie sua senha (maximo 10 caracteres, caracteres especiais permitidos: @ e #): ");
            String senha = sc.next();

            service.cadastrar(nome,email, senha);

        } catch (Exception e) {
            System.out.println("Erro ao se Cadastrar " + e.getMessage());
        }

    }

    public static void login(){

        int maxTentativas = 3;
        int tentativas = 0;

        while(tentativas < maxTentativas){

            try {
                System.out.print("Digite seu email: ");
                String emailDigitado = sc.next();

                System.out.print("Digite sua senha: ");
                String senhaDigitada = sc.next();

                usuario = service.login(emailDigitado, senhaDigitada);

                System.out.println("Bem vindo, " + usuario.getNome() + "!");

                menuUsuario();
                return;

            } catch (Exception e) {
                System.out.println("Erro ao fazer Login: " + e.getMessage());
                tentativas++;
            }
            if(maxTentativas == tentativas){
                System.out.println("Número máximo de tentativas atingido. Encerrando...");
                return;
            }
        }
    }
    public static void atualizarEmail(){
        try{
            System.out.println("Primeiro, confirme sua senha: ");
            String senhaDigitada = sc.next();

            System.out.println("Digite o novo email: ");
            String email = sc.next();

            service.atualizarEmail(usuario.getId(), email, senhaDigitada);

        } catch (Exception e){
            System.out.println("Erro ao atualizar email: " + e.getMessage());
        }
    }

    public static void atualizarSenha(){
        try{
            System.out.println("Primeiro, confirme sua senha atual: ");
            String senhaDigitada = sc.next();

            System.out.println("Digite a nova senha (maximo 10 caracteres, caracteres especiais permitidos: @ e #): ");
            String senha = sc.next();

            service.atualizarSenha(usuario.getId(), senha);

        } catch (Exception e){
            System.out.println("Erro ao atualizar senha: " + e.getMessage());
        }
    }
}