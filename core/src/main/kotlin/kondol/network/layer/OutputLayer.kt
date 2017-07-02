package kondol.network.layer

import kondol.matrix.Matrix

interface OutputLayer {
    fun forward(x: Matrix, t: Matrix): Double
    fun backward(dL: Double): Matrix
}
