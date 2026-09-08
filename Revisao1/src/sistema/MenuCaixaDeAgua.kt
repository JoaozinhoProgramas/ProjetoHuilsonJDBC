package sistema

import produto.CaixaDaAgua
import sistema.Caixa_De_Agua.CadastrarCaixa
import sistema.Caixa_De_Agua.CompraCaixa
import sistema.Caixa_De_Agua.VendaCaixa
import sistema.Caixa_De_Agua.listarCaixa


fun menuCaixaDeAgua() {
    val listaDeTeste = mutableListOf<CaixaDaAgua>()
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
                    CadastrarCaixa()
                }

                3 -> listarCaixa()
                4 -> CompraCaixa()
                5 -> VendaCaixa()
            }
        } while (true)
    }
