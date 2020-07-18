package com.example.background.workers

import android.content.Context
import android.graphics.BitmapFactory
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.background.R
import timber.log.Timber

/**
 * [androidx.work.ListenableWorker] subclass that implements [Worker.doWork] to provide the code
 * for applying the blur filter on an Image in a background process.
 *
 * @param context The application [Context]
 * @param workerParams Parameters to setup the internal state of this worker
 *
 * @author Kaushik N Sanji
 */
class BlurWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    /**
     * This method is called on a background thread. Required work must be done **synchronously**
     * and should return the [androidx.work.ListenableWorker.Result] from this method. Once you return from this
     * method, the Worker is considered to have finished what its doing and will be destroyed.  If
     * you need to do your work asynchronously on a thread of your own choice, see
     * [androidx.work.ListenableWorker].
     *
     * A Worker is given a maximum of ten minutes to finish its execution and return a
     * [androidx.work.ListenableWorker.Result].  After this time has expired, the Worker will
     * be signalled to stop.
     *
     * @return The [androidx.work.ListenableWorker.Result] of the computation; note that
     * dependent work will not execute if you use
     * [androidx.work.ListenableWorker.Result.failure] or
     * [androidx.work.ListenableWorker.Result.failure(data)]
     */
    override fun doWork(): Result {
        // Get App Context
        val appContext = applicationContext

        // Show a Notification before starting the work for blurring the image
        makeStatusNotification("Blurring Image...", appContext)

        return try {
            // Get the Picture to be blurred
            val pictureToBlur = BitmapFactory.decodeResource(appContext.resources, R.drawable.test)

            // Apply the blur filter on the Image
            val blurredPicture = blurBitmap(pictureToBlur, appContext)

            // Write the result to a temporary image file
            val blurredPictureUri = writeBitmapToFile(appContext, blurredPicture)

            // Show a Notification to display the File URI to Blurred Image file
            makeStatusNotification("Blurred Image saved to $blurredPictureUri", appContext)

            // Return as successful
            Result.success()
        } catch (throwable: Throwable) {
            // Log the error
            Timber.e(throwable, "Error occurred while applying blur")
            // Return as failed
            Result.failure()
        }
    }
}