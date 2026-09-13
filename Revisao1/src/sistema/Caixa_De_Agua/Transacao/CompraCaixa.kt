package sistema.Caixa_De_Agua

import repositorio.JDBC
import java.math.BigDecimal
import sistema.Caixa_De_Agua.Transacao.ResultadoTransacao
import sistema.Caixa_De_Agua.Transacao.TipoTransacao


fun CompraCaixa(): ResultadoTransacao? {
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
        return null
    }

    if (saldo == null) {
        println("Saldo não encontrado")
        return null
    }

    val finalValue = preco.multiply(BigDecimal(choiceQTYbuy))

    if (finalValue.compareTo(saldo) <= 0) {
        val actualQTY = jdbc.buscarQuantidade(choiceIDbuy)

        if (actualQTY == null) {
            println("Estoque não encontrado para este produto")
            return null
        }

        val novaQuantidade = actualQTY + choiceQTYbuy
        val estoqueAtualizado = jdbc.enviarQuantidade(choiceIDbuy, novaQuantidade)

        if (!estoqueAtualizado) {
            println("Erro ao atualizar o estoque. Transação cancelada.")
            return null
        }

        jdbc.atualizarSaldo(finalValue.negate())

        return ResultadoTransacao(
            tipo = TipoTransacao.COMPRA,
            valor = finalValue
        )

    } else {
        println("Transação não aprovada, saldo insuficiente. Total: $finalValue | Saldo: $saldo")
        return null
    }
}