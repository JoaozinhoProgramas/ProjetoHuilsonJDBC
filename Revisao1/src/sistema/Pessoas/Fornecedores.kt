package sistema.Pessoas

class Forncedores {
    fun cadastrar() {
        println("Cadastro de Fornecedores")

        val validaNome = Regex("^[a-zA-Z0-9]+( [a-zA-Z0-9]+)*$")
        val validaCNPJ = Regex("^(\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}|\\d{14})$")

        while (true) {
            println("Digite o nome do fornecedor")
            val nome = readln()

            if (validaNome.matches(nome)) {
                println("Nome cadastrado com sucesso")
                println("Nome: $nome")
                break
            }else {
                println("Nome invalido, tente novamente")
            }
        }

        while (true){
            println("Digite o CNPJ do fornecedor")
            val CNPJ = readln()

            if (validaCNPJ.matches(CNPJ)) {
                println("CNPJ cadastrado com sucesso")
                println("CNPJ: $CNPJ")
                break
            } else {
                println("CNPJ invalido, tente novamente")
            }
        }
    }
}

