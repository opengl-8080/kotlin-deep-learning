package kondol.network

import kondol.matrix.Matrix

interface Layer {
    fun forward(x: Matrix): Matrix
}