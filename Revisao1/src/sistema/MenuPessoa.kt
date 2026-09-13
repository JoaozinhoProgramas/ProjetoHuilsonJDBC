package sistema

import sistema.Pessoas.Forncedores
import sistema.Pessoas.Funcionarios

fun menuPessoas() {
    println("Menu Pessoas")
    do {
        println("1 - Cadastrar")
        println("2 - Pagamento")
        val choice = readln().toInt()

        when(choice) {
            1 -> CadastrarPessoas()
            2 -> println("Função de pagamento")
        }
    }while(true)
}

fun CadastrarPessoas() {
    println("Cadastrar Pessoas")
    do {
        println("1 - Cadastrar Funcionario")
        println("2 - Cadastrar Fornecedores")

        val choice = readln().toInt()

        when(choice) {
            1 -> Funcionarios().cadastrar()
            2 -> Forncedores().cadastrar()
        }
    }while(true)
}