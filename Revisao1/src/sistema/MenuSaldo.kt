package sistema

import sistema.Caixa_De_Agua.CompraCaixa
import sistema.Caixa_De_Agua.VendaCaixa

fun menuSaldo(){
    do {
        println("1 - Consultar saldo")
        println("2 - Compra")
        println("3 - Venda")
        println("4 - Sair")
        val choice = readLine()!!.toInt()

        when (choice) {
            1 -> {
                println("Função do saldo")
            }

            2 -> CompraCaixa()

            3 -> VendaCaixa()

            4 -> {
                break
            }
        }
    }while (choice != 0)
}