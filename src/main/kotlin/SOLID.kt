
// Dependency inversion

fun main(args: Array<String>) {
    val keyboard = Keyboard(true)
    keyboard.keyBoard.getKeyboardLayout()///.println()
}

class Keyboard(isWired: Boolean = false) {
    val keyBoard = if (isWired) WiredKeyboard() else WirelessKeyboard()
}

class Mouse(isWired: Boolean = false) {
    val mouse = if(isWired) WiredMouse() else WirelessMouse()
}

class WiredKeyboard: IKeyboardLayout {
    override fun getKeyboardType(): Int {
        return 1 //wired keyboard
    }

    override fun getKeyboardLayout(): String {
        return "qwerty"
    }

}

class WirelessKeyboard: IKeyboardLayout {
    override fun getKeyboardType(): Int {
        return 2 //wireless keyboard
    }

}

class WiredMouse : IMouse {
    override fun getMouseType(): Int {
        return 3// wired mouse
    }
}

class WirelessMouse : IMouse {
    override fun getMouseType(): Int {
        return 4 // wireless mouse
    }
}

interface IKeyboard {
    fun getKeyboardType(): Int
}

interface IKeyboardLayout: IKeyboard {
    fun getKeyboardLayout(): String = "none"
}

interface IMouse {
    fun getMouseType(): Int
}

open class Vehicle {
    open fun getInteriorWidth(): Double {
        return 0.0
    }
}

class Car: Vehicle() {

    override fun getInteriorWidth(): Double {
        return getCabinWidth()
    }

    private fun getCabinWidth() = 10.5
}

class RacingCar: Vehicle() {
    override fun getInteriorWidth(): Double {
        return this.getCockpitWidth()
    }

    private fun getCockpitWidth(): Double {
        return 10.67
    }
}

