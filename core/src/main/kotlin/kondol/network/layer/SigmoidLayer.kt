package kondol.network.layer

import kondol.matrix.Matrix
import kondol.matrix.operators.*


class SigmoidLayer: Layer {
    private var y: Matrix? = null
    
    override fun forward(x: Matrix): Matrix {
        val y = 1.0 / (1.0 + (-x).exp())
        this.y = y
        return y
    }

    override fun backward(dL: Matrix): Matrix {
        if (this.y == null) {
            throw IllegalStateException("this layer's predict() method is not invoked yet.")
        }
        
        return this.y!!.mapWith(dL) {yValue, dLValue ->
            dLValue * yValue * (1 - yValue)
        }
    }
}