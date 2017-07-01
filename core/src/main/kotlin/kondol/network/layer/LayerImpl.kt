package kondol.network.layer

import kondol.function.activation.ActivationFunction
import kondol.matrix.Matrix

internal class LayerImpl(
        private val weight: Matrix,
        private val bias: Matrix,
        private val activationFunction: ActivationFunction
): Layer {
    override fun backward(dL: Matrix): Matrix {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun forward(x: Matrix) = this.activationFunction((x.dot(this.weight)) + this.bias)
    
}