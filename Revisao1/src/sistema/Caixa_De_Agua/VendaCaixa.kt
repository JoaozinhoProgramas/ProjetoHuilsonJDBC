package sistema.Caixa_De_Agua

import repositorio.JDBC
import java.math.BigDecimal

fun VendaCaixa() {
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
        return
    }

    if (saldo == null) {
        println("Saldo não encontrado")
        return
    }

    val quantidadeAtual = jdbc.buscarQuantidade(choiceIDsell)

    if (quantidadeAtual == null) {
        println("Estoque não encontrado para este produto")
        return
    }

    if (choiceQTYsell > quantidadeAtual) {
        println("Estoque insuficiente. Disponível: $quantidadeAtual | Solicitado: $choiceQTYsell")
        return
    }

    val finalValue = preco.multiply(BigDecimal(choiceQTYsell))
    val novaQuantidade = quantidadeAtual - choiceQTYsell

    val estoqueAtualizado = jdbc.enviarQuantidade(choiceIDsell, novaQuantidade)

    if (!estoqueAtualizado) {
        println("Erro ao atualizar o estoque. Transação cancelada.")
        return
    }

    println("Venda aprovada")
    println("Saldo Atual: ${jdbc.atualizarSaldo(finalValue)}")
    println("Novo estoque: $novaQuantidade unidades")
}