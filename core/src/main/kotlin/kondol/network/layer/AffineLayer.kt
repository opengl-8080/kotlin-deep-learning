package kondol.network.layer

import kondol.matrix.Matrix
import kondol.matrix.operators.times

class AffineLayer(
    private var weight: Matrix,
    private var bias: Matrix
): Layer {
    internal var dw: Matrix? = null
        private set

    internal var db: Matrix? = null
        private set
    
    private var x: Matrix? = null
    
    override fun forward(x: Matrix): Matrix {
        this.x = x
        return x.dot(this.weight) + this.bias
    }

    override fun backward(dL: Matrix): Matrix {
        if (this.x == null) {
            throw IllegalStateException("this layer's predict() method is not invoked yet.")
        }
        
        this.dw = this.x!!.transpose().dot(dL)
        this.db = dL.sumVertical()
        return dL.dot(this.weight.transpose())
    }
    
    internal fun learn(learningRate: Double) {
        this.weight = this.weight - (learningRate * this.dw!!)
        this.bias = this.bias - (learningRate * this.db!!)
    }
}
