package kondol.network

import kondol.function.ActivationFunction
import kondol.matrix.Matrix

interface Layer {
    fun forward(x: Matrix): Matrix
    
    companion object {
        fun of(weight: Matrix, bias: Matrix, activationFunction: ActivationFunction): Layer
            = LayerImpl(weight, bias, activationFunction)
    }
}