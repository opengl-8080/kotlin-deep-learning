package kondol.network.layer

import kondol.matrix.Matrix

interface Layer {
    fun forward(x: Matrix): Matrix
    fun backward(dL: Matrix): Matrix
}