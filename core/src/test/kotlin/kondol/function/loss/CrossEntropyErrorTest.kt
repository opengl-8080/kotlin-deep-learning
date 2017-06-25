package kondol.function.loss

import kondol.matrix.Matrix
import org.assertj.core.api.Assertions
import org.junit.Test

class CrossEntropyErrorTest {

    @Test
    fun name() {
        // setup
        val y = Matrix(0.1, 0.05, 0.6, 0.0, 0.05, 0.1, 0.0, 0.1, 0.0, 0.0)
        val t = Matrix(0, 0, 1, 0, 0, 0, 0, 0, 0, 0)
        val function = CrossEntropyError()

        // exercise
        val actual = function(y, t)

        // verify
        Assertions.assertThat(actual).isEqualTo(0.51082545709933802)
    }
}