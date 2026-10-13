package com.thiago;

import com.thiago.exceptions.*;
import com.thiago.menu.MenuApplication;
import com.thiago.model.Usuario;
import com.thiago.repository.UsuarioRepository;
import com.thiago.service.UsuarioService;
import java.util.Scanner;
import static com.thiago.menu.MenuApplication.*;

public class CrudApplication {
    static Scanner sc = new Scanner(System.in);
    static UsuarioRepository repository = new UsuarioRepository();
    static UsuarioService service = new UsuarioService(repository);
    static Usuario usuario;
    static MenuApplication application = new MenuApplication();

    public static void main(String[] args) {
        repository.tabelaAutomatica();
        application.menuInicial();
    }

    public static void cadastrar(){
        System.out.println("----- CADASTRAR-SE -----");

        String nome;
        while(true){
            try {
                //as vezes a pessoa diigta sem querer a opção e quer voltar....
                System.out.println("Se quiser voltar para tela inicial digite: exit");
                nome = lerNome(sc);
                if (nome.toLowerCase().equals("exit")){
                    return;
                }
                break;
            }
            catch (UsuarioException e){
                System.out.println("Erro ao cadastrar nome: " + e.getMessage());
            }
        }

        String email;
        while(true){
            try {
                email = lerEmail(sc);
                break;
            }
            catch (UsuarioException e) {
                System.out.println("Erro ao cadastrar email: " + e.getMessage());
            }
        }

        String senha;
        while(true){
            try {
                senha = lerSenha(sc);
                break;
            }
            catch (UsuarioException e){
                System.out.println("Erro ao cadastrar senha: " + e.getMessage());
            }
        }

        service.cadastrar(nome,email,senha);
        System.out.println("------------------------");
    }

    public static void login(){

        int maxTentativas = 3;
        int tentativas = 0;

        String emailDigitado;
        String senhaDigitada;

        while(tentativas < maxTentativas){

            try {
                System.out.println("----- LOGIN -----");
                System.out.println("Se quiser voltar para tela inicial digite: exit");

                emailDigitado = lerEmailDigitadoLogin(sc);
                if (emailDigitado.toLowerCase().equals("exit")){
                    return;
                }

                senhaDigitada = lerSenhaDigitadaLogin(sc);
                // Essa entrada de scanner não deixara ter que dar enter duas vezes após colocar sua senha
                //ele come a quebra de linha pendente que fica após o scanner de senha
                sc.nextLine();
                usuario = service.login(emailDigitado, senhaDigitada);
                System.out.println("-----------------");

                System.out.println("Bem vindo, " + usuario.getNome() + "!");

                application.menuUsuario();
                return;

            }
            catch (UsuarioException e) {
                System.out.println("Erro ao fazer login: " + e.getMessage());
                tentativas++;

            }
            if(maxTentativas == tentativas){
                System.out.println("Número máximo de tentativas atingido. Encerrando...");
                return;
            }
        }
    }

    public static void atualizarEmail(){
        String senhaDigitada;
        String email;
        try{
            System.out.println("----- ATUALIZAR EMAIL -----");
            senhaDigitada = lerSenhaDigitadaAtualizarEmail(sc);
            email = lerEmailAtualizarEmail(sc);
            sc.nextLine();

            service.atualizarEmail(usuario.getId(), email, senhaDigitada);
            System.out.println("Email atualizado com sucesso!");
            System.out.println("------------------------------");

        } catch (UsuarioException e){
            System.out.println("Erro ao atualizar email: " + e.getMessage());
        }
    }

    public static void atualizarSenha(){
        String senhaDigitada;
        String senha;
        try{
            System.out.println("----- ATUALIZAR SENHA -----");
            senhaDigitada = lerSenhaDigitadaAtualizarSenha(sc);
            senha = lerSenhaAtualizarSenha(sc);
            sc.nextLine();

            service.atualizarSenha(usuario.getId(), senhaDigitada, senha);
            System.out.println("Senha atualizada com sucesso!");
            System.out.println("------------------------------");

        } catch (UsuarioException e){
            System.out.println("Erro ao atualizar senha: " + e.getMessage());
        }
    }

    public static void deletar(){
        String senha;
        while(true){
            try{
                System.out.println("----- DELETAR CONTA -----");
                senha = lerSenha(sc);
                sc.nextLine();

                System.out.print("Deseja realmente deletar sua conta? (Sim/Nao): ");
                String confirmacao = sc.next();

                if(confirmacao.equalsIgnoreCase("sim")){
                    service.deletar(usuario.getId(), senha);
                    System.out.println("Conta deletada com sucesso!");

                    System.out.println("-------------------------");
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