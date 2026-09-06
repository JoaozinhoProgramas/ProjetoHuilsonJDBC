package sistema.Caixa_De_Agua
import repositorio.JDBC


fun listarCaixa(){
    val conexao = JDBC()

    if (CadastrarCaixa != null)
    conexao.listar()
}
