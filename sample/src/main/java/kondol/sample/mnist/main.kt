package kondol.sample.mnist

import kondol.mnist.Mnist
import java.nio.file.Paths

fun main(args: Array<String>) {
    val mnist = Mnist.download()
    mnist.createTrainingImageReader().use { reader ->
        val imageList = reader.random(10)
        println(imageList.imageMatrix)
        println(imageList.teacherMatrix)
    }
}

