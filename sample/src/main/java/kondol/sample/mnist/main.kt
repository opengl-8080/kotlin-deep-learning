package kondol.sample.mnist

import kondol.mnist.Mnist
import java.nio.file.Paths

fun main(args: Array<String>) {
    val mnist = Mnist.download()
    mnist.createTrainingImageReader().use { reader ->
        val image = reader.readImage(1)
        image.export(Paths.get("mnist/1.jpeg"))
    }
}

