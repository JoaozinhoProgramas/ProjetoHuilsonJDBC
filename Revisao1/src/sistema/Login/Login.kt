package sistema.Login

import repositorio.JDBC
import java.util.Locale.getDefault

class Login {
    val conexao = JDBC()

    fun criaLogin() {
        println("Criando login ...")

        println("Digite o usuario:")
        val usuario = readln().trim()

        println("Digite a senha:")
        val senha = readln().trim()

        println("Digite o código de setor")
        val codigoSetor = readln().toInt()

        val erro = validarDadosCadastro(usuario, senha)

        if (erro != null) {
            println("Erro: $erro")
            return
        }

        conexao.cadastrarUsuario(
            (User(
                usuario = usuario,
                senha = senha,
                codigoSetor = codigoSetor
                )
            )
        )
    }


    fun validaLogin(): String? {
        var usuario: String = "" //Só pra deixar o compilador ciente que o usuario tem valor, dps ele muda pelo input
        var sucesso: Boolean

        do {

            println("Digite o usuario:")
            usuario = readln().uppercase(getDefault()).trim()

            when(usuario) {
                "R" -> criaLogin()
                "L" -> criaLogin()
            }

            println("Digite a senha:")
            val senha = readln().uppercase(getDefault()).trim()

            val credencial = conexao.buscarLoginPorUsuario(usuario)

            val erro = validarDadosCadastro(usuario, senha)

            if (erro != null) {
                println("Erro: $erro")
            }

            sucesso = if (credencial == null) {
                println("Usuário não encontrado.")
                false
            } else if (credencial.senha == senha) {
                println("Login realizado com sucesso!")
                true
            } else {
                println("Senha incorreta.")
                false
            }

        } while (!sucesso)

        return usuario
    }

    fun validarDadosCadastro(usuario: String, senha: String): String? {
        return when {
            usuario.isBlank() -> "Usuário não pode ser vazio."
            senha.isBlank() -> "Senha não pode ser vazia."
            usuario.length < 5 -> "Usuário deve ter pelo menos 5 caracteres."
            senha.length < 5 -> "Senha deve ter pelo menos 5 caracteres."
            usuario.any { it.isWhitespace() } -> "Usuário não pode conter espaços."
            else -> null // null = tudo válido
        }
    }
}