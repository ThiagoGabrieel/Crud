package com.thiago.service;

import com.thiago.model.Usuario;
import com.thiago.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    // ----- TESTES PARA O MÉTODO CADASTRAR ------


    // Testando o método cadastrar para verificar se lança exceção quando o nome é nulo ou vazio ao se cadastrar Usuario.
    @ParameterizedTest
    @NullAndEmptySource
    public void deveLancarExcecaoQuandoNomeForNuloOuVazio(String nome){
        IllegalArgumentException exception =  assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.cadastrar(nome, "teste12@gmail.com", "Teste123");
        });

        assertEquals("Nome inválido", exception.getMessage());
    }

    // Testando o método cadastrar para verificar se lança exceção quando o email ja estiver em uso por outro usuario
    @Test
    public void deveLancarExcecaoQuandoEmailJaForExistente(){

        Mockito.when(usuarioRepository.emailJaExistente("teste123@gmail.com")).thenReturn(true);

        IllegalArgumentException exception =  assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.cadastrar("Teste", "teste123@gmail.com", "Teste123");
        });

        assertEquals("Email já Existente no momento!", exception.getMessage());

        Mockito.verify(usuarioRepository).emailJaExistente("teste123@gmail.com");
    }

    // Testando o método cadastrar para verificar se lança exceção quando a senha é inválida ao se cadastrar Usuario.
    @ParameterizedTest
    @NullSource
    @ValueSource(strings = { "", "Teste 123", "testeDoTeste", "teste_"})
    public void deveLancarExcecaoQuandoSenhaForInvalida(String senha){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.cadastrar("Teste", "teste@gmail.com", senha);
        });

        assertEquals("Senha inválida", exception.getMessage());
    }

    //Testando se usuario esta sendo cadastrado.
    @Test
    public void naoDeveLancarExcecaoSeUsuarioForCadastradoComSucesso() {
        assertDoesNotThrow(() -> {
            usuarioService.cadastrar("Teste", "teste123@gmail.com", "Teste123");
        });
    }


    // ----- TESTES PARA O MÉTODO LOGIN ------


    // Testando o metodo Login para verificar se não encontrar usuario é lançado exceção
    @Test
    public void deveLancarExcecaoSeUsuarioNaoForEncontrado(){
        Mockito.when(usuarioRepository.buscarPorEmail("teste13@gmail.com")).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> {
           usuarioService.login("teste13@gmail.com", "teste123");
        });

        Mockito.verify(usuarioRepository).buscarPorEmail("teste13@gmail.com");
    }

    //Testando metodo login para saber se a exceção é lançado quando senha for Incorreta
    @Test
    public void deveLancarExcecaoSeSenhaForIncorreta(){
        Usuario usuarioSenhaIncorreta = new Usuario("Teste", "teste00@gmail.com", "Teste900");

        Mockito.when(usuarioRepository.buscarPorEmail("teste00@gmail.com")).thenReturn(usuarioSenhaIncorreta);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.login("teste00@gmail.com", "senhaErrada");
        });

        assertEquals("Senha incorreta!", exception.getMessage());

        Mockito.verify(usuarioRepository).buscarPorEmail("teste00@gmail.com");
    }


    // ----- TESTES PARA O MÉTODO ATUALIZAR EMAIL ------

    //Testando buscar usuario pelo Id, se não for encontrado lança a exceção
    @Test
    public void deveLancarExcecaoSeUsuarioNaoForEncontradoAtualizarEmail(){
        Mockito.when(usuarioRepository.buscarPorId(1L)).thenReturn(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
           usuarioService.atualizarEmail(1L,"teste@gmail.com", "teste900");
        });

        assertEquals("Usuario não encontrado!", exception.getMessage());

        Mockito.verify(usuarioRepository).buscarPorId(1L);
    }

    //Testando se senha invalida lança exceção antes de atualizar o email
    @Test
    public void deveLancarExcecaoQuandoSenhaForIncorretaAtualizarEmail(){
        Usuario usuarioAtualizarEmail = new Usuario(1L,"Teste", "teste@gmail.com", "teste123");
        Mockito.when(usuarioRepository.buscarPorId(1L)).thenReturn(usuarioAtualizarEmail);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.atualizarEmail(usuarioAtualizarEmail.getId(), usuarioAtualizarEmail.getEmail(), "teste321");
        });

        assertEquals("Senha incorreta!", exception.getMessage());

        Mockito.verify(usuarioRepository).buscarPorId(1L);
    }
}
