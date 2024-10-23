// Класс ConcreteProduct1
open class ConcreteProduct1_2(private var info: String) {
    init {
        info = info.lowercase()
    }

    fun getInfo(): String {
        return info
    }

    open fun transform() {
        info = info.map { if (it != '*') "$it*" else "$it" }.joinToString("")
    }
}

// Класс ConcreteProduct2, наследующий ConcreteProduct1
class ConcreteProduct2_2(info: String) : ConcreteProduct1_2(info) {
    init {
        info.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
    }

    override fun transform() {
        super.transform()
        "==${getInfo()}=="
    }
}

// Класс ConcreteCreator1
open class ConcreteCreator1_2 {
    open fun factoryMethod(info: String): ConcreteProduct1_2 {
        return ConcreteProduct1_2(info)
    }

    fun anOperation(info: String): String {
        val product = factoryMethod(info)
        product.transform()
        product.transform()
        return product.getInfo()
    }
}

// Класс ConcreteCreator2, наследующий ConcreteCreator1
class ConcreteCreator2_2 : ConcreteCreator1_2() {
    override fun factoryMethod(info: String): ConcreteProduct1_2 {
        return ConcreteProduct2_2(info)
    }
}

// Тестирование
fun main() {
    val creators: List<ConcreteCreator1_2> = listOf(ConcreteCreator1_2(), ConcreteCreator2_2())
    val testStrings = listOf("Hello", "World", "Kotlin", "IntelliJ", "Factory")

    for (creator in creators) {
        for (str in testStrings) {
            println(creator.anOperation(str))
        }
    }
}
