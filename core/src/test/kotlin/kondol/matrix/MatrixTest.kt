package kondol.matrix

import kondol.matrix.operators.div
import kondol.matrix.operators.minus
import kondol.matrix.operators.plus
import kondol.matrix.operators.times
import org.assertj.core.api.Assertions
import org.junit.Test

class MatrixTest {

    @Test
    fun test_transpose() {
        // setup
        val matrix = Matrix(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6)
        )
        
        // exercise
        val transposed = matrix.transpose()
        
        // verify
        Assertions.assertThat(transposed).isEqualTo(Matrix(
            intArrayOf(1, 4),
            intArrayOf(2, 5),
            intArrayOf(3, 6)
        ))
    }

    @Test
    fun test_pow() {
        // setup
        val matrix = Matrix(1, 2, 3)

        // exercise
        val actual = matrix.pow(3)

        // verify
        Assertions.assertThat(actual).isEqualTo(Matrix(1, 8, 27))
    }

    @Test
    fun test_mutable_by_copy_option_false() {
        // setup
        val rows = arrayOf(doubleArrayOf(1.0, 2.0, 3.0))

        val matrix = Matrix(false, *rows)

        // exercise
        rows[0][0] = 10.0

        // verify
        val after = matrix[0, 0]

        Assertions.assertThat(after).isEqualTo(10.0)
    }

    @Test
    fun test_immutable() {
        // setup
        val rows = arrayOf(doubleArrayOf(1.0, 2.0, 3.0))

        val matrix = Matrix(*rows)
        
        val before = matrix[0, 0]
        
        // exercise
        rows[0][0] = 10.0

        // verify
        val after = matrix[0, 0]
        
        Assertions.assertThat(after).isEqualTo(before)
    }

    @Test
    fun test_immutable_int_constructor() {
        // setup
        val rows = arrayOf(intArrayOf(1, 2, 3))

        val matrix = Matrix(*rows)

        val before = matrix[0, 0]

        // exercise
        rows[0][0] = 10

        // verify
        val after = matrix[0, 0]

        Assertions.assertThat(after).isEqualTo(before)
    }

    @Test
    fun test_max() {
        // setup
        val matrix = Matrix(
            doubleArrayOf(1.0, 2.0),
            doubleArrayOf(3.0, 4.0)
        )

        // exercise
        val actual = matrix.max()

        // verify
        Assertions.assertThat(actual).isEqualTo(4.0)
    }

    @Test
    fun test_sum() {
        // setup
        val matrix = Matrix(
            doubleArrayOf(1.0, 2.0),
            doubleArrayOf(3.0, 4.0)
        )
        
        // exercise
        val actual = matrix.sum()
        
        // verify
        Assertions.assertThat(actual).isEqualTo(1.0 + 2.0 + 3.0 + 4.0)
    }

    @Test
    fun test_toString() {
        // setup
        val matrix = Matrix(
            doubleArrayOf(1.0, 2.0, 3.0),
            doubleArrayOf(4.0, 5.0, 6.0),
            doubleArrayOf(7.0, 8.0, 9.0)
        )
        
        // exercise
        val string = matrix.toString()
        
        // verify
        Assertions.assertThat(string).isEqualTo("""
        |[1.0, 2.0, 3.0]
        |[4.0, 5.0, 6.0]
        |[7.0, 8.0, 9.0]""".trimMargin())
    }

    @Test
    fun test_equals_equally_same_shape() {
        // setup
        val a = Matrix(
            doubleArrayOf(1.0, 2.0, 3.0),
            doubleArrayOf(4.0, 5.0, 6.0)
        )
        val b = Matrix(
            doubleArrayOf(1.0, 2.0, 3.0),
            doubleArrayOf(4.0, 5.0, 6.0)
        )
        
        // exercise
        val actual = a == b
        
        // verify
        Assertions.assertThat(actual).isTrue()
    }

    @Test
    fun test_equals_not_equally_different_shape_1() {
        // setup
        val a = Matrix(
            doubleArrayOf(1.0, 2.0, 3.0),
            doubleArrayOf(4.0, 5.0, 6.0)
        )
        val b = Matrix(
            doubleArrayOf(1.0, 2.0),
            doubleArrayOf(4.0, 5.0)
        )

        // exercise
        val actual = a == b

        // verify
        Assertions.assertThat(actual).isFalse()
    }

    @Test
    fun test_equals_not_equally_different_shape_2() {
        // setup
        val a = Matrix(
            doubleArrayOf(1.0, 2.0, 3.0),
            doubleArrayOf(4.0, 5.0, 6.0)
        )
        val b = Matrix(
            doubleArrayOf(1.0, 2.0, 3.0)
        )

        // exercise
        val actual = a == b

        // verify
        Assertions.assertThat(actual).isFalse()
    }

    @Test
    fun test_equals_not_equally_different_shape_3() {
        // setup
        val a = Matrix(
            doubleArrayOf(1.0, 2.0, 3.0, 4.0),
            doubleArrayOf(4.0, 5.0, 6.0, 7.0)
        )
        val b = Matrix(
            doubleArrayOf(1.0, 2.0, 3.0),
            doubleArrayOf(4.0, 5.0, 6.0)
        )

        // exercise
        val actual = a == b

        // verify
        Assertions.assertThat(actual).isFalse()
    }

    @Test
    fun test_equals_not_equally_null() {
        // setup
        val a = Matrix(
            doubleArrayOf(1.0, 2.0, 3.0, 4.0),
            doubleArrayOf(4.0, 5.0, 6.0, 7.0)
        )

        // exercise
        @Suppress("SENSELESS_COMPARISON")
        val actual = a == null

        // verify
        Assertions.assertThat(actual).isFalse()
    }

    @Test
    fun test_rowSize() {
        // setup
        val matrix = Matrix(
            doubleArrayOf(1.0, 2.0, 3.0, 4.0),
            doubleArrayOf(4.0, 5.0, 6.0, 7.0)
        )
        
        // verify
        Assertions.assertThat(matrix.rowSize).isEqualTo(2)
    }

    @Test
    fun test_colSize() {
        // setup
        val matrix = Matrix(
            doubleArrayOf(1.0, 2.0, 3.0, 4.0),
            doubleArrayOf(4.0, 5.0, 6.0, 7.0)
        )

        // verify
        Assertions.assertThat(matrix.colSize).isEqualTo(4)
    }

    @Test
    fun test_constructor_validates_that_each_row_has_same_length() {
        Assertions.assertThatThrownBy { 
            Matrix(
                doubleArrayOf(1.0, 2.0),
                doubleArrayOf(1.0, 2.0, 3.0)
            )
        }
        .isInstanceOf(IllegalArgumentException::class.java)
        .hasMessage("Expected length is 2. However row[1].length is 3.")
    }

    @Test
    fun test_constructor_validates_row_is_not_empty() {
        Assertions.assertThatThrownBy {
            Matrix(doubleArrayOf())
        }
        .isInstanceOf(IllegalArgumentException::class.java)
        .hasMessage("row must have any elements.")
    }

    @Test
    fun test_A_matrix_has_a_single_row_can_be_constructed_by_raw_double_arguments() {
        // exercise
        val matrix = Matrix(1.0, 2.0, 3.0)
        
        // verify
        Assertions.assertThat(matrix).isEqualTo(Matrix(
            doubleArrayOf(1.0, 2.0, 3.0)
        ))
    }

    @Test
    fun test_A_matrix_can_be_constructed_by_IntArray() {
        // exercise
        val matrix = Matrix(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6)
        )

        // verify
        Assertions.assertThat(matrix).isEqualTo(Matrix(
            doubleArrayOf(1.0, 2.0, 3.0),
            doubleArrayOf(4.0, 5.0, 6.0)
        ))
    }

    @Test
    fun test_A_matrix_has_a_single_row_can_be_constructed_by_raw_int_arguments() {
        // exercise
        val matrix = Matrix(1, 2, 3)

        // verify
        Assertions.assertThat(matrix).isEqualTo(Matrix(
            doubleArrayOf(1.0, 2.0, 3.0)
        ))
    }

    @Test
    fun test_operator_overloading_plus_Long() {
        // setup
        val matrix = Matrix(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6)
        )
        
        // verify
        Assertions.assertThat(matrix + 3L).isEqualTo(Matrix(
            intArrayOf(4, 5, 6),
            intArrayOf(7, 8, 9)
        ))
    }

    @Test
    fun test_operator_overloading_minus_Long() {
        // setup
        val matrix = Matrix(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6)
        )

        // verify
        Assertions.assertThat(matrix - 3L).isEqualTo(Matrix(
            intArrayOf(-2, -1, 0),
            intArrayOf(1, 2, 3)
        ))
    }

    @Test
    fun test_operator_overloading_times_Long() {
        // setup
        val matrix = Matrix(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6)
        )

        // verify
        Assertions.assertThat(matrix * 3L).isEqualTo(Matrix(
            intArrayOf(3, 6, 9),
            intArrayOf(12, 15, 18)
        ))
    }

    @Test
    fun test_operator_overloading_div_Long() {
        // setup
        val matrix = Matrix(
            intArrayOf(3, 15),
            intArrayOf(9, 30)
        )

        // verify
        Assertions.assertThat(matrix / 3L).isEqualTo(Matrix(
            intArrayOf(1, 5),
            intArrayOf(3, 10)
        ))
    }

    @Test
    fun test_operator_overloading_plus_Double() {
        // setup
        val matrix = Matrix(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6)
        )

        // verify
        Assertions.assertThat(matrix + 3.0).isEqualTo(Matrix(
                intArrayOf(4, 5, 6),
                intArrayOf(7, 8, 9)
        ))
    }

    @Test
    fun test_operator_overloading_minus_Double() {
        // setup
        val matrix = Matrix(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6)
        )

        // verify
        Assertions.assertThat(matrix - 3.0).isEqualTo(Matrix(
                intArrayOf(-2, -1, 0),
                intArrayOf(1, 2, 3)
        ))
    }

    @Test
    fun test_operator_overloading_times_Double() {
        // setup
        val matrix = Matrix(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6)
        )

        // verify
        Assertions.assertThat(matrix * 3.0).isEqualTo(Matrix(
                intArrayOf(3, 6, 9),
                intArrayOf(12, 15, 18)
        ))
    }

    @Test
    fun test_operator_overloading_div_Double() {
        // setup
        val matrix = Matrix(
                intArrayOf(3, 15),
                intArrayOf(9, 30)
        )

        // verify
        Assertions.assertThat(matrix / 3.0).isEqualTo(Matrix(
                intArrayOf(1, 5),
                intArrayOf(3, 10)
        ))
    }
    
    @Test
    fun test_operator_overloading_Double_plus() {
        // setup
        val matrix = Matrix(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6)
        )

        // verify
        Assertions.assertThat(3.0 + matrix).isEqualTo(Matrix(
                intArrayOf(4, 5, 6),
                intArrayOf(7, 8, 9)
        ))
    }

    @Test
    fun test_operator_overloading_Double_minus() {
        // setup
        val matrix = Matrix(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6)
        )

        // verify
        Assertions.assertThat(3.0 - matrix).isEqualTo(Matrix(
                intArrayOf(2, 1, 0),
                intArrayOf(-1, -2, -3)
        ))
    }

    @Test
    fun test_operator_overloading_Double_times() {
        // setup
        val matrix = Matrix(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6)
        )

        // verify
        Assertions.assertThat(3.0 * matrix).isEqualTo(Matrix(
                intArrayOf(3, 6, 9),
                intArrayOf(12, 15, 18)
        ))
    }

    @Test
    fun test_operator_overloading_Double_div() {
        // setup
        val matrix = Matrix(
                intArrayOf(3, 4),
                intArrayOf(6, 2)
        )

        // verify
        Assertions.assertThat(12.0 / matrix).isEqualTo(Matrix(
                intArrayOf(4, 3),
                intArrayOf(2, 6)
        ))
    }

    @Test
    fun test_operator_overloading_Long_plus() {
        // setup
        val matrix = Matrix(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6)
        )

        // verify
        Assertions.assertThat(3L + matrix).isEqualTo(Matrix(
                intArrayOf(4, 5, 6),
                intArrayOf(7, 8, 9)
        ))
    }

    @Test
    fun test_operator_overloading_Long_minus() {
        // setup
        val matrix = Matrix(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6)
        )

        // verify
        Assertions.assertThat(3L - matrix).isEqualTo(Matrix(
                intArrayOf(2, 1, 0),
                intArrayOf(-1, -2, -3)
        ))
    }

    @Test
    fun test_operator_overloading_Long_times() {
        // setup
        val matrix = Matrix(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6)
        )

        // verify
        Assertions.assertThat(3L * matrix).isEqualTo(Matrix(
                intArrayOf(3, 6, 9),
                intArrayOf(12, 15, 18)
        ))
    }

    @Test
    fun test_operator_overloading_Long_div() {
        // setup
        val matrix = Matrix(
                intArrayOf(3, 4),
                intArrayOf(6, 2)
        )

        // verify
        Assertions.assertThat(12L / matrix).isEqualTo(Matrix(
                intArrayOf(4, 3),
                intArrayOf(2, 6)
        ))
    }

    @Test
    fun test_operator_overloading_Int_plus() {
        // setup
        val matrix = Matrix(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6)
        )

        // verify
        Assertions.assertThat(3 + matrix).isEqualTo(Matrix(
                intArrayOf(4, 5, 6),
                intArrayOf(7, 8, 9)
        ))
    }

    @Test
    fun test_operator_overloading_Int_minus() {
        // setup
        val matrix = Matrix(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6)
        )

        // verify
        Assertions.assertThat(3 - matrix).isEqualTo(Matrix(
                intArrayOf(2, 1, 0),
                intArrayOf(-1, -2, -3)
        ))
    }

    @Test
    fun test_operator_overloading_Int_times() {
        // setup
        val matrix = Matrix(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6)
        )

        // verify
        Assertions.assertThat(3 * matrix).isEqualTo(Matrix(
                intArrayOf(3, 6, 9),
                intArrayOf(12, 15, 18)
        ))
    }

    @Test
    fun test_operator_overloading_Int_div() {
        // setup
        val matrix = Matrix(
                intArrayOf(3, 4),
                intArrayOf(6, 2)
        )

        // verify
        Assertions.assertThat(12 / matrix).isEqualTo(Matrix(
                intArrayOf(4, 3),
                intArrayOf(2, 6)
        ))
    }

    @Test
    fun test_operator_overloading_Float_plus() {
        // setup
        val matrix = Matrix(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6)
        )

        // verify
        Assertions.assertThat(3.0f + matrix).isEqualTo(Matrix(
                intArrayOf(4, 5, 6),
                intArrayOf(7, 8, 9)
        ))
    }

    @Test
    fun test_operator_overloading_Float_minus() {
        // setup
        val matrix = Matrix(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6)
        )

        // verify
        Assertions.assertThat(3.0f - matrix).isEqualTo(Matrix(
                intArrayOf(2, 1, 0),
                intArrayOf(-1, -2, -3)
        ))
    }

    @Test
    fun test_operator_overloading_Float_times() {
        // setup
        val matrix = Matrix(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6)
        )

        // verify
        Assertions.assertThat(3.0f * matrix).isEqualTo(Matrix(
                intArrayOf(3, 6, 9),
                intArrayOf(12, 15, 18)
        ))
    }

    @Test
    fun test_operator_overloading_Float_div() {
        // setup
        val matrix = Matrix(
                intArrayOf(3, 4),
                intArrayOf(6, 2)
        )

        // verify
        Assertions.assertThat(12.0f / matrix).isEqualTo(Matrix(
                intArrayOf(4, 3),
                intArrayOf(2, 6)
        ))
    }

    @Test
    fun test_operator_overloading_unaryMinus() {
        // setup
        val matrix = Matrix(
            intArrayOf(3, 4),
            intArrayOf(6, 2)
        )

        // verify
        Assertions.assertThat(-matrix).isEqualTo(Matrix(
            intArrayOf(-3, -4),
            intArrayOf(-6, -2)
        ))
    }

    @Test
    fun test_operator_overloading_single_index_access() {
        // setup
        val matrix = Matrix(
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        )

        // verify
        Assertions.assertThat(matrix[1]).isEqualTo(Matrix(
            intArrayOf(3, 4)
        ))
    }

    @Test
    fun test_operator_overloading_multi_index_access() {
        // setup
        val matrix = Matrix(
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        )

        // verify
        Assertions.assertThat(matrix[1, 0]).isEqualTo(3.0)
    }

    @Test
    fun test_operator_overloading_plus_matrix() {
        // setup
        val a = Matrix(
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        )
        val b = Matrix(
            intArrayOf(5, 6),
            intArrayOf(7, 8)
        )

        // verify
        Assertions.assertThat(a + b).isEqualTo(Matrix(
            intArrayOf(6, 8),
            intArrayOf(10, 12)
        ))
    }

    @Test
    fun test_operator_overloading_minus_matrix() {
        // setup
        val a = Matrix(
                intArrayOf(1, 2),
                intArrayOf(3, 4)
        )
        val b = Matrix(
                intArrayOf(5, 6),
                intArrayOf(7, 8)
        )

        // verify
        Assertions.assertThat(a - b).isEqualTo(Matrix(
                intArrayOf(-4, -4),
                intArrayOf(-4, -4)
        ))
    }

    @Test
    fun test_operator_overloading_times_matrix() {
        // setup
        val a = Matrix(
                intArrayOf(1, 2),
                intArrayOf(3, 4)
        )
        val b = Matrix(
                intArrayOf(5, 6),
                intArrayOf(7, 8)
        )

        // verify
        Assertions.assertThat(a * b).isEqualTo(Matrix(
                intArrayOf(5, 12),
                intArrayOf(21, 32)
        ))
    }

    @Test
    fun test_operator_overloading_div_matrix() {
        // setup
        val a = Matrix(
                intArrayOf(12, 8),
                intArrayOf(32, 9)
        )
        val b = Matrix(
                intArrayOf(2, 4),
                intArrayOf(8, 3)
        )

        // verify
        Assertions.assertThat(a / b).isEqualTo(Matrix(
                intArrayOf(6, 2),
                intArrayOf(4, 3)
        ))
    }

    @Test
    fun test_dot_matrix_1() {
        // setup
        val a = Matrix(
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        )
        val b = Matrix(
            intArrayOf(5),
            intArrayOf(7)
        )

        // verify
        Assertions.assertThat(a.dot(b)).isEqualTo(Matrix(
            intArrayOf(1*5 + 2*7),
            intArrayOf(3*5 + 4*7)
        ))
    }

    @Test
    fun test_dot_matrix_2() {
        // setup
        val a = Matrix(
                intArrayOf(1, 2),
                intArrayOf(3, 4)
        )
        val b = Matrix(
                intArrayOf(5, 6, 7),
                intArrayOf(7, 8, 9)
        )

        // verify
        Assertions.assertThat(a.dot(b)).isEqualTo(Matrix(
                intArrayOf(1*5 + 2*7, 1*6 + 2*8, 1*7 + 2*9),
                intArrayOf(3*5 + 4*7, 3*6 + 4*8, 3*7 + 4*9)
        ))
    }

    @Test
    fun test_dot_method_validates_that_this_col_size_equals_to_other_row_size() {
        // setup
        val a = Matrix(
                intArrayOf(1, 2),
                intArrayOf(3, 4)
        )
        val b = Matrix(
                intArrayOf(5, 6, 7)
        )
        
        // verify
        Assertions.assertThatThrownBy {
            // exercise
            a.dot(b)
        }
        .isInstanceOf(IllegalArgumentException::class.java)
        .hasMessage("Expected row size is 2. However actual is 1.")
    }

    @Test
    fun test_exp() {
        // setup
        val matrix = Matrix(
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        )
        
        // exercise
        val actual = matrix.exp()
        
        // verify
        Assertions.assertThat(actual).isEqualTo(Matrix(
            doubleArrayOf(Math.exp(1.0), Math.exp(2.0)),
            doubleArrayOf(Math.exp(3.0), Math.exp(4.0))
        ))
    }

    @Test
    fun test_log() {
        // setup
        val matrix = Matrix(
                intArrayOf(1, 2),
                intArrayOf(3, 4)
        )

        // exercise
        val actual = matrix.log()

        // verify
        Assertions.assertThat(actual).isEqualTo(Matrix(
                doubleArrayOf(Math.log(1.0), Math.log(2.0)),
                doubleArrayOf(Math.log(3.0), Math.log(4.0))
        ))
    }

    @Test
    fun test_flatten() {
        // setup
        val matrix = Matrix(
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        )

        // exercise
        val actual = matrix.flatten()

        // verify
        Assertions.assertThat(actual).isEqualTo(Matrix(
            intArrayOf(1, 2, 3, 4)
        ))
    }

    @Test
    fun test_operator_overloading_plus_matrix_broadcast_same_column_size() {
        // setup
        val x = Matrix(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6)
        )
        val y = Matrix(4, 4, 4)
        
        // exercise
        val actual = x + y
        
        // verify
        Assertions.assertThat(actual).isEqualTo(Matrix(
            intArrayOf(5, 6, 7),
            intArrayOf(8, 9, 10)
        ))
    }

    @Test
    fun test_operator_overloading_plus_matrix_broadcast_same_column_size_reverse() {
        // setup
        val x = Matrix(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6)
        )
        val y = Matrix(4, 4, 4)

        // exercise
        val actual = y + x

        // verify
        Assertions.assertThat(actual).isEqualTo(Matrix(
            intArrayOf(5, 6, 7),
            intArrayOf(8, 9, 10)
        ))
    }

    @Test
    fun test_operator_overloading_plus_matrix_broadcast_same_row_size() {
        // setup
        val x = Matrix(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6)
        )
        val y = Matrix(
            intArrayOf(5),
            intArrayOf(5)
        )

        // exercise
        val actual = x + y

        // verify
        Assertions.assertThat(actual).isEqualTo(Matrix(
            intArrayOf(6, 7, 8),
            intArrayOf(9, 10, 11)
        ))
    }

    @Test
    fun test_operator_overloading_plus_matrix_broadcast_same_row_size_reverse() {
        // setup
        val x = Matrix(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6)
        )
        val y = Matrix(
            intArrayOf(5),
            intArrayOf(5)
        )

        // exercise
        val actual = y + x

        // verify
        Assertions.assertThat(actual).isEqualTo(Matrix(
            intArrayOf(6, 7, 8),
            intArrayOf(9, 10, 11)
        ))
    }

    @Test
    fun test_operator_overloading_plus_matrix_broadcast_exception_if_rowSize_is_same_but_colSize_is_not_1() {
        // setup
        val x = Matrix(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6)
        )
        val y = Matrix(
            intArrayOf(5, 6),
            intArrayOf(5, 6)
        )

        // exercise, verify
        Assertions.assertThatThrownBy { x + y }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Can't broadcast. This shape is ${x.shape} and other shape is ${y.shape}.")
    }

    @Test
    fun test_operator_overloading_minus_matrix_broadcast_same_column_size() {
        // setup
        val x = Matrix(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6)
        )
        val y = Matrix(4, 4, 4)

        // exercise
        val actual = x - y

        // verify
        Assertions.assertThat(actual).isEqualTo(Matrix(
            intArrayOf(-3, -2, -1),
            intArrayOf(0, 1, 2)
        ))
    }

    @Test
    fun test_operator_overloading_times_matrix_broadcast_same_column_size() {
        // setup
        val x = Matrix(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6)
        )
        val y = Matrix(4, 4, 4)

        // exercise
        val actual = x * y

        // verify
        Assertions.assertThat(actual).isEqualTo(Matrix(
            intArrayOf(4, 8, 12),
            intArrayOf(16, 20, 24)
        ))
    }

    @Test
    fun test_operator_overloading_div_matrix_broadcast_same_column_size() {
        // setup
        val x = Matrix(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6)
        )
        val y = Matrix(4, 4, 4)

        // exercise
        val actual = x / y

        // verify
        Assertions.assertThat(actual).isEqualTo(Matrix(
            doubleArrayOf(1.0/4.0, 2.0/4.0, 3.0/4.0),
            doubleArrayOf(4.0/4.0, 5.0/4.0, 6.0/4.0)
        ))
    }

    @Test
    fun test_sumVertical() {
        // setup
        val matrix = Matrix(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6)
        )
        
        // exercise
        val actual = matrix.sumVertical()
        
        // verify
        Assertions.assertThat(actual).isEqualTo(Matrix(5, 7, 9))
    }

    @Test
    fun test_maxAtHorizontal() {
        // setup
        val matrix = Matrix(
            intArrayOf(1, 2, 3),
            intArrayOf(2, 3, 1),
            intArrayOf(3, 2, 3),
            intArrayOf(1, 2, 3)
        )
        
        // exercise
        val max = matrix.maxAtHorizontal()
        
        // verify
        Assertions.assertThat(max).isEqualTo(Matrix(
            intArrayOf(2),
            intArrayOf(1),
            intArrayOf(0),
            intArrayOf(2)
        ))
    }

    @Test
    fun test_countIfIndexes() {
        // setup
        val x = Matrix(
            intArrayOf(1),
            intArrayOf(0),
            intArrayOf(4),
            intArrayOf(3)
        )
        val y = Matrix(
            intArrayOf(1),
            intArrayOf(0),
            intArrayOf(2),
            intArrayOf(3)
        )
        
        // exercise
        val actual = x.countIfIndexes { rowIndex, colIndex, value -> 
            value == y[rowIndex, colIndex]
        }
        
        // verify
        Assertions.assertThat(actual).isEqualTo(3)
    }
}
