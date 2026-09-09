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
        assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.cadastrar(nome, "teste12@gmail.com", "Teste123");
        });
    }

    // Testando o método cadastrar para verificar se lança exceção quando o email ja estiver em uso por outro usuario
    @Test
    public void deveLancarExcecaoQuandoEmailJaForExistente(){

        Mockito.when(usuarioRepository.emailJaExistente("teste123@gmail.com")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.cadastrar("Teste", "teste123@gmail.com", "Teste123");
        });
    }

    // Testando o método cadastrar para verificar se lança exceção quando a senha é inválida ao se cadastrar Usuario.
    @ParameterizedTest
    @NullSource
    @ValueSource(strings = { "", "Teste 123", "testeDoTeste", "teste_"})
    public void deveLancarExcecaoQuandoSenhaForInvalida(String senha){
        assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.cadastrar("Teste", "teste@gmail.com", senha);
        });
    }
    //Testando se usuario esta sendo cadastrado, sem lançar exceção.
    @Test
    public void naoDeveLancarExcecaoSeUsuarioForCadastradoComSucesso() {
        assertDoesNotThrow(() -> {
            usuarioService.cadastrar("Teste", "teste123@gmail.com", "Teste123");
        });

        Mockito.verify(usuarioRepository).salvar(Mockito.any(Usuario.class));
    }
    // ----- TESTES PARA O MÉTODO LOGIN ------

}
