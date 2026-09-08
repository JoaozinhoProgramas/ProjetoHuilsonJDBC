package sistema.Caixa_De_Agua
import repositorio.JDBC


fun listarCaixa(){
    val conexao = JDBC()
    conexao.listar()
}
