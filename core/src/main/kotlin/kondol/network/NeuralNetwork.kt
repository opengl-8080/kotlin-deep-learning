package kondol.network

import kondol.matrix.Matrix
import kondol.network.layer.AffineLayer
import kondol.network.layer.Layer
import kondol.network.layer.OutputLayer

class NeuralNetwork (
    private val learningRate: Double,
    private val outputLayer: OutputLayer,
    vararg layers: Layer
) {
    
    private val layerList: List<Layer> = layers.toMutableList()
    private val affineLayerList: List<AffineLayer> = layerList.filter { it is AffineLayer }.map { it as AffineLayer }
    
    constructor(
        learningRate: Double,
        outputLayer: OutputLayer,
        layerList: List<Layer>
    ): this(learningRate, outputLayer, *layerList.toTypedArray())

    fun learn(x: Matrix, t: Matrix): Matrix {
        val y = this.predict(x)
        this.loss(y, t)
        this.backward()

        this.affineLayerList.forEach { it.learn(this.learningRate) }
        
        return y
    }

    private fun predict(x: Matrix): Matrix {
        return this.layerList.fold(x) { accumulation, layer -> layer.forward(accumulation)}
    }

    private fun loss(y: Matrix, t: Matrix): Double {
        return this.outputLayer.forward(y, t)
    }
    
    private fun backward() {
        var dL = this.outputLayer.backward(1.0)

        this.layerList.reversed().forEach { layer ->
            dL = layer.backward(dL)
        }
    }
    
    fun accuracy(y: Matrix, t: Matrix): Double {
        val yMaxIndexes = y.maxAtHorizontal()
        val tMaxIndexes = t.maxAtHorizontal()

        val sum = yMaxIndexes.countIfIndexes { rowIndex, colIndex, value -> tMaxIndexes[rowIndex, colIndex] == value }
        return sum.toDouble() / y.rowSize.toDouble()
    }
}
