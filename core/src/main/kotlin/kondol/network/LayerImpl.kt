package kondol.network

import kondol.function.ActivationFunction
import kondol.matrix.Matrix

internal class LayerImpl(
    private val weight: Matrix,
    private val bias: Matrix,
    private val activationFunction: ActivationFunction
): Layer {
    
    override fun forward(x: Matrix) = this.activationFunction((x * this.weight) + this.bias)
    
}