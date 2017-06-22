package kondol.mnist

import org.slf4j.LoggerFactory
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path
import java.util.zip.GZIPInputStream


internal class MnistDownloader (
    private val downloadDir: Path,
    private val trainingImage: Path,
    private val trainingLabel: Path,
    private val testImage: Path,
    private val testLabel: Path
) {
    private val logger = LoggerFactory.getLogger(MnistDownloader::class.java)
    private val TRAINING_IMAGE_URL = URL("http://yann.lecun.com/exdb/mnist/train-images-idx3-ubyte.gz")
    private val TRAINING_LABEL_URL = URL("http://yann.lecun.com/exdb/mnist/train-labels-idx1-ubyte.gz")
    private val TEST_IMAGE_URL = URL("http://yann.lecun.com/exdb/mnist/t10k-images-idx3-ubyte.gz")
    private val TEST_LABEL_URL = URL("http://yann.lecun.com/exdb/mnist/t10k-labels-idx1-ubyte.gz")
    
    fun download() {
        if (Files.notExists(this.downloadDir)) {
            Files.createDirectories(this.downloadDir)
        }
        
        this.downloadGzipFile(TRAINING_IMAGE_URL, this.trainingImage)
        this.downloadGzipFile(TRAINING_LABEL_URL, this.trainingLabel)
        this.downloadGzipFile(TEST_IMAGE_URL, this.testImage)
        this.downloadGzipFile(TEST_LABEL_URL, this.testLabel)
    }
    
    private fun downloadGzipFile(url: URL, file: Path) {
        val out = this.downloadDir.resolve(file)
        
        if (Files.exists(out)) {
            logger.debug("Skip to download '$url' because it already exists.")
            return
        }
        
        logger.info("Downloading '$url' ...")
        GZIPInputStream(url.openStream()).use { inputStream ->
            Files.copy(inputStream, out)
        }
    }
}