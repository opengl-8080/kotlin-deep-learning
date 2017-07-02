package kondol.network

import kondol.matrix.Matrix
import kondol.network.layer.Layer
import kondol.network.layer.OutputLayer
import org.assertj.core.api.Assertions
import org.junit.Test

class NeuralNetworkTest {

    val layer1 = object : Layer {
        override fun forward(x: Matrix) = x + 10
        override fun backward(dL: Matrix): Matrix = dL
    }
    val layer2 = object : Layer {
        override fun forward(x: Matrix) = x * 2
        override fun backward(dL: Matrix): Matrix = dL
    }
    val outputLayer = object : OutputLayer {
        override fun forward(x: Matrix, t: Matrix): Double = x.sum() + t.sum()
        override fun backward(dL: Double): Matrix = Matrix(1, 2, 3)
    }
    
    val neuralNetwork = NeuralNetwork(0.01, outputLayer, layer1, layer2)

    @Test
    fun test_accuracy() {
        // setup
        
    }

    @Test
    fun test_loss() {
        // setup
        val t = Matrix(0, 1, 0)
        val x = Matrix(1, 2, 3)

        // exercise
        val actual = neuralNetwork.loss(x, t)

        // verify
        val y = neuralNetwork.predict(x)
        Assertions.assertThat(actual).isEqualTo(outputLayer.forward(y, t))
    }
    
    @Test
    fun test_predict() {
        // setup
        val x = Matrix(1, 2, 3)
        
        // exercise
        val actual = neuralNetwork.predict(x)
        
        // verify
        Assertions.assertThat(actual).isEqualTo(Matrix(
            (1+10)*2, (2+10)*2, (3+10)*2
        ))
    }
}