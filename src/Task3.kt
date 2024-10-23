// Абстрактный класс продукта A
abstract class AbstractProductA(protected var info: String) {
    abstract fun A()
    abstract fun retrieveInfo(): String // Переименованный метод
}

// Конкретные продукты типа A
class ProductA1(info: String) : AbstractProductA(info) {
    override fun A() {
        val intValue = info.toIntOrNull() ?: 0
        info = (intValue * 2).toString()
    }

    override fun retrieveInfo(): String {
        return info
    }
}

class ProductA2(info: String) : AbstractProductA(info) {
    override fun A() {
        info = info.repeat(2)
    }

    override fun retrieveInfo(): String {
        return info
    }
}

// Абстрактный класс продукта B
abstract class AbstractProductB(protected var info: String) {
    abstract fun B(pa: AbstractProductA)
    abstract fun retrieveInfo(): String // Переименованный метод
}

// Конкретные продукты типа B
class ProductB1(info: String) : AbstractProductB(info) {
    override fun B(pa: AbstractProductA) {
        val intValueA = pa.retrieveInfo().toIntOrNull() ?: 0
        val intValueB = info.toIntOrNull() ?: 0
        info = (intValueA + intValueB).toString()
    }

    override fun retrieveInfo(): String {
        return info
    }
}

class ProductB2(info: String) : AbstractProductB(info) {
    override fun B(pa: AbstractProductA) {
        info += pa.retrieveInfo()
    }

    override fun retrieveInfo(): String {
        return info
    }
}

// Абстрактная фабрика
abstract class AbstractFactory {
    abstract fun createProductA(Na: Int): AbstractProductA
    abstract fun createProductB(Nb: Int): AbstractProductB
}

// Конкретная фабрика 1
class ConcreteFactory1 : AbstractFactory() {
    override fun createProductA(Na: Int): AbstractProductA {
        return ProductA1(Na.toString())
    }

    override fun createProductB(Nb: Int): AbstractProductB {
        return ProductB1(Nb.toString())
    }
}

// Конкретная фабрика 2
class ConcreteFactory2 : AbstractFactory() {
    override fun createProductA(Na: Int): AbstractProductA {
        return ProductA2(Na.toString())
    }

    override fun createProductB(Nb: Int): AbstractProductB {
        return ProductB2(Nb.toString())
    }
}

// Тестирование
fun main() {
    val factories: List<AbstractFactory> = listOf(ConcreteFactory1(), ConcreteFactory2())

    for (factory in factories) {
        val productA = factory.createProductA(10)
        val productB = factory.createProductB(20)

        productA.A()
        productB.B(productA)

        println("Product A info: ${productA.retrieveInfo()}")
        println("Product B info: ${productB.retrieveInfo()}")
    }
}
