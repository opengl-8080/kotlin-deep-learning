package kondol.network

import kondol.function.ActivationFunction
import kondol.matrix.Matrix

class Layer(
    private val weight: Matrix,
    private val bias: Matrix,
    private val activationFunction: ActivationFunction
) {
    
    fun forward(x: Matrix) = this.activationFunction((x * this.weight) + this.bias)
    
}