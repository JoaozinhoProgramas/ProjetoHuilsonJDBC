package sistema.Caixa_De_Agua

import repositorio.JDBC
import java.math.BigDecimal

fun VendaCaixa() {
    val jdbc = JDBC()
    val saldo = jdbc.buscarSaldo()

    print("---Itens cadastrado---")
    listarCaixa()

    println("Digite o ID do item que irá vender")
    val choiceIDbuy = readln().toInt()
    val preco = jdbc.buscarPreco(choiceIDbuy)

    println("Digite a quantidade que irá vender")
    val choiceQTYbuy = readln().toInt()

    if (preco == null) {
        println("Produto não encontrado")
        return
    }

    if (saldo == null) {
        println("Saldo não encontrado")
        return
    }

    val finalValue = preco.multiply(BigDecimal(choiceQTYbuy))

    if (finalValue.compareTo(saldo) < 0) {
        println("Transação aprovada")
        println("Saldo Atual: ${jdbc.atualizarSaldo(finalValue * (1).toBigDecimal())}")
    } else {
        println("Transação não aprovada, saldo insuficiente. Total: $finalValue | Saldo: $saldo")
    }

}