// Абстрактный класс Продукта
abstract class Product {
    abstract fun getInfo(): String
    abstract fun transform()
}

// Конкретный Продукт 1
class ConcreteProduct1(private var info: String) : Product() {
    init {
        info = info.lowercase()
    }

    override fun getInfo(): String {
        return info
    }

    override fun transform() {
        info = info.map { if (it != ' ') "$it " else "$it" }.joinToString("")
    }
}

// Конкретный Продукт 2
class ConcreteProduct2(private var info: String) : Product() {
    init {
        info = info.uppercase()
    }

    override fun getInfo(): String {
        return info
    }

    override fun transform() {
        info = info.map { if (it != '*') "$it**" else "$it" }.joinToString("")
    }
}

// Абстрактный Креатор
abstract class Creator {
    abstract fun factoryMethod(info: String): Product

    fun anOperation(info: String): String {
        val product = factoryMethod(info)
        product.transform()
        product.transform()
        return product.getInfo()
    }
}

// Конкретный Креатор 1
class ConcreteCreator1 : Creator() {
    override fun factoryMethod(info: String): Product {
        return ConcreteProduct1(info)
    }
}

// Конкретный Креатор 2
class ConcreteCreator2 : Creator() {
    override fun factoryMethod(info: String): Product {
        return ConcreteProduct2(info)
    }
}

// Тестирование
fun main() {
    val creators: List<Creator> = listOf(ConcreteCreator1(), ConcreteCreator2())
    val testStrings = listOf("Hello", "World", "Factory", "Method", "Pattern")

    for (creator in creators) {
        for (str in testStrings) {
            println(creator.anOperation(str))
        }
    }
}
