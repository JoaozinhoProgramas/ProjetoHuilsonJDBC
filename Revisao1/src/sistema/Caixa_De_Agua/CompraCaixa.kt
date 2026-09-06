package sistema.Caixa_De_Agua

import repositorio.JDBC
import java.math.BigDecimal

fun CompraCaixa() {
    val jdbc = JDBC()
    val saldo = jdbc.buscarSaldo()

    print("---Itens cadastrado---")
    listarCaixa()

    println("Digite o ID do item que irá comprar")
    val choiceIDbuy = readln().toInt()
    val preco = jdbc.buscarPreco(choiceIDbuy)

    println("Digite a quantidade que irá comprar")
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

    if (finalValue.compareTo(saldo) <= 0) {
        // Busca a quantidade atual em estoque
        val actualQTY = jdbc.buscarQuantidade(choiceIDbuy)

        if (actualQTY == null) {
            println("Estoque não encontrado para este produto")
            return
        }

        val novaQuantidade = actualQTY + choiceQTYbuy

        val estoqueAtualizado = jdbc.enviarQuantidade(choiceIDbuy, novaQuantidade)

        if (!estoqueAtualizado) {
            println("Erro ao atualizar o estoque. Transação cancelada.")
            return
        }

        println("Transação aprovada")
        println("Saldo Atual: ${jdbc.atualizarSaldo(finalValue.negate())}")
        println("Novo estoque: $novaQuantidade unidades")

    } else {
        println("Transação não aprovada, saldo insuficiente. Total: $finalValue | Saldo: $saldo")
    }
}