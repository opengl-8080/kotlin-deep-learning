package kondol.network.layer

import kondol.matrix.Matrix


class AffineLayer(
    private val weight: Matrix,
    private val bias: Matrix
): Layer {
    var dw: Matrix? = null
        private set
    
    var db: Matrix? = null
        private set
    
    private var x: Matrix? = null
    
    override fun forward(x: Matrix): Matrix {
        this.x = x
        return x.dot(this.weight) + this.bias
    }

    override fun backward(dL: Matrix): Matrix {
        if (this.x == null) {
            throw IllegalStateException("this layer's forward() method is not invoked yet.")
        }
        
        this.dw = this.x!!.transpose().dot(dL)
        this.db = dL.sumVertical()
        return dL.dot(this.weight.transpose())
    }
}