package sistema.Caixa_De_Agua.Transacao

import java.math.BigDecimal
import java.time.LocalDateTime

enum class TipoTransacao {
    COMPRA, VENDA
}

data class ResultadoTransacao(
    val tipo: TipoTransacao,
    val valor: BigDecimal,
    val data: LocalDateTime = LocalDateTime.now()
)