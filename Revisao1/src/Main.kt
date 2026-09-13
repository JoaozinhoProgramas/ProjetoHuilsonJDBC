import sistema.menuCaixaDeAgua
import sistema.menuPessoas
import sistema.menuSaldo
import sistema.Login.Login
import sistema.Login.MenuLogin

fun main(){
    val L = MenuLogin()
    L.menuLogin()
    println("MENU INCIAL")
    do {
        println("1- Menu Saldo")
        println("2- Menu Caixa")
        println("3- Menu Pessoas")

        val choice = readln().toInt()

        when(choice) {
            1 -> menuSaldo()
            2 -> menuCaixaDeAgua()
            3 -> menuPessoas()
        }
    }while(true)

}
