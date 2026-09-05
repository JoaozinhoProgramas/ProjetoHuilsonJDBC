package repositorio

import produto.CaixaDaAgua
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

//porta: 5432
//usuario: postgres
//senha: postgres
//banco: NomePikaPraUmBanco
class JPA(
    val user : String = "postgres",
    val password : String = "postgres",
    val url : String = "jdbc:postgresql://localhost:5432/NomePikaPraUmBanco",
    var c :Connection? = null
) {
    fun conectar() {
        try {
            //carregando o driver
            Class.forName("org.postgresql.Driver")
            //estabelecendo conexão
            c = DriverManager.getConnection(url, user, password)
            println("A conexão foi estabelecida com sucesso")
        } catch (e: SQLException) {
            print("Fudeu foi tudo, ERRO: ${e.printStackTrace()}")

        }
    }

    fun salvar(caixaDaAgua: CaixaDaAgua) {
        println("Salvando ...")
        try {
            conectar()
            c!!.createStatement().executeQuery(
                "" +
                        "INSERT INTO tabelaMuitoFoda " +
                        "(marca,modelo,dimensao,cor,material,formato,preco) " +
                        "VALUES(${caixaDaAgua.marca},${caixaDaAgua.modelo},${caixaDaAgua.dimensao},${caixaDaAgua.cor},${caixaDaAgua.material},${caixaDaAgua.formato},${caixaDaAgua.preco}"
            )
            c!!.close()
        } catch (e: SQLException) {
            print("Fudeu foi tudo, ERRO: ${e.printStackTrace()}")

        }
    }
}


