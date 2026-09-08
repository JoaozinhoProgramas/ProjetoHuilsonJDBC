package sistema

import produto.CaixaDaAgua
import sistema.Caixa_De_Agua.CadastrarCaixa
import sistema.Caixa_De_Agua.listarCaixa


fun menuInicial() {
    val listaDeTeste = mutableListOf<CaixaDaAgua>()
    val regex = Regex("\\d")
    val validaEmail = Regex("""^[a-zA-z0-9]+.@[a-z]+(com|com.br)$""")
    val op = readln()
    validaEmail.find(op)
        if(regex.matches(op))

        do {
            println("1 sair")
            println("2 cadastrar")
            println("3 editar")
            println("4 listar")
            println("5 excluir")
            val choice = readln().toInt()
            when (choice) {
                1 -> {
                    break
                }

                2 -> {
                    CadastrarCaixa()
                }

                3 -> listarCaixa()
                4 -> println("4 listar")
                5 -> println("5 excluir")
            }
        } while (true)
    }
