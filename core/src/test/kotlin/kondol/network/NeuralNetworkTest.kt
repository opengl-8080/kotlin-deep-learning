package kondol.network

import kondol.matrix.Matrix
import kondol.network.layer.Layer
import org.assertj.core.api.Assertions
import org.junit.Test

class NeuralNetworkTest {

    @Test
    fun test_neural_network() {
        // setup
        val layer1 = object : Layer {
            override fun forward(x: Matrix) = x + 10
            override fun backward(dL: Matrix): Matrix {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
        val layer2 = object : Layer {
            override fun forward(x: Matrix) = x * 2
            override fun backward(dL: Matrix): Matrix {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }

        val neuralNetwork = NeuralNetwork(layer1, layer2)
        val x = Matrix(1, 2, 3)
        
        // exercise
        val actual = neuralNetwork.forward(x)
        
        // verify
        Assertions.assertThat(actual).isEqualTo(Matrix(
            (1+10)*2, (2+10)*2, (3+10)*2
        ))
    }
}