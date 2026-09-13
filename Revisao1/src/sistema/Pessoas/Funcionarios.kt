package sistema.Pessoas

import enumeradores.Setor

class Funcionarios {
    fun cadastrar() {
        println("Cadastro de funcionarios")

        val validaNome = Regex("^[a-zA-Z]+( [a-zA-Z]+)*$")
        val validaEmail = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(com|com\\.br)$")

        while (true) {
            println("Digite o nome")
            val nome = readln() ?: ""

            if(validaNome.matches(nome)){
                println("Nome cadastrado com sucesso")
                println("Nome: $nome")
                break
            }else {
                println("Nome invalido, tente novamente")
            }
        }

        while (true) {
            println("Digite o email")
            val email = readln() ?: "" // Elvis verifica se o input é null, se for, ele passar como "" (Caractere em branco)

            if (validaEmail.matches(email)) {
                println("E-mail cadastrado com sucesso")
                println("Email: $email")
                break
            } else {
                println("E-mail inválido, tente novamente")
            }
        }

        println("Digite o setor")
        Setor.entries.forEach { setor ->
            println(" ${setor.ordinal + 1} - ${setor.name}")
        }

        val escolhaSetor = readlnOrNull()?.toIntOrNull()

        if (escolhaSetor != null && escolhaSetor in 1..Setor.entries.size) {
            val setor = Setor.entries[escolhaSetor - 1]
            println("Escolha de setor: ${setor.name}")
        } else {
            println("Escolha inválida, tente novamente")
        }
    }
}