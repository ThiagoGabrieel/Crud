package com.thiago;

import com.thiago.menu.MenuApplication;
import com.thiago.exceptions.UsuarioException;
import com.thiago.model.Usuario;
import com.thiago.repository.UsuarioRepository;
import com.thiago.service.UsuarioService;
import java.util.Scanner;

public class CrudApplication {
    static Scanner sc = new Scanner(System.in);
    static UsuarioRepository repository = new UsuarioRepository();
    static UsuarioService service = new UsuarioService(repository);
    static Usuario usuario;
    static MenuApplication application = new MenuApplication();

    public static void main(String[] args) {
        application.menuInicial();
    }

    public static void cadastrar(){
        try {
            System.out.println("Nos diga seu Nome: ");
            String nome = sc.next();

            System.out.println("Crie seu Email (usando '@gmail'): ");
            String email = sc.next();

            System.out.println("Crie sua Senha (Min 4 - Max 10 caracteres, letras e números. Caracteres especiais permitidos: @ e #):");
            String senha = sc.next();

            service.cadastrar(nome,email, senha);

        } catch (UsuarioException e) {
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

                application.menuUsuario();
                return;

            } catch (UsuarioException e) {
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
            System.out.println("Email atualizado com sucesso!");

        } catch (UsuarioException e){
            System.out.println("Erro ao atualizar email: " + e.getMessage());
        }
    }

    public static void atualizarSenha(){
        try{
            System.out.println("Primeiro, confirme sua senha atual: ");
            String senhaDigitada = sc.next();

            System.out.println("Digite a nova senha (maximo 10 caracteres, letras e números. Caracteres especiais permitidos: @ e #): ");
            String senha = sc.next();

            service.atualizarSenha(usuario.getId(), senhaDigitada, senha);
            System.out.println("Senha atualizada com sucesso!");

        } catch (UsuarioException e){
            System.out.println("Erro ao atualizar senha: " + e.getMessage());
        }
    }

    public static void deletar(){

        while(true){
            try{

                System.out.println("Primeiro, confirme sua senha: ");
                String senha = sc.next();

                System.out.print("Deseja realmente deletar sua conta? (Sim/Nao): ");
                String confirmacao = sc.next();

                if(confirmacao.equalsIgnoreCase("sim")){
                    service.deletar(usuario.getId(), senha);
                    System.out.println("Conta deletada com sucesso!");

                    application.menuInicial();
                    return;

                } else if(confirmacao.equalsIgnoreCase("nao")) {
                    System.out.println("Operação de deletar conta cancelada.");
                    return;

                } else{
                    System.out.println("Opção inválida. Por favor, digite 'Sim' ou 'Nao'.");
                }

            }  catch (UsuarioException e) {
                System.out.println("Erro ao deletar Conta: " + e.getMessage());
            }
        }
    }
}