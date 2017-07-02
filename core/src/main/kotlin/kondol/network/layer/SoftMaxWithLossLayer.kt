package kondol.network.layer

import kondol.matrix.Matrix


class SoftMaxWithLossLayer: OutputLayer {
    
    private var y: Matrix? = null
    private var t: Matrix? = null
    
    override fun forward(x: Matrix, t: Matrix): Double {
        this.t = t
        val y = this.softMax(x)
        this.y = y
        return this.crossEntropyError(y, t)
    }

    override fun backward(dL: Double): Matrix {
        if (this.y == null || this.t == null) {
            throw IllegalStateException("this layer's predict() method is not invoked yet.")
        }
        
        val batchSize = this.t!!.rowSize
        return (this.y!! - this.t!!) / batchSize.toLong()
    }
    
    private fun softMax(matrix: Matrix): Matrix {
        val max = matrix.max()
        val tmp = matrix - max
        val exp = tmp.exp()
        return exp / exp.sum()
    }

    private fun crossEntropyError(y: Matrix, t: Matrix): Double {
        return -(t * (y + 1e-7).log()).sum()
    }
}