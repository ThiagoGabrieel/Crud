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
}