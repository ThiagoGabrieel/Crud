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

    public static  void cadastrar(){
        System.out.print("Nos diga seu nome: ");
        String nome = sc.next();

        System.out.print("Crie seu email (usando '@gmail'): ");
        String email = sc.next();

        System.out.print("Crie sua senha (maximo 10 caracteres, caracteres especiais permitidos: @ e #): ");
        String senha = sc.next();

        service.cadastrar(nome,email, senha);
    }
}