import produto.CaixaDaAgua
import sistema.Caixa_De_Agua.CadastrarCaixa
import sistema.Caixa_De_Agua.CompraCaixa
import sistema.Funcionarios.Funcionarios
import sistema.menuCaixaDeAgua
import sistema.menuSaldo
import sistema.menuCaixaDeAgua
import java.awt.print.Printable

fun main(){
    println("MENU INCIAL")
    do {
        println("1- Menu Saldo")
        println("2- Menu Caixa")
        println("3 - Menu Pessoas")

        val choice = readln().toInt()

        when(choice) {
            1 -> menuSaldo()
            2 -> menuCaixaDeAgua()
            3 -> Funcionarios().cadastrar()
        }
    }while(true)

}
