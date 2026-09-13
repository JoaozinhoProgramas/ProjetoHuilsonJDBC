package sistema.Caixa_De_Agua

import repositorio.JDBC
import java.math.BigDecimal
import sistema.Caixa_De_Agua.Transacao.ResultadoTransacao
import sistema.Caixa_De_Agua.Transacao.TipoTransacao

fun VendaCaixa(): ResultadoTransacao? {
    val jdbc = JDBC()
    val saldo = jdbc.buscarSaldo()

    print("---Itens cadastrado---")
    listarCaixa()

    println("Digite o ID do item que irá vender")
    val choiceIDsell = readln().toInt()
    val preco = jdbc.buscarPreco(choiceIDsell)

    println("Digite a quantidade que irá vender")
    val choiceQTYsell = readln().toInt()

    if (preco == null) {
        println("Produto não encontrado")
        return null
    }

    if (saldo == null) {
        println("Saldo não encontrado")
        return null
    }

    val quantidadeAtual = jdbc.buscarQuantidade(choiceIDsell)

    if (quantidadeAtual == null) {
        println("Estoque não encontrado para este produto")
        return null
    }

    if (choiceQTYsell > quantidadeAtual) {
        println("Estoque insuficiente. Disponível: $quantidadeAtual | Solicitado: $choiceQTYsell")
        return null
    }

    val finalValue = preco.multiply(BigDecimal(choiceQTYsell))
    val novaQuantidade = quantidadeAtual - choiceQTYsell

    val estoqueAtualizado = jdbc.enviarQuantidade(choiceIDsell, novaQuantidade)

    if (!estoqueAtualizado) {
        println("Erro ao atualizar o estoque. Transação cancelada.")
        return null
    }

    jdbc.atualizarSaldo(finalValue)

    return ResultadoTransacao(
        tipo = TipoTransacao.VENDA,
        valor = finalValue
    )
}