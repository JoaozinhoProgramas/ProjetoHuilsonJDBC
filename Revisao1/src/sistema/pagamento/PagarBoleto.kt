package sistema.pagamento

import repositorio.JDBCmovimentacao
import java.time.LocalDateTime

fun pagar(){
    println("Digite o contexto: ")
    val contexto = readln()
    println("Digite um valor: ")
    val valor = readln()
    val data = LocalDateTime.now()

    JDBCmovimentacao(contexto, valor, data)
}