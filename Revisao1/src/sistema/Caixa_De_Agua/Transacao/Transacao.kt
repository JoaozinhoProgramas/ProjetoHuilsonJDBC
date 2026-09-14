package sistema.Caixa_De_Agua.Transacao

import sistema.Login.Login
import sistema.Caixa_De_Agua.VendaCaixa
import sistema.Caixa_De_Agua.CompraCaixa
import java.time.format.DateTimeFormatter
import repositorio.JDBC

class Transacao {
    fun montarTransacao(tipo: TipoTransacao) {
        val login = Login()
        val usuario = login.validaLogin()  // pega o return do login

        val resultado = when (tipo) {
            TipoTransacao.COMPRA -> CompraCaixa()
            TipoTransacao.VENDA -> VendaCaixa()
        }

        if (resultado == null) {
            println("Transação não concluída.")
            return
        }

        // A partir daqui, o Kotlin faz "smart cast":
        // resultado passa a ser tratado como ResultadoTransacao (não-nulo)
        val dataFormatada = resultado.data.format(
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
        )

        println("""
        ={ TRANSAÇÃO EFETUADA }=
        Responsável: ${usuario}
        Tipo: ${resultado.tipo}
        Valor: R$ ${resultado.valor}
        Data: $dataFormatada
        =======================
        """.trimIndent())
        println()

        val conexao = JDBC()
        conexao.salvarTransacao(
            montarTransacao(
                responsavel = usuario,
                tipo = resultado.tipo,
                valor = resultado.valor,
                data = dataFormatada
            )

            )
    }
}