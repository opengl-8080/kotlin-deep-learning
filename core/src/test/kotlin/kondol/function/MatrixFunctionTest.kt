package kondol.function

import kondol.matrix.operators.*
import kondol.matrix.Matrix
import org.assertj.core.api.Assertions
import org.junit.Test

class MatrixFunctionTest {

    @Test
    fun name() {
        // setup
        val f = object: MatrixFunction {
            override fun invoke(matrix: Matrix): Matrix {
                return 0.01 * matrix.pow(2.0) + 0.1 * matrix
            }
        }
        val x = Matrix(5, 10)
        
        // exercise
        val actual = f.numericalDifferential(x)
        
        // verify
        Assertions.assertThat(actual).isEqualTo(Matrix(
            0.1999999999990898, 0.2999999999986347
        ))
    }
}