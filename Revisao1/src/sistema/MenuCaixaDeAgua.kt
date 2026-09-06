package sistema

import produto.CaixaDaAgua
import sistema.Caixa_De_Agua.CadastrarCaixa
import sistema.Caixa_De_Agua.CompraCaixa
import sistema.Caixa_De_Agua.VendaCaixa
import sistema.Caixa_De_Agua.listarCaixa


fun menuCaixaDeAgua() {
    val listaDeTeste = mutableListOf<CaixaDaAgua>()
    /*val regex = Regex("\\d")
    val validaEmail = Regex("""^[a-zA-z0-9]+.@[a-z]+(com|com.br)$""")
    val op = readln()
    validaEmail.find(op)
        if(regex.matches(op))*/

        do {
            println("1 sair")
            println("2 cadastrar")
            println("3 listar")
            println("4 comprar")
            println("5 vender")
            val choice = readln().toInt()
            when (choice) {
                1 -> {
                    break
                }

                2 -> {
                    CadastrarCaixa(listaDeTeste)
                }

                3 -> listarCaixa()
                4 -> CompraCaixa()
                5 -> VendaCaixa()
            }
        } while (true)
    }
