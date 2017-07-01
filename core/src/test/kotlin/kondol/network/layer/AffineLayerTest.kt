package kondol.network.layer

import kondol.matrix.Matrix
import org.assertj.core.api.Assertions
import org.junit.Test

class AffineLayerTest {

    @Test
    fun test_forward() {
        // setup
        val w = Matrix(
            intArrayOf(1, 2, 4),
            intArrayOf(4, 5, 6)
        )
        val b = Matrix(5, 6, 7)
        val layer = AffineLayer(w, b)
        
        val x = Matrix(1, 2)
        // exercise
        val actual = layer.forward(x)
        
        // verify
        Assertions.assertThat(actual).isEqualTo(Matrix(
            intArrayOf((1*1+2*4)+5, (1*2+2*5)+6, (1*4+2*6)+7)
        ))
    }

    @Test
    fun test_backward() {
        // setup
        val w = Matrix(
            intArrayOf(1, 2, 4),
            intArrayOf(4, 5, 6)
        )
        val b = Matrix(5, 6, 7)
        val layer = AffineLayer(w, b)
        val x = Matrix(1, 2)
        layer.forward(x)
        
        // exercise
        val dL = Matrix(2, 3, 4)
        val actual = layer.backward(dL)
        
        // verify
        Assertions.assertThat(actual).`as`("dx").isEqualTo(Matrix(24, 47))
        Assertions.assertThat(layer.dw).`as`("dw").isEqualTo(Matrix(
            intArrayOf(2, 3, 4),
            intArrayOf(4, 6, 8)
        ))
        Assertions.assertThat(layer.db).`as`("db").isEqualTo(dL)
    }
    
    @Test
    fun test_batch_backward() {
        // setup
        val w = Matrix(
            intArrayOf(1, 2, 4),
            intArrayOf(4, 5, 6)
        )
        val b = Matrix(5, 6, 7)
        val layer = AffineLayer(w, b)
        val x = Matrix(
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        )
        layer.forward(x)

        // exercise
        val dL = Matrix(
            intArrayOf(2, 3, 4),
            intArrayOf(5, 6, 7)
        )
        val actual = layer.backward(dL)

        // verify
        Assertions.assertThat(actual).`as`("dx").isEqualTo(Matrix(
            intArrayOf(24, 47),
            intArrayOf(45, 92)
        ))
        Assertions.assertThat(layer.dw).`as`("dw").isEqualTo(Matrix(
            intArrayOf(17, 21, 25),
            intArrayOf(24, 30, 36)
        ))
        Assertions.assertThat(layer.db).`as`("db").isEqualTo(Matrix(
            7, 9, 11
        ))
    }

    @Test
    fun throw_exception_if_backward_is_invoked_without_forward() {
        // setup
        val w = Matrix(
            intArrayOf(1, 2, 4),
            intArrayOf(4, 5, 6)
        )
        val b = Matrix(5, 6, 7)
        val layer = AffineLayer(w, b)
        val dL = Matrix(2, 3, 4)

        // exercise, verify
        Assertions.assertThatThrownBy { layer.backward(dL) }
                .isInstanceOf(IllegalStateException::class.java)
                .hasMessage("this layer's forward() method is not invoked yet.")
    }
}
