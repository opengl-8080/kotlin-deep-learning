package kondol.network

import kondol.matrix.Matrix
import org.assertj.core.api.Assertions
import org.junit.Test

class NeuralNetworkTest {

    @Test
    fun test_neural_network() {
        // setup
        val layer1 = object :Layer {
            override fun forward(x: Matrix) = x + 10
        }
        val layer2 = object :Layer {
            override fun forward(x: Matrix) = x * 2
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