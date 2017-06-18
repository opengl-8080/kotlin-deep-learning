package kondol.function

import kondol.matrix.Matrix
import org.assertj.core.api.Assertions
import org.junit.Test

class IdentityFunctionTest {

    @Test
    fun test_identity_function() {
        // setup
        val identityFunction = IdentityFunction()
        val matrix = Matrix(1.0, 2.0, 3.0)
        
        // exercise
        val actual = identityFunction(matrix)
        
        // verify
        Assertions.assertThat(actual).isEqualTo(Matrix(
            1.0, 2.0, 3.0
        ))
    }
}