package repositorio

import produto.CaixaDaAgua
import sistema.Caixa_De_Agua.Transacao.Transacao
import sistema.Login.User
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.math.BigDecimal

//LEMBRAR DE ORGANIZAR ISSO AQUI DPS, TA UMA BAGUNÇA

//porta: 5432
//usuario: postgres
//senha: postgres
//banco: ProjetoHuilson
open class JDBC(
    val user : String = "postgres",
    val password : String = "postgres",
    val url : String = "jdbc:postgresql://localhost:5432/ProjetoHuilson",
    var c :Connection? = null
) {
    fun conectar() {
        try {
            //carregando o driver
            Class.forName("org.postgresql.Driver")
            //estabelecendo conexão
            c = DriverManager.getConnection(url, user, password)
            //println("AVISO: A conexão com o BD foi estabelecida com sucesso") toda vez q conecta com o BD, roda esse print
        } catch (e: SQLException) {
            print("Fudeu foi tudo, ERRO: ${e.printStackTrace()}")

        }
    }

    fun salvar(caixaDaAgua: CaixaDaAgua) {
        println("Salvando ...")
        try {
            conectar()
            val sql = """
            INSERT INTO CAIXA_DE_AGUA 
            (marca, modelo, dimensao, cor, material, formato, preco) 
            VALUES (?, ?, ?, ?, ?, ?, ?);
            
        """.trimIndent()

            val stmt = c!!.prepareStatement(sql)

            stmt.setString(1, caixaDaAgua.marca)
            stmt.setString(2, caixaDaAgua.modelo)
            stmt.setArray(3, c!!.createArrayOf("float8", caixaDaAgua.dimensao.toTypedArray()))
            stmt.setString(4, caixaDaAgua.cor.name)
            stmt.setString(5, caixaDaAgua.material.name)
            stmt.setString(6, caixaDaAgua.formato)
            stmt.setBigDecimal(7, caixaDaAgua.preco)

            stmt.executeUpdate()
            stmt.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            c?.close()
        }
    }

    fun listar() {
        try {
            conectar()
            val stmt = c!!.createStatement()

            val sql = "SELECT * from CAIXA_DE_AGUA"
            //metadados vem em forma de lista, ResultSet
            val metadados = stmt.executeQuery(sql)

            val resultado = metadados.metaData // Metadados
            val tamanhoTabela = resultado.columnCount//Tamanho da tabela em colunas

            while (metadados.next()) {
                for (i in 1..tamanhoTabela) {
                    //nome da coluna
                    val nomeColuna = resultado.getColumnName(i)
                    //dado que esta na coluna
                    val valorColuna = metadados.getObject(i)
                    println("$nomeColuna -> $valorColuna")
                } // fim for
                println("---------------------------------------------------")
            }//fim while


            stmt.close()
            c!!.close()

        } catch (e: SQLException) {
            println(e.printStackTrace())
        }

    }//Fim listar

    fun editar(caixa: CaixaDaAgua, id: Int) {
        try {
            conectar()
            val sql =
                "UPDATE CAIXA_DE_AGUA SET preco = ?, marca = ?, modelo = ?, formato = ?, cor = ?, material = ?, dimensao = ? WHERE id = ?"
            //Continuar a logica para os outros itens

            val stmt = c!!.prepareStatement(sql)

            val doublePrecision = c!!.createArrayOf("float8", caixa.dimensao.toTypedArray())

            stmt.setBigDecimal(1, caixa.preco)
            stmt.setString(2, caixa.marca)
            stmt.setString(3, caixa.modelo)
            stmt.setString(4, caixa.formato)
            stmt.setString(5, caixa.cor.name)
            stmt.setString(6, caixa.material.name)
            stmt.setArray(7, doublePrecision)
            stmt.setInt(8, id)

            stmt.executeUpdate()//Faz as alterações e manda pro banco

            stmt.close()
            c!!.close()

        } catch (e: SQLException) {
            println(e.printStackTrace())
        }

    }

    fun excluir(id: Int) {
        try {
            conectar()
            val sql = "DELETE FROM CAIXA_DE_AGUA WHERE id = ?"
            val stmt = c!!.prepareStatement(sql)
            stmt.setInt(1, id)
            stmt.executeUpdate()

            c!!.close()


        } catch (e: SQLException) {
            println(e.printStackTrace())
        }
    }

    fun buscarPreco(id: Int): BigDecimal? {
        var preco: BigDecimal? = null
        try {
            conectar()

            val sql = "SELECT preco FROM CAIXA_DE_AGUA WHERE id = ?"
            val stmt = c!!.prepareStatement(sql)
            stmt.setInt(1, id)

            val rs = stmt.executeQuery()

            if (rs.next()) {
                preco = rs.getBigDecimal("preco")
            }

            rs.close()
            stmt.close()

        } catch (e: SQLException) {
            println(e.printStackTrace())
        } finally {
            c?.close()
        }
        return preco
    }

    fun buscarSaldo(): BigDecimal? {
        var saldo: BigDecimal? = null
        try {
            conectar()

            val sql = "SELECT valor_saldo FROM SALDO LIMIT 1"
            val stmt = c!!.prepareStatement(sql)

            val rs = stmt.executeQuery()
            if (rs.next()) {
                saldo = rs.getBigDecimal("valor_saldo")
            }

            rs.close()
            stmt.close()

        } catch (e: SQLException) {
            println(e.printStackTrace())
        } finally {
            c?.close()
        }
        return saldo
    }

    fun atualizarSaldo(valor: BigDecimal): BigDecimal? {
        try {
            conectar()
            val sql = "UPDATE saldo SET valor_saldo = valor_saldo + ? WHERE id = 1 RETURNING valor_saldo"
            val smt = c!!.prepareStatement(sql)
            smt.setBigDecimal(1, valor)
            val rs = smt.executeQuery()
            rs.next()
            val saldo = rs.getBigDecimal("valor_saldo")
            smt.close()
            return saldo

        } catch (e: SQLException) {
            println(e.printStackTrace())
        } finally {
            c?.close()
        }

        return null
    }

    fun buscarQuantidade(id: Int): Int? {
        conectar()
        try {
            val sql = "SELECT quantidade FROM ESTOQUE_CAIXA_DE_AGUA WHERE id = ?"
            val stmt = c!!.prepareStatement(sql)
            stmt.setInt(1, id)
            val rs = stmt.executeQuery()

            return if (rs.next()) rs.getInt("quantidade") else null

        } catch (e: SQLException) {
            e.printStackTrace()
            return null
        } finally {
            c?.close()
        }
    }

    fun enviarQuantidade(id: Int, quantidade: Int): Boolean {
        conectar()
        try {
            val sql = "UPDATE ESTOQUE_CAIXA_DE_AGUA SET quantidade = ? WHERE id = ?"
            val stmt = c!!.prepareStatement(sql)
            stmt.setInt(1, quantidade)
            stmt.setInt(2, id)

            return stmt.executeUpdate() > 0

        } catch (e: SQLException) {
            e.printStackTrace()
            return false
        } finally {
            c?.close()
        }
    }

    fun buscarLoginPorUsuario(usuario: String): Credencial? {
        conectar()
        try {
            val sql = "SELECT USUARIO, SENHA FROM CREDENCIAIS WHERE usuario = ?"
            val stmt = c!!.prepareStatement(sql)
            stmt.setString(1, usuario)
            val rs = stmt.executeQuery()

            return if (rs.next()) {
                Credencial(
                    usuario = rs.getString("USUARIO"),
                    senha = rs.getString("SENHA")
                )
            } else null

        } catch (e: SQLException) {
            e.printStackTrace()
            return null
        } finally {
            c?.close()
        }

    }

    fun cadastrarUsuario(usuario: User): Credencial? {
        conectar()
        try {
            val sql = """
            INSERT INTO CREDENCIAIS
            (usuario, senha)
            VALUES (?, ?)
        """.trimIndent()

            val stmt = c!!.prepareStatement(sql)

            stmt.setString(1, usuario.usuario)
            stmt.setString(2, usuario.senha)

            stmt.executeUpdate()
            stmt.close()

            return Credencial(usuario.usuario, usuario.senha)

        } catch (e: SQLException) {
            e.printStackTrace()
            return null
        } finally {
            c?.close()
        }
    }

    fun salvarTransacao(transacao : Transacao) {
        try {
            conectar()
            val sql = """
                INSERT INTO TRANSACAO
                (responsável, tipo, valor, data)
                VALUES (?, ?, ?, ?)
            """.trimIndent()
        }catch (e: SQLException) {
            println(e.printStackTrace())
        }
    }

    data class Credencial(val usuario: String, val senha: String) {


    }
}




