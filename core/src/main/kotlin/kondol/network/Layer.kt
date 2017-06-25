package kondol.network

import kondol.function.activation.ActivationFunction
import kondol.matrix.Matrix

interface Layer {
    fun forward(x: Matrix): Matrix
    
    companion object {
        fun of(weight: Matrix, bias: Matrix, activationFunction: ActivationFunction): Layer
            = LayerImpl(weight, bias, activationFunction)
    }
}