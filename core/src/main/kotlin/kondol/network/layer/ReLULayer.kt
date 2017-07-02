package kondol.network.layer

import kondol.matrix.Matrix


class ReLULayer: Layer {

    private var x: Matrix? = null
    
    override fun forward(x: Matrix): Matrix {
        this.x = x
        return x.map { if (it <= 0.0) 0.0 else it }
    }
    
    override fun backward(dL: Matrix): Matrix {
        if (this.x == null) {
            throw IllegalStateException("this layer's predict() method is not invoked yet.")
        }
        
        return this.x!!.mapWith(dL) { xValue, dLValue ->
            if (xValue <= 0.0) {
                0.0
            } else {
                dLValue
            }
        }
    }
}