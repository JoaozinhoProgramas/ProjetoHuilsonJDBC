package sistema.Caixa_De_Agua

import enumeradores.Cor
import enumeradores.Material
import produto.CaixaDaAgua
import repositorio.JDBC

fun CadastrarCaixa(){
    println("Digite a marca: (STR)")
    var marca = readln()

    println("Digite a modelo: (STR)")
    var modelo = readln()

    println("Digite a largura: (DOUBLE)")
    var largura = readln().toDouble()

    println("Digite a altura: (DOUBLE)")
    var altura = readln().toDouble()

    println("Digite a profundidade: (DOUBLE)")
    var profund = readln().toDouble()

    val dimensao = mutableListOf<Double>(largura,altura,profund)

    println("Digite a cor: (INT)")
    Cor.entries.forEach { cor ->
        println("${cor.ordinal} - ${cor.name}")
    }
    var cor = readln().toInt()

    println("Digite o material: (INT)")
    Material.entries.forEach { material ->
        println("${material.ordinal} - ${material.name}")
    }
    var material = readln().toInt()

    println("Digite o formato: (STR)")
    var formato = readln()

    println("Digite o preço: ")
    var preco = readln().toBigDecimal()

    val conexao = JDBC()
    conexao.salvar(
        CaixaDaAgua(
            marca = marca,
            modelo = modelo,
            dimensao = dimensao,
            cor = Cor.entries[cor],
            material = Material.entries[material],
            formato = formato,
            preco = preco
        )
    )
}
