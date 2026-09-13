package sistema.Login

import sistema.Login.Login

class MenuLogin {
    fun menuLogin() {
        println("ACESSANDO SISTEMA ...")
        println()

        println("""
        PAGINA DE LOGIN
        ====================
        Usuario: *****
        Senha: *****
        ====================
        
        É seu primeiro acesso? tecle "R ou L"
        """.trimIndent())

        val L = Login()
        L.validaLogin()
    }
}